import java.time.LocalDateTime;

public class GroupMessage {
    String sender;
    String content;
    LocalDateTime timestamp;

    public GroupMessage(String sender, String content) {
        this.sender = sender;
        this.content = content;
        this.timestamp = LocalDateTime.now();
    }
}
