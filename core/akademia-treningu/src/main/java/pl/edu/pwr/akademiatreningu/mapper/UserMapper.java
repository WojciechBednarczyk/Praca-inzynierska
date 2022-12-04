package pl.edu.pwr.akademiatreningu.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.MenteeDto;
import pl.edu.pwr.akademiatreningu.dto.PersonalTrainerDto;
import pl.edu.pwr.akademiatreningu.dto.UserDto;
import pl.edu.pwr.akademiatreningu.model.ERole;
import pl.edu.pwr.akademiatreningu.model.Mentee;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainer;
import pl.edu.pwr.akademiatreningu.model.User;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Component
public class UserMapper {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public List<MenteeDto> mapMenteesToDto(List<Mentee> mentees) {
        return mentees.stream()
                .map(this::convertToDto)
                .toList();
    }

    public MenteeDto convertToDto(Mentee mentee) {
        LocalDate dateOfBirth = null;
        try {
            dateOfBirth = dateFormat.parse(mentee.getUser().getDateOfBirth().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        } catch (ParseException exception) {
            exception.printStackTrace();
        }
        return MenteeDto.builder()
                .firstName(mentee.getUser().getFirstName())
                .secondName(mentee.getUser().getSecondName())
                .dateOfBirth(dateOfBirth)
                .location(mentee.getUser().getLocation())
                .weight(mentee.getWeight())
                .height(mentee.getHeight())
                .waistCircumference(mentee.getWaistCircumference())
                .bodyFat(mentee.getBodyFat())
                .bicepsCircumference(mentee.getBicepsCircumference())
                .thighCircumference(mentee.getThighCircumference())
                .chestCircumference(mentee.getChestCircumference())
                .userId(mentee.getUser().getId())
                .build();
    }

    public PersonalTrainerDto getPersonalTrainerDto(PersonalTrainer personalTrainer) throws ParseException {
        return PersonalTrainerDto.builder()
                .firstName(personalTrainer.getUser().getFirstName())
                .secondName(personalTrainer.getUser().getSecondName())
                .dateOfBirth(dateFormat.parse(personalTrainer.getUser().getDateOfBirth().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
                .location(personalTrainer.getUser().getLocation())
                .rating(personalTrainer.getRating())
                .description(personalTrainer.getDescription())
                .userId(personalTrainer.getUser().getId())
                .build();
    }

    public UserDto getUserDto(User user) {
        String role = "";
        if (user.getRole().equals(ERole.ROLE_MENTEE)) {
            role = "Podopieczny";
        } else if (user.getRole().equals(ERole.ROLE_PERSONAL_TRAINER)) {
            role = "Trener";
        }
        return UserDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .secondName(user.getSecondName())
                .location(user.getLocation())
                .role(role)
                .build();
    }
}
