import Service.ExpenseHandler;

import java.util.Scanner;
import Enum.Category;
import util.CategoryUtil;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        ExpenseHandler expenseHandler = new ExpenseHandler();
        int ch1;
        String username = "";
        String password = "";
        while(true){
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("1. Login\n2. Sign Up\n3. Exit");
            ch1 = inp.nextInt();
            inp.nextLine();
            switch(ch1){
                case 1:
                    System.out.println("UserName: ");
                    username = inp.nextLine();
                    System.out.println("Password: ");
                    password = inp.nextLine();
                    if(expenseHandler.login(username, password)){
                        expenseHandler.setExpenses(username);
                        expense(inp, expenseHandler, username);
                    }
                    break;
                case 2:
                    System.out.println("UserName: ");
                    username = inp.nextLine();
                    System.out.println("Password: ");
                    password = inp.nextLine();
                    if(expenseHandler.signUp(username,password)){
                        expenseHandler.setExpenses(username);
                        expense(inp, expenseHandler, username);
                    }
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }

    public static void expense(Scanner inp, ExpenseHandler expenseHandler, String userName){
        boolean flag = true;
        while(flag){
            System.out.println("-----------------------------------------------------------------------------------------");
            System.out.println("1. Add Expense\n2. Update Expense\n3. Delete Expense\n4. List Expense\n5. Search Expense\n6. OverView Total Expense\n7. Exit");
            int ch2 = inp.nextInt();
            inp.nextLine();
            switch(ch2){
                case 1:
                    System.out.println("Enter category:");
                    String category = getUserChoiceCategory(inp);
                    inp.nextLine();
                    System.out.println("Enter description:");
                    String desc = inp.nextLine();
                    System.out.println("Enter amount:");
                    double amt = inp.nextDouble();
                    expenseHandler.addExpense(userName,desc,category,amt);
                    break;
                case 2:
                    System.out.println("Enter task id");
                    long taskId = inp.nextLong();
                    inp.nextLine();
                    System.out.println("Enter update category:");
                    String updateCategory = getUserChoiceCategory(inp);
                    inp.nextLine();
                    System.out.println("Enter update description:");
                    String updDesc = inp.nextLine();
                    System.out.println("Enter update amount:");
                    double updateAmt = inp.nextDouble();
                    expenseHandler.updateExpense(userName,taskId,updDesc,updateCategory,updateAmt);
                    break;
                case 3:
                    System.out.println("Enter expense id");
                    int deleteId = inp.nextInt();
                    inp.nextLine();
                    expenseHandler.deleteExpense(userName,deleteId);
                    break;
                case 4:
                    expenseHandler.display(userName);
                    break;
                case 5:
                    System.out.println("Enter category or description:");
                    String search = inp.nextLine();
                    expenseHandler.search(userName,search);
                    break;
                case 6:
                    expenseHandler.overView(userName);
                    break;
                case 7:
                    flag = false;
                    break;
                default:
                    System.out.println("Invalid choice!");

            }
        }
    }
    public static String getUserChoiceCategory(Scanner inp){
        int i = 1,ch;
        String category = "";
        String subCategory = "";
        Category[] cat = Category.values();
        while(true){
            for(Category c: cat){
                System.out.print("\t"+i+". "+c.getDisplayName());
                i++;
            }
            ch = inp.nextInt();
            if(ch > cat.length || ch < 1) System.out.println("Invalid choice!");
            else{
                if(cat[ch-1].getSubCategories().length > 0){
                    subCategory = getUserChoiceSubCategory(inp,cat[ch-1].toString());
                }
                category = cat[ch-1].getDisplayName();
                return CategoryUtil.getCombinedCategory(category,subCategory);
            }
        }
    }

    public static String getUserChoiceSubCategory(Scanner inp, String subCategory){
        int i = 1,ch;

        String[] subCat = Category.valueOf(subCategory).getSubCategories();

        while(true){
            System.out.println("Enter subCategory:");
            for(String sub: subCat){
                System.out.print("\t"+i+". "+sub);
                i++;
            }
            ch = inp.nextInt();
            if(ch > subCat.length) System.out.println("Invalid choice!");
            else return subCat[ch-1];
        }
    }
}
