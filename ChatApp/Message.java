import java.time.LocalDateTime;

public class Message {
    String sender;
    String receiver;
    String content;

    int msgId;

    LocalDateTime dateTime;
    boolean isDeleted;
    public Message(String sender, String receiver, String content, int msgId) {
        this.msgId = msgId;
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        dateTime = LocalDateTime.now();
        this.isDeleted = false;
    }
}
