package Customer;

public class Customer {
    private int customerId;
    private long accountId;
    private String name;
    private String password;
    private double balance;

    public Customer(int customerId, long accountId, String name, String password, double balance) {
        this.customerId = customerId;
        this.accountId = accountId;
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return customerId+" "+accountId+" "+name+" "+password+" "+balance;
    }
}
