package Entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private long id;
    private String category;
    private String description;
    private String date;
    private String time;
    private double amount;

    DateTimeFormatter d = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    DateTimeFormatter t = DateTimeFormatter.ofPattern("hh:mm a");
    public Expense(long id, String category, String description, double amount) {
        this.id = id;
        this.category = category;
        this.date = d.format(LocalDate.now());
        this.time = t.format(LocalTime.now());
        this.amount = amount;
        this.description = description;
    }
    public Expense(long id, String category,double amount, String date, String time, String description){
        this.id = id;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.time = time;
        this.description = description;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString(){
        return this.id+"\t\t"+this.category+"\t\t"+this.description+"\t\t"+this.amount+"\t\t"+this.date+"\t\t"+this.time;
    }
}
