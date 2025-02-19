package taskAPI.service;

import taskAPI.domain.Task;
import taskAPI.enums.STATUS;
import taskAPI.repository.taskRepos.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    @Test
    public void findByIdTest() {
        Task mockTask = new Task("1","Test Service", "This is a mock Task to test service", STATUS.FINISHED);

        when(taskRepository.findById("1")).thenReturn(Optional.of(mockTask));
        Task result = taskService.findById("1");
        assertNotNull(result);
        assertEquals("Test Service",result.title());
    }
}


