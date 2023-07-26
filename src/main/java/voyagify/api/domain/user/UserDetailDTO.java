package voyagify.api.domain.user;

public record UserDetailDTO(

        String name,

        String email,

        Country country,

        UserRole userRole){

    public UserDetailDTO(User user){
        this(user.getName(), user.getEmail(), user.getCountry(), user.getUserRole());
    }
}
