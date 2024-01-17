package org.alexey.ITBotcamp.core.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.alexey.ITBotcamp.core.entity.UserRole;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class UserCreationDto {

    @NotNull
    @Size(max = 20, message = "The lastname size should not exceed 20 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field accepts Latin characters only")
    private String lastName;

    @NotNull
    @Size(max = 40, message = "The firstname size should not exceed 40 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field accepts Latin characters only")
    private String firstName;

    @Size(max = 40, message = "The patronymic size should not exceed 40 characters")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "The field accepts Latin characters only")
    private String middleName;

    @NotNull
    @Email(message = "Email should be valid")
    @Size(max = 50, message = "The email size should not exceed 50 characters")
    private String email;

    @NotNull
    private UserRole role;
}
