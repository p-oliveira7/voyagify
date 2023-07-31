package voyagify.api.infra.security;

import org.springframework.security.core.context.SecurityContextHolder;
import voyagify.api.domain.user.User;

public class SecurityUtils {
    public static Long getAuthenticatedUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            var user = (User) principal;
            return user.getId();
        } else {
            // handle unexpected principal class
            throw new IllegalStateException("Unexpected principal class: " + principal.getClass());
        }
    }
}
