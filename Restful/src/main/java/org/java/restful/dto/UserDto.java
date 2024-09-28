package org.java.restful.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Schema(
        description = "UserDto Model Information"
)
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @Schema(
            description = "user first name"
    )
    @NotEmpty(message = "User first name should not be null or empty")
    private String name;

    @Schema(
            description = "user last name"
    )
    @NotEmpty(message = "User last name should not be null or empty")
    private String surname;


    @Schema(
            description = "user email"
    )
    @NotEmpty(message = "User email should not be null or empty")
    @Email(message = "Email should be valid")
    private String email;
}
