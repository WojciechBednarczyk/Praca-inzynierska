package pl.edu.pwr.akademiatreningu.security.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import pl.edu.pwr.akademiatreningu.model.ERole;
import pl.edu.pwr.akademiatreningu.model.Mentee;
import pl.edu.pwr.akademiatreningu.model.PersonalTrainer;
import pl.edu.pwr.akademiatreningu.model.User;
import pl.edu.pwr.akademiatreningu.repository.MenteeRepository;
import pl.edu.pwr.akademiatreningu.repository.PersonalTrainerRepository;
import pl.edu.pwr.akademiatreningu.repository.UserRepository;
import pl.edu.pwr.akademiatreningu.security.jwt.JwtUtils;
import pl.edu.pwr.akademiatreningu.security.payload.request.LoginRequest;
import pl.edu.pwr.akademiatreningu.security.payload.request.SignUpRequest;
import pl.edu.pwr.akademiatreningu.security.payload.response.MessageResponse;
import pl.edu.pwr.akademiatreningu.security.payload.response.UserInfoResponse;
import pl.edu.pwr.akademiatreningu.security.services.UserDetailsImpl;

import javax.validation.Valid;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials = "true")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final UserRepository userRepository;

    private final MenteeRepository menteeRepository;

    private final PersonalTrainerRepository personalTrainerRepository;

    private final PasswordEncoder encoder;

    private final JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getLogin(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        ResponseCookie jwtCookie = jwtUtils.generateJwtCookie(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .toList();

        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, jwtCookie.toString())
                .body(new UserInfoResponse(userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        roles.get(0)));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) throws ParseException {
        if (userRepository.existsByLogin(signUpRequest.getLogin())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
        }

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");

        User user = new User(signUpRequest.getFirstName(),
                signUpRequest.getSecondName(),
                signUpRequest.getLogin(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getEmail(),
                dateFormat.parse(signUpRequest.getDateOfBirth()),
                signUpRequest.getLocation(),
                signUpRequest.getRole()
        );
        // Create new user's account

        userRepository.save(user);

        if (user.getRole() == ERole.ROLE_MENTEE) {
            Mentee mentee = Mentee.builder()
                    .user(user)
                    .build();
            menteeRepository.save(mentee);
        } else if (user.getRole() == ERole.ROLE_PERSONAL_TRAINER) {
            PersonalTrainer personalTrainer = PersonalTrainer.builder()
                    .user(user)
                    .build();
            personalTrainerRepository.save(personalTrainer);
        }

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }
}
