package voyagify.api.domain.user;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import voyagify.api.domain.exception.FileTooLargeException;
import voyagify.api.domain.exception.InvalidFileException;
import voyagify.api.domain.user.validation.Validation;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.nio.file.Files.readAllBytes;


@Service
public class UserService {
    @Value("${image.path}")
    private String imagePath;
    @Value("${max.profile.picture.size}")
    private int maxProfilePictureSize;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private List<Validation> validations;

    public void uploadProfileImage(Long id, MultipartFile file) {

        // NOTE: Execute all validations for the profile picture by iterating over the list of Validation objects.
        validations.forEach(v ->v.validate(id, file));

        try {
            String filePath=imagePath+id+file.getOriginalFilename();

            var user =userRepository.findUserById(id);

            if (user.getImagePath() != null && !user.getImagePath().isEmpty()) {
                // NOTE: Delete the old image if it exists.
                new File(user.getImagePath()).delete();
            }

            user.setImagePath(filePath);

            userRepository.save(user);

            file.transferTo(new File(filePath));

        } catch (IOException e) {
            throw new RuntimeException("Error updating profile picture", e);
        }
    }

    public byte[] downloadImageFromFileSystem(String filePath) throws IOException {
        if (filePath == null || filePath.isEmpty()) {
            return null;
        } else {
            byte[] image = readAllBytes(new File(filePath).toPath());
            return image;
        }
    }
    public UserResponseDTO userDetail(Long id) throws IOException {
       var user = userRepository.findUserById(id);

       var filePath = user.getImagePath();
       byte[] image = downloadImageFromFileSystem(filePath);
       return new UserResponseDTO(user.getName(), user.getEmail(), user.getCountry(), image);
    }
}
