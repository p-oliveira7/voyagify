package voyagify.api.domain.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterDTO(
        @NotBlank
        String name,
        @NotBlank
        @Email
        String email,
        @NotNull
        Country country,
        @NotNull
        UserRole userRole,
        // NOTE: Passwords must be securely hashed for JWT authentication.
        @NotBlank
        String password) {
}
