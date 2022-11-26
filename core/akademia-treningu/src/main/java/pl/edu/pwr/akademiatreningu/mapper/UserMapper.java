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
import java.time.ZoneId;

@Component
public class UserMapper {

    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public MenteeDto getMenteeDto(Mentee mentee) throws ParseException {
        return MenteeDto.builder()
                .firstName(mentee.getUser().getFirstName())
                .secondName(mentee.getUser().getSecondName())
                .dateOfBirth(dateFormat.parse(mentee.getUser().getDateOfBirth().toString()).toInstant().atZone(ZoneId.systemDefault()).toLocalDate())
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
