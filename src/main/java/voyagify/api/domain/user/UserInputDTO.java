package voyagify.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserInputDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotNull
        Country country,
        @NotNull
        Role role,
        // NOTE: Passwords must be securely hashed for JWT authentication.
        @NotBlank
        String password ) {
}
