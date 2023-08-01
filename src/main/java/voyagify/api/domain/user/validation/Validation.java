package voyagify.api.domain.user.validation;

import org.springframework.web.multipart.MultipartFile;

public interface Validation {
    void validate(Long id, MultipartFile file);
}
