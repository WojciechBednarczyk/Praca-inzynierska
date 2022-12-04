package pl.edu.pwr.akademiatreningu.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.MessageDto;
import pl.edu.pwr.akademiatreningu.dto.MessageWithSenderDto;
import pl.edu.pwr.akademiatreningu.model.Message;
import pl.edu.pwr.akademiatreningu.repository.UserRepository;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MessageMapper {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

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

    public List<MessageWithSenderDto> mapMessagesToDtoWithSender(List<Message> messages) {
        return messages.stream()
                .map(this::convertToDto)
                .toList();
    }

    private MessageWithSenderDto convertToDto(Message message) {
        LocalDate dateOfSent = null;

        try {
            dateOfSent = dateFormat.parse(message.getDateOfSent().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return MessageWithSenderDto.builder()
                .senderFirstName(message.getUserSender().getFirstName())
                .senderSecondName(message.getUserSender().getSecondName())
                .senderId(message.getUserSender().getId())
                .message(message.getMessage())
                .dateOfSent(dateOfSent)
                .build();

    }
}
