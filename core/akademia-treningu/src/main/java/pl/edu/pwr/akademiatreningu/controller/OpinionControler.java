package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.OpinionDto;
import pl.edu.pwr.akademiatreningu.service.OpinionService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class OpinionControler {

    private final OpinionService opinionService;

    @PostMapping("/opinion")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MENTEE')")
    public String getMentee(@RequestBody OpinionDto opinionDto) {
        return opinionService.saveOpinion(opinionDto);
    }

    @GetMapping("/opinion")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MENTEE') or hasRole('ROLE_PERSONAL_TRAINER')")
    public List<OpinionDto> getOpinions(@RequestParam Integer personalTrainerUserId) {
        return opinionService.getOpinions(personalTrainerUserId);
    }
}
