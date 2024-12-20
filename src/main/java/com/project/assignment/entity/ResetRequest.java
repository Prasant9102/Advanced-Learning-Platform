package com.project.assignment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResetRequest implements Serializable {


    private static final long serialVersionUID = 1L;

    private String token;

    private String password;

}
