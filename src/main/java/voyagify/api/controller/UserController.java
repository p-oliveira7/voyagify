package voyagify.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import voyagify.api.domain.user.User;
import voyagify.api.domain.user.UserDetailDTO;
import voyagify.api.domain.user.UserInputDTO;
import voyagify.api.domain.user.UserRepository;

@RestController
@RequestMapping("users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid UserInputDTO dto, UriComponentsBuilder uriBuilder){
        var user = new User(dto);

        repository.save(user);
        var uri = uriBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(uri).body(new UserDetailDTO(user));
    }
    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var user = repository.getReferenceById(id);

        return ResponseEntity.ok(new UserDetailDTO((User) user));
    }
}
