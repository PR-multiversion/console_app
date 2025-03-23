package Repository;

import Entity.ToDo;
import Enum.Status;
import java.io.*;
import java.util.ArrayList;

public class TodoRepo {

    public static TodoRepo todoRepo = null;
    public static TodoRepo intializeToDoRepo(){
        if(todoRepo == null){
            todoRepo = new TodoRepo();
            return todoRepo;
        }
        return todoRepo;
    }

    public ArrayList<ToDo> getToDo(String userName){
        final String FILEPATH = "src/utils/"+userName+".txt";
        ArrayList<ToDo> lst = new ArrayList<>();
        try{
            File f = new File(FILEPATH);
            if(f.exists()){
                FileReader fr = new FileReader(FILEPATH);
                BufferedReader br = new BufferedReader(fr);
                String line = br.readLine();
                while(line != null){
                    lst.add(parseToToDo(line));
                    line = br.readLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return lst;
    }
    public ToDo parseToToDo(String line){
        int firstSpaceIndex = line.indexOf(" ");
        int lastSpaceIndex = line.lastIndexOf(" ");
        int id = Integer.parseInt(line.substring(0, firstSpaceIndex));
        String description = line.substring(firstSpaceIndex + 1, lastSpaceIndex);
        String s = line.substring(lastSpaceIndex + 1);
        Status status = null;
        if(s.equals("NOT_STARTED")) status = Status.NOT_STARTED;
        else if(s.equals("IN_PROGRESS")) status = Status.IN_PROGRESS;
        else status = Status.COMPLETED;
        return new ToDo(id, description, status);
    }
    public void saveToUserFile(String userName,ToDo todo){
       final String FILEPATH = "src/utils/"+userName+".txt";

       try(BufferedWriter writer = new BufferedWriter(new FileWriter(FILEPATH, true))){
           writer.write(todo.getId()+" "+todo.getDescription()+" "+todo.getStatus()+"\n");
           System.out.println("Task saved in DB successfully!");
       }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateToUserFile(int id, String userName, ToDo todo){
        final String FILEPATH = "src/utils/"+userName+".txt";
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String line = br.readLine();
            String updatedLine = todo.getId()+" "+todo.getDescription()+" "+todo.getStatus();
            while(line != null){
                if(line.startsWith(id+" ")){
                    lines.add(updatedLine);
                }
                else lines.add(updatedLine);
                line = br.readLine();
            }

            for(String l: lines){
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILEPATH))){
                    bw.write(l);
                }
            }
            System.out.println("Task updated in DB successfully!");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteFromUserFile(String userName,int id){
        final String FILEPATH = "src/utils/"+userName+".txt";
        ArrayList<String> lines = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(FILEPATH))){
            String line = br.readLine();
            while(line != null){
                if(!line.startsWith(id+" ")){
                    lines.add(line);
                }
                line = br.readLine();
            }

            for(String l: lines){
                try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILEPATH))){
                    bw.write(l);
                }
            }
            System.out.println("Task updated in DB successfully!");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

}
