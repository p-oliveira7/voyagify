package voyagify.api.domain.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import voyagify.api.infra.security.TokenService;
@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    public String getUserEmailFromToken(String authHeader) {
        if(authHeader == null) return null;
        var token = authHeader.replace("Bearer ", "");
        var email = tokenService.validateToken(token);
        return email;
    }
}
