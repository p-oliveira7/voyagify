package voyagify.api.domain.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewInputDTO(
        @NotNull
        Long idUser,
        @NotBlank
        String text ) {

}
