package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {

    private Integer weight;

    private Integer height;

    private Integer waistCircumference;

    private Float bodyFat;

    private Integer bicepsCircumference;

    private Integer thighCircumference;

    private Integer chestCircumference;

    private String location;

    private Integer userId;

}
