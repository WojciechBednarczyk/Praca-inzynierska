package pl.edu.pwr.akademiatreningu.dto;

import lombok.*;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageWithSenderDto {

    private String senderFirstName;

    private String senderSecondName;

    private Integer senderId;

    private LocalDate dateOfSent;

    private String message;

}
