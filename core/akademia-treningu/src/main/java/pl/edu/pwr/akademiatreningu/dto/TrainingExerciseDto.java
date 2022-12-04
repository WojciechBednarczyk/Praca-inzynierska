package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingExerciseDto {

    private Integer exerciseId;

    private String nameOfExercise;

    private String muscleGroup;

    private Integer sets;

    private Integer reps;
}
