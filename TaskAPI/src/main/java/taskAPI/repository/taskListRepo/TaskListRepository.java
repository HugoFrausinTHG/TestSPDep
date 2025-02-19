package taskAPI.repository.taskListRepo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskAPI.domain.TaskList;

@Repository
public interface TaskListRepository extends JpaRepository<TaskList,String>, CustomTaskListRepository {

}