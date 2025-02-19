package taskAPI.repository.taskRepos;
import taskAPI.domain.Task;
import taskAPI.enums.STATUS;

import java.util.Collection;

public interface CustomTaskRepository {
    Task findAndDelete(String id);
    Collection<Task> findByStatus(STATUS status);

}