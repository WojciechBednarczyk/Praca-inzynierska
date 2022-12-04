package pl.edu.pwr.akademiatreningu.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import pl.edu.pwr.akademiatreningu.dto.OpinionDto;
import pl.edu.pwr.akademiatreningu.model.Opinion;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OpinionMapper {
    public List<OpinionDto> mapOpinionsToDto(List<Opinion> opinions) {
        return opinions.stream()
                .map(this::convertToDto)
                .toList();
    }

    private OpinionDto convertToDto(Opinion opinion) {
        return OpinionDto.builder()
                .review(opinion.getDescription())
                .rating(opinion.getRating())
                .build();
    }
}
