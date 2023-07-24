package voyagify.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserInputDTO(
        @NotBlank
        String name,
        @NotBlank
        String email,
        @NotBlank
        String nationality,
        @NotNull
        Role Role,
        // NOTE: Passwords must be securely hashed for JWT authentication.
        @NotBlank
        String password ) {
}
