package voyagify.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import voyagify.api.domain.user.User;
import voyagify.api.domain.user.UserResponseDTO;
import voyagify.api.domain.user.UserRepository;
import voyagify.api.domain.user.UserService;
import voyagify.api.infra.security.SecurityUtils;

import java.io.IOException;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository repository;
    // NOTE: The idea here is to display the account details view and the admin view.
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var user = repository.getReferenceById(id);

        return null;
    }

    @GetMapping("/detail")
    public ResponseEntity<UserResponseDTO> detail(){
        var user = SecurityUtils.getAuthenticatedUser();
        try {
            UserResponseDTO userDetail = userService.userDetail(user);

            return ResponseEntity.ok(userDetail);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    @PostMapping("/profile-picture")
    public ResponseEntity uploadProfilePicture(@RequestParam("file")MultipartFile file){
        var user = SecurityUtils.getAuthenticatedUser();

        userService.uploadProfileImage(user, file);

        return ResponseEntity.ok().build();
    }
}
