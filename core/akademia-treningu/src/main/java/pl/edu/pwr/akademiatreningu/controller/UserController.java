package pl.edu.pwr.akademiatreningu.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.dto.MenteeDto;
import pl.edu.pwr.akademiatreningu.dto.PersonalTrainerDto;
import pl.edu.pwr.akademiatreningu.dto.ProfileDto;
import pl.edu.pwr.akademiatreningu.dto.UserDto;
import pl.edu.pwr.akademiatreningu.service.UserService;

import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RequiredArgsConstructor
@RequestMapping(value = "/api/auth")
public class UserController {


    private final UserService userService;

    @GetMapping("/get/mentee")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MENTEE') or hasRole('ROLE_PERSONAL_TRAINER')")
    public MenteeDto getMentee(@RequestParam Integer id) throws ParseException {
        return userService.getMentee(id);
    }

    @GetMapping("/get/personal-trainer")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MENTEE') or hasRole('ROLE_PERSONAL_TRAINER')")
    public PersonalTrainerDto getPersonalTrainer(@RequestParam Integer id) throws ParseException {
        return userService.getPersonalTrainer(id);
    }

    @GetMapping("/get/search")
    @ResponseBody
    @PreAuthorize("hasRole('ROLE_MENTEE') or hasRole('ROLE_PERSONAL_TRAINER')")
    public List<UserDto> getPersonalTrainer(@RequestParam String firstName, @RequestParam String secondName, @RequestParam String location) throws ParseException {
        return userService.getSearchedUsers(firstName, secondName, location);
    }

    @PostMapping("/user/profile")
    @PreAuthorize("hasRole('ROLE_MENTEE') or hasRole('ROLE_PERSONAL_TRAINER')")
    public void updateProfile(@RequestBody ProfileDto profileDto) {
        userService.updateProfile(profileDto);
    }
}
