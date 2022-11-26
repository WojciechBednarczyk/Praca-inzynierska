package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MenteeDto {

    private String firstName;

    private String secondName;

    private LocalDate dateOfBirth;

    private String location;

    private Integer weight;

    private Integer height;

    private Integer waistCircumference;

    private Float bodyFat;

    private Integer bicepsCircumference;

    private Integer thighCircumference;

    private Integer chestCircumference;

    private Integer userId;

}
