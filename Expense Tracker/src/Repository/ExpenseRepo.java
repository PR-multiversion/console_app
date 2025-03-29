package Repository;

import Entity.Expense;

import java.io.*;
import java.util.ArrayList;

public class ExpenseRepo {

    public static ExpenseRepo expenseRepo = null;
    public static ExpenseRepo initializeExpenseRepo(){
        if(expenseRepo == null){
            expenseRepo = new ExpenseRepo();
            return expenseRepo;
        }
        return expenseRepo;
    }

    public Expense parseExpense(String line){
        try{
            String[] e = line.split("\\|\\|");

            if(e.length == 6){
                long id = Integer.parseInt(e[0].trim());
                String category = e[1].trim();
                double amount = Double.parseDouble(e[2].trim());
                String date = e[3].trim();
                String time = e[4].trim();
                String desc = e[5].trim();
                return new Expense(id,category,amount,date,time,desc);
            }
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public ArrayList<Expense> getExpense(String userName){
        final String FILE_PATH = "src/util/"+userName+".txt";
        ArrayList<Expense> lst = new ArrayList<>();
        try{
            File f = new File(FILE_PATH);
            if(f.exists()){
                BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
                String line = br.readLine();
                while(line != null){
                    lst.add(parseExpense(line));
                    line = br.readLine();
                }
            }
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
        return lst;
    }
    public void addExpense(String userName, Expense expense){
        final String FILE_PATH = "src/util/"+userName+".txt";
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH,true))){
            bw.write(expense.getId()+" || "+expense.getCategory()+" || "+expense.getAmount()+" || "+expense.getDate()+" || "+expense.getTime()+" || "+expense.getDescription());
            bw.newLine();
            System.out.println("Expense added successfully!");
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void updateExpense(String userName, long id, Expense expense){
        final String FILE_PATH = "src/util/"+userName+".txt";
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line = br.readLine();
            ArrayList<String> lines = new ArrayList<>();
            while(line != null){
                if(line.startsWith(id+"")){
                    lines.add(id+" || "+expense.getCategory()+" || "+expense.getAmount()+" || "+expense.getDate()+" || "+expense.getTime()+" || "+expense.getDescription());
                }
                else{
                    lines.add(line);
                }
                line = br.readLine();
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))){
                for(String l: lines){
                    bw.write(l);
                    bw.newLine();
                }
            }
            System.out.println(id+" updated successfully!");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void deleteExpense(String userName, long id){
        final String FILE_PATH = "src/util/"+userName+".txt";
        try(BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))){
            String line = br.readLine();
            ArrayList<String> lines = new ArrayList<>();
            while(line != null){
                if(!line.startsWith(id+"")){
                    lines.add(line);
                }
                line = br.readLine();
            }
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))){
                for(String l: lines){
                    bw.write(l);
                    bw.newLine();
                }
            }
            System.out.println(id+" deleted successfully!");
        }
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
