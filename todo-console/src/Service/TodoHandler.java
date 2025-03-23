package Service;

import Enum.Status;
import Entity.ToDo;
import Repository.TodoRepo;
import Repository.UserRepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TodoHandler {

    private int taskId = 1;
    Map<String, String> users = new HashMap<>();
    Map<String, ArrayList<ToDo>> todos = new HashMap<>();

    TodoRepo todoRepo = null;
    UserRepo userRepo = null;
    public TodoHandler(){
        todoRepo = TodoRepo.intializeToDoRepo();
        userRepo = UserRepo.initializeUserRepo();
        setUsers();
    }

    public void setUsers(){
        users = userRepo.getUsersFromFile();
    }
    public void setTodos(String userName,TodoRepo todoRepo){
        todos.put(userName,todoRepo.getToDo(userName));
        ArrayList<ToDo> lst = todos.get(userName);
        int index = lst.size() - 1;
        if(index >= 0){
            ToDo todo = lst.get(index);
            taskId = todo.getId() + 1;
        }
        else taskId = 1;
    }
    public boolean signUp(String userName, String password){
        if(userName.isEmpty() || password.isEmpty()){
            System.out.println("Username or password can't be empty!");
            return false;
        }
        else if(users.containsKey(userName)){
            System.out.println("UserName already exists!");
            return false;
        }
        else{
            String encryptPasswd = encrypt(password.trim());
            users.put(userName.trim(),encryptPasswd);
            System.out.println(userName + " User created successfully!");
            userRepo.saveUserToFile(userName,encryptPasswd);
            return true;
        }
    }
    public boolean login(String userName, String password){
        if(userName.isEmpty() || password.isEmpty()){
            System.out.println("Username or password can't be empty!");
            return false;
        }
        else if(users.containsKey(userName)){
            if(!users.get(userName).equals(encrypt(password.trim()))){
                System.out.println("Invalid password");
                return false;
            }
            System.out.println("Logged in successfully!");
            return true;
        }
        else{
            System.out.println("UserName doesn't exist");
            return false;
        }
    }
    public String encrypt(String password) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            char ch = password.charAt(i);

            if (Character.isUpperCase(ch)) {
                char newChar = (char) (((ch - 'A' + 1) % 26) + 'A');
                result.append(newChar);
            } else if (Character.isLowerCase(ch)) {
                char newChar = (char) (((ch - 'a' + 1) % 26) + 'a');
                result.append(newChar);
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public void addTask(String userName, String description, Status status){
        ToDo todo;
        if(todos.containsKey(userName)){
            todo = new ToDo(taskId++,description, status);
            todos.get(userName).add(todo);
            System.out.println("Task created successfully!");
            todoRepo.saveToUserFile(userName,todo);
        }
        else{
            ArrayList<ToDo> newLst = new ArrayList<>();
            todo = new ToDo(taskId++,description, status);
            newLst.add(todo);
            todos.put(userName,newLst);
            System.out.println("Task created successfully!");
            todoRepo.saveToUserFile(userName,todo);
        }
    }

    public void updateTask(String userName, int taskId, String description, Status status){
        if(todos.containsKey(userName)){
            boolean isTaskPresent = false;
           for(ToDo todo: todos.get(userName)){
               if(todo.getId() == taskId){
                   if(!description.isEmpty()) todo.setDescription(description);
                   todo.setStatus(status);
                   System.out.println(taskId + " Task updated successfully!");
                   todoRepo.updateToUserFile(taskId, userName, todo);
                   return;
               }
           }
           if(!isTaskPresent) System.out.println("Invalid taskId!");
        }
        else{
            System.out.println("Not yet to create task!");
        }
    }

    public void deleteTask(String userName, int taskId){
        if(todos.containsKey(userName)){
            boolean isTaskPresent = false;
            ArrayList<ToDo> lst = todos.get(userName);
            for(ToDo todo: lst){
                if(todo.getId() == taskId){
                    lst.remove(todo);
                    System.out.println(taskId + " Task deleted successfully!");
                    todoRepo.deleteFromUserFile(userName, taskId);
                    return;
                }
            }
            if(!isTaskPresent) System.out.println("Invalid taskId!");
        }
        else{
            System.out.println("Not yet to create task!");
        }
    }

    public void display(String userName){
        if(todos.containsKey(userName)){
            ArrayList<ToDo> lst = todos.get(userName);
            System.out.println("TaskId\t\t\tDescription\t\t\tStatus");
            for(ToDo todo: lst){
                System.out.println(todo);
            }
        }
        else{
            System.out.println("Not yet to create task!");
        }
    }

}
