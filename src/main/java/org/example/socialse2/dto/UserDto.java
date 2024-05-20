package org.example.socialse2.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String username;

    private String email;

    private String password;

    private byte[] profileImage;

    private MultipartFile imageFile;

    private String bio;

    private String website;

    private Date dateOfBirth;

}
