package pl.edu.pwr.akademiatreningu.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mentees")
@Getter
@Setter
public class Mentee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentee_id")
    private Integer id;

    private Integer weight;

    private Integer height;

    @Column(name = "waist_circumference")
    private Integer waistCircumference;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private PersonalTrainer personalTrainer;

    @OneToMany(mappedBy = "mentee")
    private Set<Opinion> opinions;
}
