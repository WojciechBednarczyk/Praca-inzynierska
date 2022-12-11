package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.akademiatreningu.dto.MessageDto;
import pl.edu.pwr.akademiatreningu.dto.MessageWithSenderDto;
import pl.edu.pwr.akademiatreningu.mapper.MessageMapper;
import pl.edu.pwr.akademiatreningu.model.Message;
import pl.edu.pwr.akademiatreningu.repository.MessageRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    public Message saveMessage(MessageDto messageDto) {
        messageDto.setDateOfSent(LocalDate.now());
        Message message = messageMapper.mapMessageDtoToEntity(messageDto);
        return messageRepository.save(message);
    }

    public List<MessageWithSenderDto> getUserMessages(Integer userId) {
        List<Message> messages = messageRepository.findByUserReceiverId(userId);
        return messageMapper.mapMessagesToDtoWithSender(messages);
    }
}
