package voyagify.api.domain.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "users")
@Entity(name = "User")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @Enumerated(EnumType.STRING)
    private Country country;

    @Column(name = "user_type")
    @Enumerated(EnumType.STRING)
    private Role role;

    private String password;

    public User(UserInputDTO dto) {
        this.name = dto.name();
        this.email = dto.email();
        this.country = dto.country();
        this.role = dto.Role();
        this.password = dto.password();
    }
}