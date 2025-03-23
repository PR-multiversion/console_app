package Repository;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserRepo {

    public static UserRepo userRepo = null;
    public static UserRepo initializeUserRepo(){
        if(userRepo == null){
            userRepo = new UserRepo();
            return userRepo;
        }
        return userRepo;
    }
    public Map<String,String> getUsersFromFile(){
        final String FILEPATH = "src/utils/userDB.txt";
        Map<String, String> map = new HashMap<>();
        try{
            FileReader fr = new FileReader(FILEPATH);
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] userLine;
            while(line != null){
                userLine = line.split(" ");
                map.put(userLine[0],userLine[1]);
                line = br.readLine();
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return map;
    }

    public void saveUserToFile(String userName, String password){
        final String FILEPATH = "src/utils/userDB.txt";
        try(BufferedWriter br = new BufferedWriter(new FileWriter(FILEPATH,true))){
            br.write(userName+" "+password+"\n");
            System.out.println("User saved in DB successfully!");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
