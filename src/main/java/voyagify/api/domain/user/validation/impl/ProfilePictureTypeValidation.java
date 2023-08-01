package voyagify.api.domain.user.validation.impl;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import voyagify.api.domain.exception.InvalidFileException;
import voyagify.api.domain.user.validation.Validation;

@Service
public class ProfilePictureTypeValidation implements Validation {
    @Override
    public void validate(Long id, MultipartFile file) {
        if (!MediaType.IMAGE_JPEG.isCompatibleWith(MediaType.parseMediaType(file.getContentType())) &&
                !MediaType.IMAGE_PNG.isCompatibleWith(MediaType.parseMediaType(file.getContentType()))) {
            throw new InvalidFileException("Invalid file type. Only image files (JPEG, PNG) are allowed.");
        }
    }
}
