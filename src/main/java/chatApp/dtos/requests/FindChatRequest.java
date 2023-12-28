package chatApp.dtos.requests;

import chatApp.data.models.Users;
import lombok.Data;

import java.util.List;

@Data
public class FindChatRequest {

    private String firstChatName;
    private String secondChatName;
    private List<Users> participant;
}
