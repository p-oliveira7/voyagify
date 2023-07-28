package voyagify.api.domain.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ReviewInputDTO(
        @NotBlank
        String text ) {

}
