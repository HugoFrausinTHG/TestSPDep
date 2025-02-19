
package taskAPI.controller;

import taskAPI.domain.Task;
import taskAPI.dto.TaskDTO;
import taskAPI.enums.STATUS;
import taskAPI.service.TaskService;
import taskAPI.transformer.TaskTransformer;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/List")
@CrossOrigin
public class TaskListController {
    private final TaskService taskService;
    private final TaskTransformer taskTransformer;

    public TaskListController(TaskService taskService, TaskTransformer taskTransformer) {
        this.taskService = taskService;
        this.taskTransformer = taskTransformer;
    }

    @GetMapping
    public Collection<TaskDTO> findAll() {
        Collection<Task> allTasks = taskService.findAll();
        return allTasks.stream().map(taskTransformer::transform).toList();
    }
    @GetMapping("/status/{status}")
    public Collection<TaskDTO> findByStatus(@PathVariable("status") STATUS status){
        Collection<Task> statusTasks = taskService.findByStatus(status);
        return statusTasks.stream().map(taskTransformer::transform).toList();
    }

    @GetMapping("/{id}")
    public TaskDTO findById(@PathVariable("id") String id) {
        Task task = taskService.findById(id);
        return taskTransformer.transform(task);
    }

    @PostMapping
    public TaskDTO create(@RequestBody TaskDTO taskDTO) {
        Task task = taskTransformer.transform(taskDTO);
        Task createdTask = taskService.create(task);
        return taskTransformer.transform(createdTask);
    }
    @PutMapping("/{id}")
    public TaskDTO update(@PathVariable("id") String id, @RequestBody TaskDTO taskDTO) {
        Task updatedTask = taskTransformer.transform(id, taskDTO);
        Task taskSaved = taskService.save(updatedTask);
        return taskTransformer.transform(taskSaved);
    }
    @DeleteMapping("/{id}")
    public TaskDTO delete(@PathVariable("id") String id) {
        Task deletedTask = taskService.delete(id);
        return taskTransformer.transform(deletedTask);
    }

}