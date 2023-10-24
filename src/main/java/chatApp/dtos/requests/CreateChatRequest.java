package chatApp.dtos.requests;

import lombok.Data;

@Data
public class CreateChatRequest {
    private String firstUser;
    private String secondUser;
}
