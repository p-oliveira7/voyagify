package voyagify.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import voyagify.api.domain.user.User;
import voyagify.api.domain.user.UserDetailDTO;
import voyagify.api.domain.user.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;
    // NOTE: The idea here is to display the account details view and the admin view.
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var user = repository.getReferenceById(id);

        return ResponseEntity.ok(new UserDetailDTO((User) user));
    }
}
