package taskAPI.domain;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Collection;

@Entity
@Table(name = "taskLists")
public class TaskList{
    @Id
    private String listId;
    private String listName;
    private String description;
    @OneToMany
    private Collection<Task> tasks;
    public TaskList(){
    }

    public TaskList(String listId,String listName, Collection<Task> tasks, String description) {
        this.listId = listId;
        this.listName = listName;
        this.description = description;
        this.tasks = tasks;
    }
    public String id() {
        return listId;
    }
    public String listName() {
        return listName;
    }
    public String description() {return description;}

    public Collection<Task> tasks() {return tasks;}

    public String toString() {
        return "ID: " + listId + "\nName:" + listName + "\nDescription: " + description + "\nTasks:\n" + tasks;
    }

}
