package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.akademiatreningu.dto.MenteeDto;
import pl.edu.pwr.akademiatreningu.dto.PersonalTrainerDto;
import pl.edu.pwr.akademiatreningu.dto.UserDto;
import pl.edu.pwr.akademiatreningu.mapper.UserMapper;
import pl.edu.pwr.akademiatreningu.model.Mentee;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainer;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainerRequest;
import pl.edu.pwr.akademiatreningu.model.User;
import pl.edu.pwr.akademiatreningu.repository.MenteeRepository;
import pl.edu.pwr.akademiatreningu.repository.PersonalTrainerRepository;
import pl.edu.pwr.akademiatreningu.repository.RequestRepository;
import pl.edu.pwr.akademiatreningu.repository.UserRepository;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final MenteeRepository menteeRepository;

    private final PersonalTrainerRepository personalTrainerRepository;

    private final UserRepository userRepository;

    private final RequestRepository requestRepository;

    private final UserMapper userMapper;


    public MenteeDto getMentee(Integer id) throws ParseException {
        return userMapper.getMenteeDto(menteeRepository.findByUserId(id));
    }

    public PersonalTrainerDto getPersonalTrainer(Integer id) throws ParseException {
        return userMapper.getPersonalTrainerDto(personalTrainerRepository.findByUserId(id));
    }

    public List<UserDto> getSearchedUsers(String firstName, String secondName, String location) {
        List<User> users = userRepository.findByFirstNameOrSecondNameOrLocation(firstName, secondName, location);
        return users.stream()
                .map(userMapper::getUserDto)
                .toList();
    }

    public String sendRequestToPersonalTrainer(Integer menteeUserId, Integer personalTrainerId) {

        Mentee mentee = userRepository.findById(menteeUserId).get().getMentee();
        if (!checkIfMenteeAlreadyHasPersonalTrainer(mentee)) {
            if (!checkIfRequestWasSentBefore(mentee.getId(), personalTrainerId)) {
                requestRepository.save(PersonalTrainerRequest.builder()
                        .personalTrainer(personalTrainerRepository.findByUserId(personalTrainerId))
                        .mentee(mentee)
                        .build());
                return "Wyslano prosbe do trenera";
            } else {
                return "Prosba do trenera zostala juz wyslana wczesniej";
            }
        } else {
            return "Masz juz trenera";
        }
    }

    private boolean checkIfMenteeAlreadyHasPersonalTrainer(Mentee mentee) {
        if (mentee.getPersonalTrainer() == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean checkIfRequestWasSentBefore(Integer menteeUserId, Integer personalTrainerUserId) {
        PersonalTrainer personalTrainer = personalTrainerRepository.findByUserId(personalTrainerUserId);
        Optional<PersonalTrainerRequest> request = requestRepository.findByPersonalTrainerIdAndMenteeId(personalTrainer.getId(), menteeUserId);
        if (request.isPresent()) {
            return true;
        } else {
            return false;
        }
    }
}
