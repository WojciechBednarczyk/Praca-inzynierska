package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.service.UserService;

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
}
