package com.amirscode.clickup.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {
    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String password;

}
