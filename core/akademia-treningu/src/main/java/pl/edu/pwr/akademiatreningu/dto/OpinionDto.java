package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OpinionDto {

    private String review;

    private Integer rating;

    private Integer personalTrainerId;

    private Integer userId;
}
