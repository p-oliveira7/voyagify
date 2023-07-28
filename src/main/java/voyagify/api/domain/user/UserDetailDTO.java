package voyagify.api.domain.user;

public record UserDetailDTO(

        String name,
        Country country

        ){

    public UserDetailDTO(User user){
        this(user.getName(), user.getCountry());
    }
}
