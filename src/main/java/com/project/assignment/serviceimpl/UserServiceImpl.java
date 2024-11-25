package com.project.assignment.serviceimpl;

import com.project.assignment.dto.UserDTO;
import com.project.assignment.entity.*;
import com.project.assignment.exception.UserAlreadyExistException;
import com.project.assignment.repository.CourseRepository;
import com.project.assignment.repository.RefreshTokenRepository;
import com.project.assignment.repository.RoleResourceEntitlementRepository;
import com.project.assignment.repository.UserRepository;
import com.project.assignment.service.MailService;
import com.project.assignment.service.RefreshTokenService;
import com.project.assignment.service.RoleService;
import com.project.assignment.service.UserService;
import com.project.assignment.util.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    @Autowired
    private BCryptPasswordEncoder bCryptEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private MailService mailService;

    @Autowired
    private RefreshTokenService refreshTokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleResourceEntitlementRepository roleResourceEntitlementRepository;

    @Autowired
    private RefreshTokenRepository refreshTokenRepository;

    @Override
    public Optional<Users> findByUserName(String username) {
        return userRepository.findByUserName(username);
    }


    @Override
    public Integer saveUser(Users user) {
        System.out.println("user details are " + user);
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        return userRepository.save(user).getUserId();
    }


//    @Override
//    public UserResponse createUser(Map<String, Object> userPayload, Long createdByUserId) {
//        // Extract payload data
//        String userName = (String) userPayload.get("userName");
//        String password = (String) userPayload.get("password");
//        String email = (String) userPayload.get("email");
//        String firstName = (String) userPayload.get("firstName");
//        String lastName = (String) userPayload.get("lastName");
//        String status = (String) userPayload.get("status");
//        Long roleId = Long.valueOf((Integer) userPayload.get("roleId"));
//        Long courseId = Long.valueOf((Integer) userPayload.get("courseId"));
//
//        // Check if the username already exists
//        Optional<Users> existingUserByUsername = findByUserName(userName);
//        if (existingUserByUsername.isPresent()) {
//            throw new UserAlreadyExistException("Provided UserName '" + userName + "' Already Exists, Please Choose a Different UserName");
//        }
//
//        // Check if the email already exists
//        Optional<Users> existingUserByEmail = findUserByEmail(email);
//        if (existingUserByEmail.isPresent()) {
//            throw new UserAlreadyExistException("Provided Email '" + email + "' Already Exists, Please Choose a Different Email");
//        }
//
//        // Fetch role from the database
//        Roles role = roleService.findById(roleId)
//                .orElseThrow(() -> new UserAlreadyExistException("Invalid roleId provided"));
//
//        // Validate the courseId
//        if (!courseRepository.existsById(courseId)) {
//            throw new UserAlreadyExistException("Invalid courseId provided");
//        }
//
//        // Create and save user
//        Users user = new Users();
//        user.setUserName(userName);
//        user.setPassword(password); // Consider encrypting the password before saving
//        user.setEmail(email);
//        user.setFirstName(firstName);
//        user.setLastName(lastName);
//        user.setStatus(status);
//        user.setRoleId(role); // Set the role as a Roles entity
//        user.setCourseId(courseId); // Set courseId explicitly
//
//        // Set createdBy and createdDate
//        user.setCreatedBy(createdByUserId);
//        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
//        user.setCreatedDate(currentTimestamp);
//
//        // Set updatedBy and updatedDate the same as createdBy and createdDate
//        user.setUpdatedBy(createdByUserId);
//        user.setUpdatedDate(currentTimestamp);
//
//        // Save the user
//        Integer id = saveUser(user);
//
//        // Retrieve the saved user from the database
//        Optional<Users> optionalUser = findById(id);
//        Users savedUser = optionalUser.orElseThrow(() -> new RuntimeException("Failed to fetch saved user details"));
//
//        // Fetch createdBy and updatedBy usernames
//        Users createdByUser = getUserById(savedUser.getCreatedBy());
//        Users updatedByUser = getUserById(savedUser.getUpdatedBy());
//
//        String createdByUsername = (createdByUser != null) ? createdByUser.getUserName() : "Unknown";
//        String updatedByUsername = (updatedByUser != null) ? updatedByUser.getUserName() : "Unknown";
//
//        String loginLink = "http://localhost:4200/login";
//
//        // Send email notification with a template
//        mailService.sendTemplatedEmail(email, "Welcome to Advanced Learning Platform", firstName, userName, password, loginLink);
//
//        // Prepare response with all user attributes
//        return UserResponse.builder()
//                .userId(savedUser.getUserId())
//                .userName(savedUser.getUserName())
//                .firstName(savedUser.getFirstName())
//                .lastName(savedUser.getLastName())
//                .email(savedUser.getEmail())
//                .status(savedUser.getStatus())
//                .roleName(savedUser.getRoleId().getRoleName()) // Get roleName from RolesEntity
//                .roleId(savedUser.getRoleId().getRoleId()) // Get roleId from RolesEntity
//                .courseId(savedUser.getCourseId()) // Get courseId directly
//                .courseName(savedUser.getCourse().getCourseName())
//                .createdBy(createdByUsername) // Use createdByUsername
//                .createdDate(savedUser.getCreatedDate())
//                .updatedBy(updatedByUsername) // Use updatedByUsername
//                .updatedDate(savedUser.getUpdatedDate())
//                .message("User with id '" + id + "' saved successfully!")
//                .build();
//    }


    @Override
    public UserResponse createUser(Map<String, Object> userPayload, Long createdByUserId) {
        // Extract payload data
        String userName = (String) userPayload.get("userName");
        String password = (String) userPayload.get("password");
        String email = (String) userPayload.get("email");
        String firstName = (String) userPayload.get("firstName");
        String lastName = (String) userPayload.get("lastName");
        String status = (String) userPayload.get("status");
        Long roleId = Long.valueOf((Integer) userPayload.get("roleId"));
        Long courseId = Long.valueOf((Integer) userPayload.get("courseId"));

        // Check if the username already exists
        Optional<Users> existingUserByUsername = findByUserName(userName);
        if (existingUserByUsername.isPresent()) {
            throw new UserAlreadyExistException("Provided UserName '" + userName + "' Already Exists, Please Choose a Different UserName");
        }

        // Check if the email already exists
        Optional<Users> existingUserByEmail = findUserByEmail(email);
        if (existingUserByEmail.isPresent()) {
            throw new UserAlreadyExistException("Provided Email '" + email + "' Already Exists, Please Choose a Different Email");
        }

        // Fetch role from the database
        Roles role = roleService.findById(roleId)
                .orElseThrow(() -> new UserAlreadyExistException("Invalid roleId provided"));

        // Fetch course entity
        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new UserAlreadyExistException("Invalid courseId provided"));

        // Create and save user
        Users user = new Users();
        user.setUserName(userName);
        user.setPassword(password); // Consider encrypting the password before saving
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setStatus(status);
        user.setRoleId(role); // Set the role as a Roles entity
        user.setCourse(course); // Set course entity directly

        // Set createdBy and createdDate
        user.setCreatedBy(createdByUserId);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        user.setCreatedDate(currentTimestamp);

        // Set updatedBy and updatedDate the same as createdBy and createdDate
        user.setUpdatedBy(createdByUserId);
        user.setUpdatedDate(currentTimestamp);

        // Save the user
        Integer id = saveUser(user);

        // Retrieve the saved user from the database
        Optional<Users> optionalUser = findById(id);
        Users savedUser = optionalUser.orElseThrow(() -> new RuntimeException("Failed to fetch saved user details"));

        // Fetch createdBy and updatedBy usernames
        Users createdByUser = getUserById(savedUser.getCreatedBy());
        Users updatedByUser = getUserById(savedUser.getUpdatedBy());

        String createdByUsername = (createdByUser != null) ? createdByUser.getUserName() : "Unknown";
        String updatedByUsername = (updatedByUser != null) ? updatedByUser.getUserName() : "Unknown";

        String loginLink = "http://localhost:4200/login";

        // Send email notification with a template
        mailService.sendTemplatedEmail(email, "Welcome to Advanced Learning Platform", firstName, userName, password, loginLink);

        // Prepare response with all user attributes
        return UserResponse.builder()
                .userId(savedUser.getUserId())
                .userName(savedUser.getUserName())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .status(savedUser.getStatus())
                .roleName(savedUser.getRoleId().getRoleName()) // Get roleName from RolesEntity
                .roleId(savedUser.getRoleId().getRoleId()) // Get roleId from RolesEntity
                .courseId(savedUser.getCourse().getCourseId()) // Get courseId directly
                .courseName(savedUser.getCourse().getCourseName()) // Get courseName directly
                .createdBy(createdByUsername) // Use createdByUsername
                .createdDate(savedUser.getCreatedDate())
                .updatedBy(updatedByUsername) // Use updatedByUsername
                .updatedDate(savedUser.getUpdatedDate())
                .message("User with id '" + id + "' saved successfully!")
                .build();
    }


    private Users getUserById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public Optional<Users> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<Users> findById(Integer userId) {
        return userRepository.findById(Long.valueOf(userId));
    }

    @Transactional
    @Override
    public UserResponse loginUser(UserRequest request) {
        // Fetch user by username
        Optional<Users> userOptional = userRepository.findByUserName(request.getUserName());
        if (userOptional.isEmpty()) {
            throw new UserAlreadyExistException("Provided Username '" + request.getUserName() + "' does not exist. Please Signup First.");
        }

        // Authenticate the user
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));

        // Load user details and create a refresh token
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUserName());
        RefreshToken refreshToken = this.refreshTokenService.createRefreshToken(userDetails.getUsername());

        // Get roles and generate JWT token
        Set<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

        Users user = userOptional.get();

        // Update lastLoggedInTime
        userRepository.updateLastLoggedInTime(user.getUserName(), LocalDateTime.now());
        user.setLastLoggedInTime(Timestamp.valueOf(LocalDateTime.now()));

        String roleName = user.getRoleId().getRoleName();
        String courseName = user.getCourse().getCourseName();
        String token = this.jwtUtil.generateJWTToken(user, roles);

        // Fetch entitlements based on roleId
        List<Object[]> entitlementsAndResources = roleResourceEntitlementRepository.findEntitlementNamesAndResourceNamesByRoleId(user.getRoleId().getRoleId());

        // Map resource names to their entitlements
        Map<String, List<String>> resourceEntitlementsMap = new HashMap<>();
        for (Object[] entry : entitlementsAndResources) {
            String entitlementName = (String) entry[0];
            String resourceName = (String) entry[1];
            resourceEntitlementsMap.computeIfAbsent(resourceName, k -> new ArrayList<>()).add(entitlementName);
        }

        // Build and return the user response
        return UserResponse.builder()
                .token(token)
                .refreshToken(refreshToken.getRefreshToken())
                .message("Login successful")
                .userId(user.getUserId())
                .roleId(user.getRoleId().getRoleId())
                .courseId(user.getCourse().getCourseId())
                .email(user.getEmail())
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .status(user.getStatus())
                .roleName(roleName)
                .courseName(courseName)
                .entitlements(resourceEntitlementsMap)
                .lastLoggedInTime(user.getLastLoggedInTime()) // Add entitlements to the response
                .build();
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<Users> opt = userRepository.findByUserName(userName);
        System.out.println("USER IS PRESENT: " + opt.isPresent());

        org.springframework.security.core.userdetails.User springUser = null;
        if (opt.isEmpty()) {
            throw new UsernameNotFoundException("User with userName: " + userName + " not found");
        } else {
            Users user = opt.get();    // Retrieve user from DB

            // Fetch role details using roleId from User entity
            Roles role = roleService.findById(user.getRoleId().getRoleId()).orElseThrow(() -> new UsernameNotFoundException("Role not found for user: " + userName));

            // Construct authorities for Spring Security
            Set<GrantedAuthority> authorities = new HashSet<>();
            authorities.add(new SimpleGrantedAuthority(role.getStatus())); // Assuming role.getStatus() provides authority

            springUser = new org.springframework.security.core.userdetails.User(userName, user.getPassword(), authorities);
        }
        return springUser;
    }

    @Override
    public Users findUserByResetToken(String resetToken) {
        return userRepository.findByResetToken(resetToken);
    }

    @Override
    public List<UserDTO> getUsersByCourseIdAndStatus(Long courseId, String status) {
        if (courseId == null || courseId == 0) {
            throw new UserAlreadyExistException("Invalid arguments: either ID is 0 or missing in payload.");
        }

        Courses courses = courseRepository.findById(courseId)
                .orElseThrow(() -> new UserAlreadyExistException("Course with ID " + courseId + " not found"));

        List<Users> users = userRepository.findByCourse_CourseIdAndStatus(courseId, status);

        if (users.isEmpty()) {
            throw new UserAlreadyExistException("No users found for course ID " + courseId);
        }

        return users.stream().map(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(Long.valueOf(user.getUserId())); // Assuming getUserId returns Long
            userDTO.setUserName(user.getUserName());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTO.setStatus(user.getStatus());
            userDTO.setRoleName(user.getRoleId().getRoleName()); // Assuming you want roleName from RolesEntity
            userDTO.setRoleId(user.getRoleId().getRoleId()); // Get the roleId from RolesEntity
            userDTO.setCourseName(user.getCourse().getCourseName());
            userDTO.setCourseId(user.getCourse().getCourseId());
            userDTO.setLastLoggedInTime(user.getLastLoggedInTime() != null ? user.getLastLoggedInTime().toString() : "Yet Not Logged In");

            // Fetch createdBy user entity and set the username
            Long createdById = user.getCreatedBy();
            if (createdById != null) {
                Users createdByUser = getUserById(createdById);
                if (createdByUser != null) {
                    userDTO.setCreatedBy(createdByUser.getUserName());
                } else {
                    userDTO.setCreatedBy("Unknown");
                }
            } else {
                userDTO.setCreatedBy("Unknown");
            }

            // Fetch updatedBy user entity and set the username
            Long updatedById = user.getUpdatedBy();
            if (updatedById != null) {
                Users updatedByUser = getUserById(updatedById);
                if (updatedByUser != null) {
                    userDTO.setUpdatedBy(updatedByUser.getUserName());
                } else {
                    userDTO.setUpdatedBy("Unknown");
                }
            } else {
                userDTO.setUpdatedBy("Unknown");
            }

            // Format dates
            userDTO.setCreatedDate(user.getCreatedDate() != null ? user.getCreatedDate().toString() : "Yet Not Logged In");
            userDTO.setUpdatedDate(user.getUpdatedDate() != null ? user.getUpdatedDate().toString() : "Yet Not Logged In");

            return userDTO;
        }).collect(Collectors.toList());
    }

    @Override
    public UserDTO getUserByUserId(Integer userId) {
        Optional<Users> userOptional = findById(userId);
        if (userOptional.isPresent()) {
            Users user = userOptional.get();
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getUserId().longValue());
            userDTO.setUserName(user.getUserName());
            userDTO.setFirstName(user.getFirstName());
            userDTO.setLastName(user.getLastName());
            userDTO.setEmail(user.getEmail());
            userDTO.setStatus(user.getStatus());
            userDTO.setRoleName(user.getRoleId().getRoleName()); // Assuming you want roleName from RolesEntity
            userDTO.setRoleId(user.getRoleId().getRoleId()); // Get the roleId from RolesEntity
            userDTO.setCourseName(user.getCourse().getCourseName());
            userDTO.setCourseId(user.getCourse().getCourseId());

            // Check if lastLoggedInTime is null and set accordingly
            if (user.getLastLoggedInTime() != null) {
                userDTO.setLastLoggedInTime(String.valueOf(user.getLastLoggedInTime()));
            } else {
                userDTO.setLastLoggedInTime("-");
            }

            // Fetch createdBy user entity and set the username
            Long createdById = user.getCreatedBy();
            if (createdById != null) {
                Users createdByUser = getUserById(createdById);
                userDTO.setCreatedBy(createdByUser != null ? createdByUser.getUserName() : "Unknown");
            } else {
                userDTO.setCreatedBy("Unknown");
            }

            // Fetch updatedBy user entity and set the username
            Long updatedById = user.getUpdatedBy();
            if (updatedById != null) {
                Users updatedByUser = getUserById(updatedById);
                userDTO.setUpdatedBy(updatedByUser != null ? updatedByUser.getUserName() : "Unknown");
            } else {
                userDTO.setUpdatedBy("Unknown");
            }

            userDTO.setCreatedDate(String.valueOf(user.getCreatedDate()));
            userDTO.setUpdatedDate(String.valueOf(user.getUpdatedDate()));

            return userDTO;
        } else {
            throw new UserAlreadyExistException("User with ID " + userId + " not found");
        }
    }


    @Override
    public String deleteUserByUserId(Integer userId) {
        Optional<Users> userOptional = userRepository.findById(Long.valueOf(userId));

        if (userOptional.isPresent()) {
            // First, delete the related refresh tokens
            refreshTokenRepository.deleteByUserId(userId);

            // Then, delete the user
            userRepository.delete(userOptional.get());
            return "User Deleted Successfully";
        } else {
            throw new UserAlreadyExistException("User with ID " + userId + " not found");
        }
    }


    @Override
    public UserResponse updateUser(Integer userId, Map<String, Object> userPayload, Long updatedByUserId) {
        // Fetch the existing user from the database
        Optional<Users> optionalUser = findById(userId);
        Users existingUser = optionalUser.orElseThrow(() -> new UserAlreadyExistException("User not found"));

        // Update user fields
        if (userPayload.containsKey("userName")) {
            String userName = (String) userPayload.get("userName");
            if (!userName.equals(existingUser.getUserName())) {
                Optional<Users> existingUserByUsername = findByUserName(userName);
                if (existingUserByUsername.isPresent()) {
                    throw new UserAlreadyExistException("Provided Username '" + userName + "' Already Exists, Please Choose a Different Username");
                }
                existingUser.setUserName(userName);
            }
        }

        if (userPayload.containsKey("email")) {
            String email = (String) userPayload.get("email");
            if (!email.equals(existingUser.getEmail())) {
                Optional<Users> existingUserByEmail = findUserByEmail(email);
                if (existingUserByEmail.isPresent()) {
                    throw new UserAlreadyExistException("Provided Email '" + email + "' Already Exists, Please Choose a Different Email");
                }
                existingUser.setEmail(email);
            }
        }

        if (userPayload.containsKey("firstName")) {
            existingUser.setFirstName((String) userPayload.get("firstName"));
        }

        if (userPayload.containsKey("lastName")) {
            existingUser.setLastName((String) userPayload.get("lastName"));
        }

        if (userPayload.containsKey("status")) {
            existingUser.setStatus((String) userPayload.get("status"));
        }

        if (userPayload.containsKey("password")) {
            existingUser.setPassword((String) userPayload.get("password")); // Encrypt in production
        }

        if (userPayload.containsKey("roleId")) {
            Long roleId = Long.valueOf((Integer) userPayload.get("roleId"));
            Roles role = roleService.findById(roleId)
                    .orElseThrow(() -> new UserAlreadyExistException("Invalid roleId provided"));
            existingUser.setRoleId(role);
        }

        if (userPayload.containsKey("courseId")) {
            Long courseId = Long.valueOf((Integer) userPayload.get("courseId"));
            Courses course = courseRepository.findById(courseId)
                    .orElseThrow(() -> new UserAlreadyExistException("Invalid courseId provided"));

        }


        // Set updatedBy and updatedDate
        existingUser.setUpdatedBy(updatedByUserId);
        existingUser.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

        if (userPayload.containsKey("lastLoggedInTime")) {
            existingUser.setLastLoggedInTime((Timestamp) userPayload.get("lastLoggedInTime"));
        }

        // Save updated user to the database
        saveUser(existingUser);

        // Fetch createdBy and updatedBy usernames
        Long createdById = existingUser.getCreatedBy();
        Long updatedById = existingUser.getUpdatedBy();

        Users createdByUser = getUserById(createdById);
        Users updatedByUser = getUserById(updatedById);

        String createdByUsername = (createdByUser != null) ? createdByUser.getUserName() : "Unknown";
        String updatedByUsername = (updatedByUser != null) ? updatedByUser.getUserName() : "Unknown";

        // Prepare detailed response with all user attributes
        UserResponse response = UserResponse.builder()
                .userId(existingUser.getUserId())
                .userName(existingUser.getUserName())
                .firstName(existingUser.getFirstName())
                .lastName(existingUser.getLastName())
                .email(existingUser.getEmail())
                .status(existingUser.getStatus())
                .roleName(existingUser.getRoleId() != null ? existingUser.getRoleId().getRoleName() : null)
                .roleId(existingUser.getRoleId() != null ? existingUser.getRoleId().getRoleId() : null)
                .courseId(existingUser.getCourse() != null ? existingUser.getCourse().getCourseId() : null) // Fetch courseId
                .courseName(existingUser.getCourse() != null ? existingUser.getCourse().getCourseName() : null) // Fetch courseName
                .createdBy(createdByUsername)
                .createdDate(existingUser.getCreatedDate())
                .updatedBy(updatedByUsername)
                .updatedDate(existingUser.getUpdatedDate())
                .lastLoggedInTime(existingUser.getLastLoggedInTime())
                .message("User with id '" + userId + "' updated successfully!")
                .build();

        return response;
    }

}
