package chatApp.dtos.requests;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String username;
    private String password;
}
