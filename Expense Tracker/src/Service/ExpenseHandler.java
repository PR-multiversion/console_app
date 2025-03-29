package Service;

import Entity.Expense;
import Repository.ExpenseRepo;
import Repository.UserRepo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExpenseHandler {
    private long expenseId = 1;
    Map<String, String> users = new HashMap<>();
    Map<String, ArrayList<Expense>> expense = new HashMap<>();

    ExpenseRepo expenseRepo = null;
    UserRepo userRepo = null;

    DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter t = DateTimeFormatter.ofPattern("hh:mm a");
    public ExpenseHandler(){
        expenseRepo = ExpenseRepo.initializeExpenseRepo();
        userRepo = UserRepo.initializeUserRepo();
        setUsers();
    }

    public void setUsers(){
        users = userRepo.getUsersFromFile();
    }
    public void setTodos(String userName){
        expense.put(userName,expenseRepo.getExpense(userName));
        ArrayList<Expense> lst = expense.get(userName);
        int index = lst.size() - 1;
        if(index >= 0){
            Expense e = lst.get(index);
            expenseId = e.getId() + 1;
        }
        else expenseId = 1;
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

    public void addExpense(String userName, String desc, String category, double amount){
        Expense expenseObj = null;
        if(!expense.containsKey(userName)){
            ArrayList<Expense> expenses = new ArrayList<>();
            expenseObj = new Expense(expenseId++,category,desc,amount);
            expenses.add(expenseObj);
            expense.put(userName, expenses);
            expenseRepo.addExpense(userName,expenseObj);
        }
        else{
            ArrayList<Expense> e = expense.get(userName);
            expenseObj = new Expense(expenseId++,category,desc,amount);
            e.add(expenseObj);
            expense.put(userName, e);
            expenseRepo.addExpense(userName,expenseObj);
        }
    }

    public void updateExpense(String userName,long expenseId, String desc, String category, double amount){
        if(users.containsKey(userName)){

            ArrayList<Expense> expenses = expense.get(userName);
            if(expenses.isEmpty()){
                System.out.println("Invalid expenseId!");
                return;
            }
            for(Expense e: expenses){
                if(e.getId() == expenseId){
                    e.setCategory(category);
                    e.setDescription(desc);
                    e.setAmount(amount);
                    e.setDate(d.format(LocalDate.now()));
                    e.setTime(t.format(LocalTime.now()));

                    expenseRepo.updateExpense(userName, expenseId, e);
                }
            }
        }
        else{
            System.out.println("Expense not yet to added!");
        }
    }

    public void deleteExpense(String userName,long expenseId){
        if(users.containsKey(userName)){

            ArrayList<Expense> expenses = expense.get(userName);
            if(expenses.isEmpty()){
                System.out.println("Invalid expenseId!");
                return;
            }

            Iterator<Expense> iterator = expenses.iterator();
            while(iterator.hasNext()){
                Expense e = iterator.next();
                if(e.getId() == expenseId){
                    expenseRepo.deleteExpense(userName,e.getId());
                    iterator.remove();
                    break;
                }
            }
        }
        else{
            System.out.println("Expense not yet to added!");
        }
    }

    public void search(String userName, String search){
        if(expense.containsKey(userName)){
            ArrayList<Expense> expenses = expense.get(userName);
            if(!expenses.isEmpty()){
                expenses.sort((e1, e2) ->{
                    int dateCompare = e2.getDate().compareTo(e1.getDate());
                    if(dateCompare == 0){
                        return e2.getTime().compareTo(e1.getTime());
                    }
                    return dateCompare;
                });
                ArrayList<Expense> result = new ArrayList<>();
                String searchLower = search.toLowerCase();
                for(Expense e: expenses){
                    String category = e.getCategory().toLowerCase();
                    String description = e.getDescription().toLowerCase();
                    if(category.contains(searchLower) || description.contains(searchLower)){
                        result.add(e);
                    }
                }
                if(!result.isEmpty()){
                    System.out.println("ExpenseId\t\tCategory\t\tDescription\t\tAmount\t\tDate\t\tTime");
                    for(Expense e: result){
                        System.out.println(e);
                    }
                }
                else System.out.println("No data found");
            }
            else{
                System.out.println("No data found");
            }
        }
        else System.out.println("Expense not yet to added!");
    }

    public void overView(String userName){
        if(expense.containsKey(userName)){
            ArrayList<Expense> expenses = expense.get(userName);
            if(!expenses.isEmpty()){
                HashMap<String,Double> amount = new HashMap<>();
                double amt = 0.0, total = 0.0;
                for(Expense e: expenses){
                    amt = e.getAmount();
                    amount.put(e.getCategory(),amount.getOrDefault(e.getCategory(),0.0)+amt);
                    total += amt;
                }

                System.out.println("==============================================================");
                System.out.printf("%-30s %-15s %s\n","Category","Total Amount","Percentage");
                System.out.println("==============================================================");
                double percentage;
                for(String cat: amount.keySet()){
                    amt = amount.get(cat);
                    percentage = (amt / total) * 100;
                    System.out.printf("%-30s ₹%-15.2f %.2f%%\n", cat, amt, percentage);
                }
                System.out.println("==============================================================");
                System.out.printf("%-30s ₹%-15.2f\n", "Total Expense",total);
            }
            else System.out.println("No data found");
        }
        else System.out.println("Expense not yet to added!");
    }
    public void display(String userName){
        if(expense.containsKey(userName)){
            ArrayList<Expense> expenses = expense.get(userName);
            if(!expenses.isEmpty()){
                expenses.sort((e1, e2) ->{
                    int dateCompare = e2.getDate().compareTo(e1.getDate());
                    if(dateCompare == 0){
                        return e2.getTime().compareTo(e1.getTime());
                    }
                    return dateCompare;
                });
                System.out.println("ExpenseId\t\tCategory\t\tDescription\t\tAmount\t\tDate\t\tTime");
                for(Expense e: expenses){
                    System.out.println(e);
                }
            }
        }
        else System.out.println("Expense not yet to added!");
    }

}
