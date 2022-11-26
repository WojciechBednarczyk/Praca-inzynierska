package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.akademiatreningu.dto.MessageDto;
import pl.edu.pwr.akademiatreningu.mapper.MessageMapper;
import pl.edu.pwr.akademiatreningu.repository.MessageRepository;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;

    private final MessageMapper messageMapper;

    public void saveMessage(MessageDto messageDto) {
        messageDto.setDateOfSent(LocalDate.now());
        messageRepository.save(messageMapper.mapMessageDtoToEntity(messageDto));
    }
}
