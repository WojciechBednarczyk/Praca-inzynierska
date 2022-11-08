package pl.edu.pwr.akademiatreningu.security.payload.request;

import lombok.Getter;
import lombok.Setter;
import pl.edu.pwr.akademiatreningu.model.ERole;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SignUpRequest {

    @NotBlank
    @Size(min = 3, max = 20)
    private String login;

    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    private ERole role;

    @NotBlank
    @Size(min = 6)
    private String password;

    @NotBlank
    private String firstName;

    @NotBlank
    private String secondName;

    @NotBlank
    private String dateOfBirth;

    private String location;
}
