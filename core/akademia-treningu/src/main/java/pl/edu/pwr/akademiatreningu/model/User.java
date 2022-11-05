package pl.edu.pwr.akademiatreningu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "second_name")
    private String secondName;

    private String login;

    private String password;

    private String email;

    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    private String location;

    @Enumerated(EnumType.STRING)
    private ERole role;

    public User(String firstName, String secondName, String login, String password, String email, Date dateOfBirth, String location, ERole role) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.login = login;
        this.password = password;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.location = location;
        this.role = role;
    }

    public User() {

    }
}
