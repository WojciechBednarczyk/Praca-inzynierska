package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DescriptionDto {

    private String description;

    private Integer userId;
}
