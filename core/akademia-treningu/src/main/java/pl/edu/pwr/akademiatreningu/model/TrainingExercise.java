package pl.edu.pwr.akademiatreningu.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "training_exercises")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TrainingExercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "training_exercise_id")
    private Integer id;

    private Integer sets;

    private Integer reps;

    @ManyToOne
    @JoinColumn(name = "training_id", nullable = false)
    private Training training;

    @ManyToOne
    @JoinColumn(name = "exercise_id", nullable = false)
    private Exercise exercise;
}
