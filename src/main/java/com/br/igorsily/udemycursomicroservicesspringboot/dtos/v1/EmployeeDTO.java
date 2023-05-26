package com.br.igorsily.udemycursomicroservicesspringboot.dtos.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class EmployeeDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @Email
    @NotNull
    private String email;

    @NotNull
    private String department;

}