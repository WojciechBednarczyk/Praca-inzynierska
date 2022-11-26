package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageDto {

    private Integer receiverId;

    private Integer senderId;

    private String message;

    private LocalDate dateOfSent;
}
