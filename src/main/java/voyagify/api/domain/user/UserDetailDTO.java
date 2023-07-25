package voyagify.api.domain.user;

public record UserDetailDTO(
        Long id,
        String name,

        String email,

        Country country,

        Role role,

        String password ){

    public UserDetailDTO(User user){
        this(user.getId(), user.getName(), user.getEmail(), user.getCountry(), user.getRole(), user.getPassword());
    }
}
