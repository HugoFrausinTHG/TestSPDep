package taskAPI.repository.taskListRepo;
import taskAPI.domain.Task;
import taskAPI.domain.TaskList;
import taskAPI.enums.STATUS;

import java.util.Collection;

public interface CustomTaskListRepository {
    TaskList findAndDelete(String id);
}