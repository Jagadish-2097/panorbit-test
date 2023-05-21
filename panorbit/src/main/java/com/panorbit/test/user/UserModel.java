package com.panorbit.test.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserModel(@NotNull @NotBlank @NotEmpty String firstName, @NotNull @NotBlank @NotEmpty String lastName, @NotNull @NotBlank @NotEmpty Gender gender, @Email String email, @Min(value = 10) @Max(10) String phone) {

}
