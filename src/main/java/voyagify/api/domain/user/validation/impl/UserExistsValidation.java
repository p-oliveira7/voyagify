package voyagify.api.domain.user.validation.impl;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import voyagify.api.domain.user.UserRepository;
import voyagify.api.domain.user.validation.Validation;

@Service
public class UserExistsValidation implements Validation {
    @Autowired
    private UserRepository userRepository;

    @Override
    public void validate(Long id, MultipartFile file) {
        if (!userRepository.existsById(id)) {
            throw new ValidationException("User not found");
        }
    }
}