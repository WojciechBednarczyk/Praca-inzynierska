package pl.edu.pwr.akademiatreningu.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "mentees")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Mentee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mentee_id")
    private Integer id;

    private Integer weight;

    private Integer height;

    @Column(name = "waist_circumference")
    private Integer waistCircumference;

    @Column(name = "body_fat")
    private Float bodyFat;

    @Column(name = "biceps_circumference")
    private Integer bicepsCircumference;

    @Column(name = "thigh_circumference")
    private Integer thighCircumference;

    @Column(name = "chest_circumference")
    private Integer chestCircumference;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trainer_id")
    private PersonalTrainer personalTrainer;

    @OneToMany(mappedBy = "mentee")
    private Set<Opinion> opinions;

}
