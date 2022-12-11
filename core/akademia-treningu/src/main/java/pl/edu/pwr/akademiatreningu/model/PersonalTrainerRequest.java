package pl.edu.pwr.akademiatreningu.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "requests")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalTrainerRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "mentee_id")
    private Mentee mentee;

    @ManyToOne
    @JoinColumn(name = "personal_trainer_id")
    private PersonalTrainer personalTrainer;
}
