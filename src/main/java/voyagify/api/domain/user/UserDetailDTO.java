package voyagify.api.domain.user;

public record UserDetailDTO(
        Long id,
        String name,
        String email,
        Country country

        ){}

