package org.example.socialse2.dto;

import lombok.*;
import org.example.socialse2.validator.PasswordMatches;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@PasswordMatches(message = "Passwords do not match")
public class RegistrationDto {

    private String firstName;

    private String lastName;

    private String email;

    private String username;

    private String password;

    private String confirmPassword;

}
