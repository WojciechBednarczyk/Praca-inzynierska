package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PersonalTrainerDto {

    private String firstName;

    private String secondName;

    private LocalDate dateOfBirth;

    private String location;

    private Float rating;

    private String description;

}
