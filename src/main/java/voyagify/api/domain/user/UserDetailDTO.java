package voyagify.api.domain.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserDetailDTO(
        Long id,
        String name,

        String email,

        Country country,

        Role Role,

        String password ){

    public UserDetailDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getCountry(), user.getRole(), user.getPassword());
    }
}
