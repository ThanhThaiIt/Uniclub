package com.Tsoft.UniClub.request;


//import lombok.Data;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

//@Data
public record AuthenRequest(
        @NotNull(message = "Do not let Email Null")
                @NotEmpty(message = "Do not let Email empty")
                    @Email(message = "it is not email format")
        String email,
        @NotNull(message = "Do not let password Null")
        @NotEmpty(message = "Do not let password empty")
        String password) {
}
