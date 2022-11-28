package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.PersonalTrainerRequestDto;
import pl.edu.pwr.akademiatreningu.service.UserService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class PersonalTrainerController {

    private final UserService userService;

    @PostMapping("/personal-trainer/request")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MENTEE')")
    public String getMentee(@RequestParam Integer menteeId, @RequestParam Integer personalTrainerId) {
        return userService.sendRequestToPersonalTrainer(menteeId, personalTrainerId);
    }

    @GetMapping("/personal-trainer/request")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_PERSONAL_TRAINER')")
    public List<PersonalTrainerRequestDto> getMenteesRequests(@RequestParam Integer personalTrainerId) {
        return userService.getMenteesRequests(personalTrainerId);
    }
}
