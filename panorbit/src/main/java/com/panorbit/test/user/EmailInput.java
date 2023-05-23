package com.panorbit.test.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmailInput(@NotNull @NotBlank @Email String email) {

}
