package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String firstName;

    private String secondName;

    private String location;

    private String role;
}
