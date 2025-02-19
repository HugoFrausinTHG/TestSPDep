package taskAPI.transformer;
import taskAPI.dto.TaskDTO;
import taskAPI.domain.Task;
import org.springframework.stereotype.Component;

@Component

public class TaskTransformer {
    public TaskDTO transform(Task task) {
        if (task == null) {
            return null;
        }
        return new TaskDTO(task.id(), task.title(),task.description(),task.status());
    }
    public Task transform(TaskDTO taskDTO) {
        return new Task(taskDTO.id(), taskDTO.title(),taskDTO.description(),taskDTO.status());
    }
    public Task transform(String id, TaskDTO taskDTO) {
        return new Task(id, taskDTO.title(),taskDTO.description(),taskDTO.status());
    }
}