package voyagify.api.domain.user;

public record UserResponseDTO(
        String name,
        String email,
        Country country,
        byte[] image

        ){}

