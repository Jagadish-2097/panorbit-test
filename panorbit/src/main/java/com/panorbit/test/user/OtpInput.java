package com.panorbit.test.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;

public record OtpInput(@Min(1) Integer otp, @Email String email) {

}
