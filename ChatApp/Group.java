import java.util.*;

public class Group {
    String admin;
    Set<String> members;
    List<GroupMessage> messages;

    public Group(String admin) {
        this.members = new HashSet<>();
        this.messages = new ArrayList<>();
        this.admin = admin;
        this.members.add(admin);
    }
}
