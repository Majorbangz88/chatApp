package chatApp.data.models;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Document("/Charts")
public class Chats {

    @Id
    private String id;
    @DBRef
    private List<Users> participants = new ArrayList<>();
    @CreatedDate
    private LocalDateTime dateCreated = LocalDateTime.now();
    private String  chatName;
    private String  message;

}
