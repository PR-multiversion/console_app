import Enum.Status;
import Repository.TodoRepo;
import Service.TodoHandler;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        TodoHandler todoHandler = new TodoHandler();
        TodoRepo todoRepo = null;

        int ch1;
        String username = "";
        String password = "";
        while(true){
            System.out.println("1. Login\n2. Sign Up\n3. Exit");
            ch1 = inp.nextInt();
            inp.nextLine();
            switch(ch1){
                case 1:
                    System.out.println("UserName: ");
                    username = inp.nextLine();
                    System.out.println("Password: ");
                    password = inp.nextLine();
                    if(todoHandler.login(username, password)){
                        if(todoRepo == null){
                            todoRepo =TodoRepo.intializeToDoRepo();
                        }
                        todoHandler.setTodos(username,todoRepo);
                        todo(inp, todoHandler, username);
                    }
                    break;
                case 2:
                    System.out.println("UserName: ");
                    username = inp.nextLine();
                    System.out.println("Password: ");
                    password = inp.nextLine();
                    if(todoHandler.signUp(username,password)){
                        if(todoRepo == null){
                            todoRepo =TodoRepo.intializeToDoRepo();
                        }
                        todoHandler.setTodos(username,todoRepo);
                        todo(inp, todoHandler, username);
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void todo(Scanner inp, TodoHandler todoHandler, String userName){
        boolean flag = true;
        while(flag){
            System.out.println("1. Add task\n2. Update task\n3. Delete task\n4. List task\n5. Exit");
            int ch2 = inp.nextInt();
            inp.nextLine();
            switch(ch2){
                case 1:
                    System.out.println("Enter description:");
                    String desc = inp.nextLine();
                    System.out.println("Enter task status:");
                    Status status = status(inp);
                    todoHandler.addTask(userName,desc,status);
                     break;
                case 2:
                    System.out.println("Enter task id");
                    int taskId = inp.nextInt();
                    inp.nextLine();
                    System.out.println("Enter update description:");
                    String updDesc = inp.nextLine();
                    System.out.println("Enter task update status:");
                    Status updateStatus = status(inp);
                    todoHandler.updateTask(userName,taskId,updDesc, updateStatus);
                    break;
                case 3:
                    System.out.println("Enter task id");
                    int deleteId = inp.nextInt();
                    inp.nextLine();
                    todoHandler.deleteTask(userName,deleteId);
                    break;
                case 4:
                    todoHandler.display(userName);
                    break;
                case 5:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice!");

            }
        }
    }

    public static Status status(Scanner inp){

        while(true){
            System.out.println("\t1. NOT_STARTED\n\t2. IN_PROGRESS\n\t3. COMPLETED");
            if (inp.hasNextInt()) {
                int ch = inp.nextInt();

                switch (ch) {
                    case 1:
                        return Status.NOT_STARTED;
                    case 2:
                        return Status.IN_PROGRESS;
                    case 3:
                        return Status.COMPLETED;
                    default:
                        System.out.println("❗ Invalid choice! Please enter a valid option.");
                }
            } else {
                System.out.println("❗ Invalid input. Please enter a number.");
                inp.next(); // Clear invalid input
            }
        }
    }
}
