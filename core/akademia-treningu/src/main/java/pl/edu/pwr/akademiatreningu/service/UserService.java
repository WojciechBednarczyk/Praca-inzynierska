package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.akademiatreningu.dto.MenteeDto;
import pl.edu.pwr.akademiatreningu.dto.PersonalTrainerDto;
import pl.edu.pwr.akademiatreningu.mapper.UserMapper;
import pl.edu.pwr.akademiatreningu.repository.MenteeRepository;
import pl.edu.pwr.akademiatreningu.repository.PersonalTrainerRepository;

import java.text.ParseException;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MenteeRepository menteeRepository;

    private final PersonalTrainerRepository personalTrainerRepository;

    private final UserMapper userMapper;


    public MenteeDto getMentee(Integer id) throws ParseException {
        return userMapper.getMenteeDto(menteeRepository.findByUserId(id));
    }

    public PersonalTrainerDto getPersonalTrainer(Integer id) throws ParseException {
        return userMapper.getPersonalTrainerDto(personalTrainerRepository.findByUserId(id));
    }
}
