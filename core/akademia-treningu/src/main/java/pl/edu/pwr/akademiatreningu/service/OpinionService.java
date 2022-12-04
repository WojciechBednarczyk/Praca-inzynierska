package pl.edu.pwr.akademiatreningu.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pwr.akademiatreningu.dto.OpinionDto;
import pl.edu.pwr.akademiatreningu.model.Opinion;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainer;
import pl.edu.pwr.akademiatreningu.model.User;
import pl.edu.pwr.akademiatreningu.repository.OpinionRepository;
import pl.edu.pwr.akademiatreningu.repository.PersonalTrainerRepository;
import pl.edu.pwr.akademiatreningu.repository.UserRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OpinionService {

    private final OpinionRepository opinionRepository;

    private final UserRepository userRepository;

    private final PersonalTrainerRepository personalTrainerRepository;

    public String saveOpinion(OpinionDto opinionDto) {

        User user = userRepository.findById(opinionDto.getUserId()).get();

        if (user.getMentee().getPersonalTrainer() == null || user.getMentee().getPersonalTrainer().getUser().getId() != opinionDto.getPersonalTrainerId()) {
            return "Nie mozesz wystawic opinii dla nie swojego trenera";
        } else if (opinionRepository.findByPersonalTrainerIdAndMenteeId(user.getMentee().getPersonalTrainer().getId(), user.getMentee().getId()).isPresent()) {
            return "Wystawiono juz opinie dla tego trenera";
        } else {
            Opinion opinion = Opinion.builder()
                    .description(opinionDto.getReview())
                    .rating(opinionDto.getRating())
                    .personalTrainer(user.getMentee().getPersonalTrainer())
                    .mentee(user.getMentee())
                    .build();
            opinionRepository.save(opinion);
            Integer count = opinionRepository.countAllByPersonalTrainerId(user.getMentee().getPersonalTrainer().getId());
            List<Opinion> opinions = opinionRepository.findAll();
            Integer sum = 0;
            for (Opinion iterator : opinions) {
                sum += iterator.getRating();
            }
            BigDecimal value = new BigDecimal(sum / (float) count);
            value = value.setScale(1, RoundingMode.HALF_EVEN);
            Float overallRating = value.floatValue();
            PersonalTrainer personalTrainer = user.getMentee().getPersonalTrainer();
            personalTrainer.setRating(overallRating);
            personalTrainerRepository.save(personalTrainer);
            return "Opinia zostala zapisana";
        }
    }
}
