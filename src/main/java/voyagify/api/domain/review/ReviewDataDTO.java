package voyagify.api.domain.review;

import jakarta.validation.constraints.NotBlank;

public record ReviewDataDTO(
        @NotBlank
        String text ) {

}
