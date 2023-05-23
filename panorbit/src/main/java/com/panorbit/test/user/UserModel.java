package com.panorbit.test.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserModel(@NotNull @NotBlank @NotEmpty String firstName, @NotNull @NotBlank @NotEmpty String lastName, Gender gender, @Email String email, @Size(min = 10, max = 10) String phone) {

}
