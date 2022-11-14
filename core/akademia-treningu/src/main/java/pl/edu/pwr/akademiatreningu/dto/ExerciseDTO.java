package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExerciseDTO {

    private Integer id;

    private String name;

    private String description;

    private String url;

    private Integer rating;

    private String muscleGroup;
}
