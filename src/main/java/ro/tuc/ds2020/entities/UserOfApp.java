package ro.tuc.ds2020.entities;

import lombok.*;
import org.apache.catalina.Role;
import org.apache.catalina.User;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class UserOfApp {

    private static final long serialVersionUID =1L;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "uuid-binary")

    private UUID id;

    @Column(name="name")
    private String name;

    @Column(name="email")
    private String email;

    @Column(name="password")
    private String password;

    @Column(name="role")
    @Enumerated(EnumType.ORDINAL)
    private RoleEnum role;

    @OneToMany(mappedBy = "userOfApp",cascade = CascadeType.ALL)
    private List<Device> devices;

    public UserOfApp(String name, String email, String password, RoleEnum role) {
        this.name=name;
        this.email=email;
        this.password=password;
        this.role=role;
    }
}
