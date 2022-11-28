package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalTrainerRequestDto {

    private Integer requestId;

    private String firstName;

    private String secondName;

    private Integer menteeId;
}
