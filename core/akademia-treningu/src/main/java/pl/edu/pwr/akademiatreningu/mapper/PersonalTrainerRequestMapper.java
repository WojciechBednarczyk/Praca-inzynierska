package pl.edu.pwr.akademiatreningu.mapper;

import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.PersonalTrainerRequestDto;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainerRequest;

import java.util.List;

@Component
public class PersonalTrainerRequestMapper {
    public List<PersonalTrainerRequestDto> getPersonalTrainerRequestsDto(List<PersonalTrainerRequest> personalTrainerRequests) {
        return personalTrainerRequests.stream()
                .map(this::converToDto)
                .toList();
    }

    private PersonalTrainerRequestDto converToDto(PersonalTrainerRequest personalTrainerRequest) {
        return PersonalTrainerRequestDto.builder()
                .requestId(personalTrainerRequest.getId())
                .firstName(personalTrainerRequest.getMentee().getUser().getFirstName())
                .secondName(personalTrainerRequest.getMentee().getUser().getSecondName())
                .menteeId(personalTrainerRequest.getMentee().getUser().getId())
                .build();
    }
}
