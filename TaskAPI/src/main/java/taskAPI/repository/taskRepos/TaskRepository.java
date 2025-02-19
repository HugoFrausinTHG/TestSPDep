package taskAPI.repository.taskRepos;

import taskAPI.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,String>, CustomTaskRepository {

}