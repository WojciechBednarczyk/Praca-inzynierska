package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingExerciseDto {

    private Integer exerciseId;

    private Integer sets;

    private Integer reps;
}
