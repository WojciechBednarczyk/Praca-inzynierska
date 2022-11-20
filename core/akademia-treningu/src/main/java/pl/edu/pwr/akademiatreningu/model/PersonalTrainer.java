package pl.edu.pwr.akademiatreningu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "personal_trainers")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalTrainer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personal_trainer_id")
    private Integer id;

    private Float rating;

    private String description;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "personalTrainer")
    private Set<Mentee> mentees;

    @OneToMany(mappedBy = "personalTrainer")
    private Set<Opinion> opinions;

}
