package voyagify.api.domain.user;

import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import voyagify.api.domain.exception.FileTooLargeException;
import voyagify.api.domain.exception.InvalidFileException;

import java.io.File;
import java.io.IOException;

import static java.nio.file.Files.readAllBytes;


@Service
public class UserService {
    @Value("${image.path}")
    private String imagePath;
    @Value("${max.profile.picture.size}")
    private int maxProfilePictureSize;
    @Autowired
    private UserRepository userRepository;

    public void uploadProfileImage(Long id, MultipartFile file) {
        if (!userRepository.existsById(id)) {
            throw new ValidationException("User not found");
        }
        if (file.getSize() > maxProfilePictureSize) {
            throw new FileTooLargeException("Profile picture is too large");
        }
        if (!MediaType.IMAGE_JPEG.isCompatibleWith(MediaType.parseMediaType(file.getContentType())) &&
                !MediaType.IMAGE_PNG.isCompatibleWith(MediaType.parseMediaType(file.getContentType()))) {
            throw new InvalidFileException("Invalid file type. Only image files (JPEG, PNG) are allowed.");
        }
        try {
            String filePath=imagePath+id+file.getOriginalFilename();

            var user =userRepository.findUserById(id);

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
