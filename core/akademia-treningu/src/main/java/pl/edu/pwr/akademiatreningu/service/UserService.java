package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.pwr.akademiatreningu.dto.*;
import pl.edu.pwr.akademiatreningu.mapper.PersonalTrainerRequestMapper;
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

    private final PersonalTrainerRequestMapper personalTrainerRequestMapper;


    public MenteeDto getMentee(Integer id) throws ParseException {
        return userMapper.convertToDto(menteeRepository.findByUserId(id));
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

    public List<MenteeDto> getPersonalTrainerMentees(Integer userId) throws ParseException {
        Integer personalTrainerId = personalTrainerRepository.findByUserId(userId).getId();
        List<Mentee> mentees = menteeRepository.findByPersonalTrainerId(personalTrainerId);
        return userMapper.mapMenteesToDto(mentees);
    }

    @Transactional
    public void acceptMentee(Integer requestId) {
        PersonalTrainerRequest request = requestRepository.findById(requestId).get();
        PersonalTrainer personalTrainer = personalTrainerRepository.findById(request.getPersonalTrainer().getId()).get();
        Mentee mentee = menteeRepository.findById(request.getMentee().getId()).get();
        mentee.setPersonalTrainer(personalTrainer);
        menteeRepository.save(mentee);
        requestRepository.deleteAllByMenteeId(mentee.getId());
    }

    @Transactional
    public void rejectMentee(Integer requestId) {
        PersonalTrainerRequest request = requestRepository.findById(requestId).get();
        requestRepository.deleteByMenteeIdAndPersonalTrainerId(request.getMentee().getId(), request.getPersonalTrainer().getId());
    }

    public List<PersonalTrainerRequestDto> getMenteesRequests(Integer personalTrainerId) {
        User user = userRepository.findById(personalTrainerId).get();
        List<PersonalTrainerRequest> personalTrainerRequests = requestRepository.findByPersonalTrainerId(user.getPersonalTrainer().getId());
        return personalTrainerRequestMapper.getPersonalTrainerRequestsDto(personalTrainerRequests);
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

    public void setPersonalTrainerDescription(DescriptionDto descriptionDto) {
        PersonalTrainer personalTrainer = userRepository.findById(descriptionDto.getUserId())
                .get()
                .getPersonalTrainer();
        personalTrainer.setDescription(descriptionDto.getDescription());
        personalTrainerRepository.save(personalTrainer);
    }

    public void updateProfile(ProfileDto profileDto) {
        User user = userRepository.findById(profileDto.getUserId()).get();
        if (user.getMentee() != null) {
            Mentee mentee = user.getMentee();
            if (profileDto.getWeight() != null) {
                mentee.setWeight(profileDto.getWeight());
            }
            if (profileDto.getHeight() != null) {
                mentee.setHeight(profileDto.getHeight());
            }
            if (profileDto.getBodyFat() != null) {
                mentee.setBodyFat(profileDto.getBodyFat());
            }
            if (profileDto.getWaistCircumference() != null) {
                mentee.setWaistCircumference(profileDto.getWaistCircumference());
            }
            if (profileDto.getBicepsCircumference() != null) {
                mentee.setBicepsCircumference(profileDto.getBicepsCircumference());
            }
            if (profileDto.getThighCircumference() != null) {
                mentee.setThighCircumference(profileDto.getThighCircumference());
            }
            if (profileDto.getChestCircumference() != null) {
                mentee.setChestCircumference(profileDto.getChestCircumference());
            }
            menteeRepository.save(mentee);
        }
        if (profileDto.getLocation() != null) {
            user.setLocation(profileDto.getLocation());
            userRepository.save(user);
        }
    }
}
