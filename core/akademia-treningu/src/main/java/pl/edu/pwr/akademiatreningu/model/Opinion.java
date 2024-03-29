package pl.edu.pwr.akademiatreningu.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "opinions")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Opinion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "opinion_id")
    private Integer id;

    private String title;

    private String description;

    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @ManyToOne
    @JoinColumn(name = "personal_trainer_id")
    private PersonalTrainer personalTrainer;

}
