package com.project.assignment.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.assignment.dto.GooglePayload;
import com.project.assignment.entity.Roles;
import com.project.assignment.entity.Users;
import com.project.assignment.service.RoleService;
import com.project.assignment.service.UserService;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.*;

@Component
public class JwtUtil {

    @Autowired
    private UserService userService;

    @Value("${app.secret.key}")
    private String jwtSecret;

    @Value("${project.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    @Autowired
    private RoleService roleService;


    public String generateJWTToken(Users user, Set<String> roles) {
        String tokenId = String.valueOf(new Random().nextInt(10000));

        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", user.getUserId());
        claims.put("roleName", user.getRoleId().getRoleName());
        claims.put("roleId", user.getRoleId().getRoleId());
        claims.put("firstName", user.getFirstName());
        claims.put("lastName", user.getLastName());
        claims.put("userName", user.getUserName());
        claims.put("email", user.getEmail());
        claims.put("courseName", user.getCourse().getCourseName());

        return Jwts.builder()
                .setId(tokenId)
                .setSubject(user.getUserName())
                .setIssuer("ABC_Ltd")
                .setClaims(claims)
                .setAudience("XYZ_Ltd")
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, Base64.getEncoder().encode(jwtSecret.getBytes()))
                .compact();
    }

    // Parse JWT token and extract claims
    public static Claims getClaims(String token, String jwtSecret1) {
        try {

            int i = token.lastIndexOf('.');
            String withoutSignature = token.substring(0, i + 1);
            Jwt<Header, Claims> untrusted = Jwts.parser().parseClaimsJwt(withoutSignature);

            Claims claims = untrusted.getBody();

            System.out.println("Claims " + claims);
            return claims;
        } catch (Exception e) {

            // Handle token parsing error
            e.printStackTrace();
            return null;
        }
    }

    // Validate if the token is expired
    public boolean isTokenExpired(String token) {
        Date expirationDate = getExpirationDate(token);
        return expirationDate != null && expirationDate.before(new Date());
    }

    // Get expiration date of the token
    public Date getExpirationDate(String token) {
        Claims claims = getClaims(token, jwtSecret);
        return claims != null ? claims.getExpiration() : null;
    }

    public boolean isValidToken(String token, String userName) {
        String tokenUserName = getSubject(token);
        return userName != null && userName.equals(tokenUserName) && !isTokenExpired(token);
    }


    public String getSubject(String token) {
        String[] split = token.split("\\.");
        String payload = new String(Base64.getUrlDecoder().decode(split[1]));

        ObjectMapper mapper = new ObjectMapper();
        String userName = null;
        GooglePayload parser = null;

        try {
            parser = mapper.readValue(payload, GooglePayload.class);
            userName = parser.getEmail();

            if (userName != null) {
                Optional<Users> findByUserName = userService.findByUserName(userName);

                if (findByUserName.isEmpty()) {
                    Users user = new Users();
                    user.setUserName(userName);
                    user.setPassword(userName); // Consider hashing the password in a real application

                    // Fetch the role entity from the database
                    List<Roles> rolesList = roleService.findByRoleName("superAdmin");
                    if (rolesList.isEmpty()) {
                        throw new RuntimeException("Error: Role not found.");
                    }
                    Roles userRole = rolesList.get(0); // Assuming you want the first match

                    // Set the role in the user entity
                    user.setRoleId(userRole);

                    try {
                        userService.saveUser(user);
                    } catch (DataIntegrityViolationException e) {
                        // Handle the case where the user already exists in the database
                        System.err.println("User with email '" + userName + "' already exists.");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // For normal login scenarios
        if (userName == null) {
            userName = parser != null ? parser.getSub() : null;
        }

        return userName;
    }


    public String getJwtSecret() {
        return jwtSecret;
    }

    public Long getUserIdFromToken(String token) {
        Claims claims = getClaims(token, jwtSecret);
        return claims.get("userId", Long.class);
    }

    public String getUserNameFromToken(String token) {
        Claims claims = getClaims(token, jwtSecret);
        return claims != null ? claims.get("userName", String.class) : null;
    }
}
