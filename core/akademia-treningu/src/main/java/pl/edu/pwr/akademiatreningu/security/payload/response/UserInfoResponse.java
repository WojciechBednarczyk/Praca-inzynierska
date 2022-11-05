package pl.edu.pwr.akademiatreningu.security.payload.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserInfoResponse {

    private Integer id;
    private String username;
    private String email;
    private String role;

    public UserInfoResponse(Integer id, String username, String email, String role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.role = role;
    }
}
