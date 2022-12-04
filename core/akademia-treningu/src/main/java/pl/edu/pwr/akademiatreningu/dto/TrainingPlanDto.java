package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TrainingPlanDto {

    private List<TrainingExerciseDto> trainingPlan;

    private Integer userId;

    private LocalDate dateOfCreate;

}
