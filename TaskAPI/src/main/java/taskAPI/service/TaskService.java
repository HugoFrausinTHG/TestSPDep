package taskAPI.service;

import taskAPI.domain.Task;
import taskAPI.enums.STATUS;
import taskAPI.repository.taskRepos.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Collection<Task> findAll() {
        return taskRepository.findAll();
    }

    public Collection<Task> findByStatus(STATUS status) {
        return taskRepository.findByStatus(status);
    }
    public Task findById(String id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }

    public Task create(Task task) {
        Task newTask = new Task(UUID.randomUUID().toString(),task.title(),task.description(),task.status());
        return taskRepository.save(newTask);
    }

    public Task save(Task task) {
        return taskRepository.save(task);
    }

    public Task delete(String id) {
        return taskRepository.findAndDelete(id);
    }

}