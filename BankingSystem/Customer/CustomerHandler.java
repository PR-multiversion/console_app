package Customer;

import java.util.Scanner;

public class CustomerHandler {
    public static CustomerHandler customerHandler;
    public static CustomerHandler getInstance(){
        if(customerHandler == null){
            return customerHandler = new CustomerHandler();
        }
        return customerHandler;
    }

    public void addCustomer(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter you name: ");
        String name = sc.nextLine();

        System.out.println("Enter Password");
        String pass = sc.nextLine();

        System.out.println("Retype Password");
        String repass = sc.nextLine();

        if (name == "" || pass == "" || repass == "") {
            System.out.println("Customer creation failed!");
            return;
        }
        if (!pass.equals(repass)) {
            System.out.println("Password Mismatch");
            return;
        }
        if (!validPass(pass)) {
            System.out.println("Invalid Password");
            return;
        }
        //pass = encryptPass(pass);


    }
    private boolean validPass(String pass) {
        for (char c : pass.toCharArray()) {
            if ((Character.isAlphabetic(c)||Character.isDigit(c))){
                return false;
            }
        }
        return true;
    }


}
