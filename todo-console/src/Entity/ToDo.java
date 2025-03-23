package Entity;
import Enum.Status;
public class ToDo {
    private int id;
    private String description;
    private Status status;

    public ToDo(int id,String description, Status status) {
        this.id = id;
        this.description = description;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String toString(){
        return this.id +"\t\t\t"+ this.description +"\t\t\t"+this.status;
    }
}
