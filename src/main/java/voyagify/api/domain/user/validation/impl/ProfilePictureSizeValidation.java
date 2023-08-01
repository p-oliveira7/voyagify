package voyagify.api.domain.user.validation.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import voyagify.api.domain.exception.FileTooLargeException;
import voyagify.api.domain.user.validation.Validation;

@Service
public class ProfilePictureSizeValidation implements Validation {
    @Value("${max.profile.picture.size}")
    private int maxProfilePictureSize;

    @Override
    public void validate(Long id, MultipartFile file) {
        if (file.getSize() > maxProfilePictureSize) {
            throw new FileTooLargeException("Profile picture is too large");
        }
    }
}