package taskAPI.domain;
import taskAPI.enums.STATUS;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task{
    @Id
    private String id;
    private String title;
    private String description;
    private STATUS status;
    public Task(){
    }

    public Task(String id, String title, String description, STATUS status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
    }
    public String id() {
        return id;
    }
    public String title() {
        return title;
    }
    public String description() {
        return description;
    }
    public STATUS status() {
        return status;
    }
    public String toString() {
        return "Task [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status + "]\n";
    }

}
