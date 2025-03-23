import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        int choice;
        AppHandler appHandler = new AppHandler();
        appHandler.addUser("pr", "123");
        appHandler.addUser("raja", "1234");

        appHandler.createGroup("The Boys", "pattu");
        appHandler.addUserToGroup("The Boys","pattu","pr");
        do{
            System.out.println("1. Login\n2. Exit");
            choice = inp.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter username: ");
                    String username = inp.next();
                    System.out.println("Enter password: ");
                    String password = inp.next();
                    if(appHandler.validate(username,password)){
                        System.out.println("Logged in...");
                        boolean flag = true;
                        while(flag){
                            System.out.println("1. Create User\n2. Message\n3. Delete Message\n4. Display Message\n5. Groups \n6. Exit");
                            choice = inp.nextInt();
                            inp.nextLine();
                            switch (choice){
                                case 1:
                                    System.out.println("Enter username: ");
                                    String newUsername = inp.nextLine();
                                    System.out.println("Enter password");
                                    String newUserPasswd = inp.nextLine();
                                    appHandler.addUser(newUsername,newUserPasswd);
                                    break;
                                case 2:
                                    System.out.println("Enter receiver name: ");
                                    String receiver = inp.nextLine();
                                    System.out.println("Enter message content: ");
                                    String content = inp.nextLine();
                                    appHandler.message(username,receiver,content);
                                    break;
                                case 3:
                                    System.out.println("Enter messageId: ");
                                    int msgId = inp.nextInt();
                                    appHandler.deleteMessage(msgId);
                                    break;
                                case 4:
                                    appHandler.displayMessage(username);
                                    break;
                                case 5:
                                    while(true){
                                        System.out.println("1. Create a new Group\n2. Delete Group\n3. Add User\n4.Remove User\n5. Message\n6. Display message\n7. Display member list\n8. Exit");
                                        choice = inp.nextInt();
                                        inp.nextLine();
                                        switch(choice){
                                            case 1:
                                                System.out.println("Enter a groupName");
                                                String newGroupName = inp.nextLine();
                                                appHandler.createGroup(newGroupName,username);
                                                break;
                                            case 2:
                                                System.out.println("Enter a groupName");
                                                String groupName = inp.nextLine();
                                                appHandler.deleteGroup(groupName,username);
                                                break;
                                            case 3:
                                                System.out.println("Enter a groupName");
                                                String UserGroupName = inp.nextLine();
                                                System.out.println("Enter a userName");
                                                String groupAddUserName = inp.nextLine();
                                                appHandler.addUserToGroup(UserGroupName,username,groupAddUserName);
                                                break;
                                            case 4:
                                                System.out.println("Enter a groupName");
                                                String removeUserGroupName = inp.nextLine();
                                                System.out.println("Enter a userName");
                                                String groupRemoveUserName = inp.nextLine();
                                                appHandler.removeUserFromGroup(removeUserGroupName,username,groupRemoveUserName);
                                                break;
                                            case 5:
                                                System.out.println("Enter a groupName");
                                                String mGroupName = inp.nextLine();
                                                System.out.println("Enter a sender");
                                                String sender = inp.nextLine();
                                                System.out.println("Enter message content");
                                                String gContent = inp.nextLine();
                                                appHandler.messageToGroup(mGroupName,sender,gContent);
                                                break;
                                            case 6:
                                                System.out.println("Enter a groupName");
                                                String dGroupName = inp.nextLine();
                                                System.out.println("Enter userName");
                                                String dSender = inp.nextLine();
                                                appHandler.displayGroupMessage(dGroupName, dSender);
                                                break;
                                            case 7:
                                                System.out.println("Enter a groupName");
                                                String memGroupName = inp.nextLine();
                                                appHandler.displayGroupMembers(memGroupName);
                                                break;
                                            case 8:
                                                break;
                                            default:
                                                System.out.println("Invalid choice!");
                                                break;
                                        }
                                        if (choice == 8) break;
                                    }
                                    break;
                                case 6:
                                    flag = false;
                                    break;
                                default:
                                    System.out.println("Invalid choice!");
                                    break;
                            }
                        }
                    }
                    else{
                        System.out.println("Invalid username or password!");
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }
        while(true);
    }
}
