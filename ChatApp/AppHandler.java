import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AppHandler {

    static int msgId = 1;
    List<Message> messages = new ArrayList<>();
    HashMap<String, User> users = new HashMap<>();

    HashMap<String, Group> groups = new HashMap<>();
    boolean validate(String username, String password){
        if(users.containsKey(username)){
            return users.get(username).password.equals(password);
        }
        else return false;
    }
    void addUser(String username, String password){
        if(!users.containsKey(username)){
            users.put(username, new User(username, password));
        }
        else{
            System.out.println("Username already exist!");
        }
    }

    void message(String sender, String receiver, String content){
        if(users.containsKey(receiver)){
            messages.add(new Message(sender, receiver, content, msgId));
            msgId++;
        }
        else{
            System.out.println("Receiver doesn't exist!");
        }
    }

    void deleteMessage(int msgId){
        for (Message msg : messages){
            if(msg.msgId == msgId){
                if(!msg.isDeleted){
                    msg.isDeleted = true;
                    System.out.println("Message deleted!");
                    return;
                }
                else{
                    System.out.println("Message already deleted!");
                    return;
                }
            }
        }
        System.out.println("Invalid message Id!");
    }
    void displayMessage(String username){
        boolean isUserExist = false;
        messages.sort((m1, m2) -> m2.dateTime.compareTo(m1.dateTime));
        for(Message msg: messages){
            if(msg.sender.equals(username) || msg.receiver.equals(username)){
                isUserExist = true;
                if(msg.isDeleted){
                    System.out.println("=============================================================================");
                    System.out.println("MessageId = "+ msg.msgId+ "\n Receiver = "+msg.receiver+ "\n Content = This message was deleted"+"  |  "+msg.dateTime);
                    System.out.println("=============================================================================");
                }
                else{
                    System.out.println("=============================================================================");
                    System.out.println("MessageId = "+ msg.msgId+ "\n Receiver = "+msg.receiver+ "\n Content = "+ msg.content+"  |  "+msg.dateTime);
                    System.out.println("=============================================================================");
                }
            }
        }
        if(!isUserExist){
            System.out.println("User not yet to message!");
        }
    }
    //Groups

    void createGroup(String groupName, String admin){
        if (!groups.containsKey(groupName)){
            groups.put(groupName, new Group(admin));
        }
        else{
            System.out.println(groupName+" already exists!");
        }
    }

    void addUserToGroup(String groupName,String admin, String userName){
        if (groups.containsKey(groupName)){
            Group group = groups.get(groupName);
            if(group.admin.equals(admin)){
                group.members.add(userName);
                System.out.println(admin+" added "+userName);
            }
            else{
                System.out.println("Admin only can add user");
            }
        }
        else{
            System.out.println(groupName+" not exists!");
        }
    }

    void removeUserFromGroup(String groupName, String admin, String userName){
        if (groups.containsKey(groupName)){
            Group group = groups.get(groupName);
            if(group.admin.equals(admin)){
                group.members.remove(userName);
                System.out.println(admin+" removed "+userName);
            }
            else{
                System.out.println("Admin only can remove user");
            }
        }
        else{
            System.out.println(groupName+" not exists!");
        }
    }

    void messageToGroup(String groupName, String sender, String content){
        if (groups.containsKey(groupName)){
            Group group = groups.get(groupName);
            if(group.members.contains(sender)){
                group.messages.add(new GroupMessage(sender,content));
                System.out.println("Message sent successfully" );
            }
            else{
                System.out.println(sender+" not in the group");
            }
        }
        else{
            System.out.println(groupName+" not exists!");
        }
    }

    void displayGroupMessage(String groupName, String username){
        if (groups.containsKey(groupName)){
            Group group = groups.get(groupName);
            if(group.members.contains(username)){
                for(GroupMessage message: group.messages){
                    System.out.println("Sender = "+message.sender+" Content = "+message.content+" | "+message.timestamp);
                    System.out.println("-------------------------------------------------------------");
                }
            }
            else{
                System.out.println(username+" not in the group");
            }
        }
        else{
            System.out.println(groupName+" not exists!");
        }
    }

    void displayGroupMembers(String groupName){
        if (groups.containsKey(groupName)){
            Group group = groups.get(groupName);
            System.out.println("Members List");
            for(String member: group.members){
                System.out.println(member);
            }
        }
        else{
            System.out.println(groupName+" not exists!");
        }
    }

    void deleteGroup(String groupName, String admin){
        if (groups.containsKey(groupName)){
            Group group = groups.get(groupName);
            if(group.admin.equals(admin)){
                groups.remove(groupName);
            }
            else{
                System.out.println(admin+" can't delete the group");
            }
        }
        else{
            System.out.println(groupName+" not exists!");
        }
    }

}
