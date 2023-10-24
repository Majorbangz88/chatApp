package chatApp.dtos.responses;

import lombok.Data;

@Data
public class RegisterUserResponse {

    private String username;
    private String password;
    private String registrationDate;
}
