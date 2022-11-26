package pl.edu.pwr.akademiatreningu.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.MessageDto;
import pl.edu.pwr.akademiatreningu.model.Message;
import pl.edu.pwr.akademiatreningu.repository.UserRepository;

import java.time.ZoneId;
import java.util.Date;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    private final UserRepository userRepository;

    public Message mapMessageDtoToEntity(MessageDto messageDto) {
        return convertToEntity(messageDto);
    }

    private Message convertToEntity(MessageDto messageDto) {
        return Message.builder()
                .userReceiver(userRepository.findById(messageDto.getReceiverId()).orElseThrow())
                .userSender(userRepository.findById(messageDto.getSenderId()).orElseThrow())
                .message(messageDto.getMessage())
                .dateOfSent(Date.from(messageDto.getDateOfSent().atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
    }
}
