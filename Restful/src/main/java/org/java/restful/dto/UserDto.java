package org.java.restful.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotEmpty(message = "User first name should not be null or empty")
    private String name;

    @NotEmpty(message = "User last name should not be null or empty")
    private String surname;

    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email should be valid")
    private String email;
}
