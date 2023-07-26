package voyagify.api.domain.user;

public enum UserRole {
    USER("user"),
    ADVERTISER("advertiser");

    private String role;

    UserRole(String role){
        this.role =role;
    }

    public String getRole(){
        return role;
    }
}
