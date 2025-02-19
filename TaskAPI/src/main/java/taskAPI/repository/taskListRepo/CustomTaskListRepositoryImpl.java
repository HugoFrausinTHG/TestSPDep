package taskAPI.repository.taskListRepo;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;
import taskAPI.domain.Task;
import taskAPI.domain.TaskList;
import taskAPI.enums.STATUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

@Repository
public class CustomTaskListRepositoryImpl implements CustomTaskListRepository {

    private final NamedParameterJdbcOperations jdbcTemplate;

    public CustomTaskListRepositoryImpl(NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public TaskList findAndDelete(String id) {
        TaskList taskList = findById(id);
        jdbcTemplate.update("DELETE FROM taskLists WHERE id = :id", new MapSqlParameterSource("id", id));
        return taskList;
    }

    private TaskList findById(String id) {
        String query = "Select id, title, description, tasks from taskLists where id = :id";
        MapSqlParameterSource params =new MapSqlParameterSource("id", id);
        TaskListMapper mapper = new TaskListMapper();
        Collection<TaskList> tasks = jdbcTemplate.query(query, params, mapper);
        return tasks.stream().findFirst().orElse(null);
    }

    private static class TaskListMapper implements ResultSetExtractor<Collection<TaskList>> {

        @Override
        public Collection<TaskList> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Collection<TaskList> taskLists = new ArrayList<>();
            while(rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("listName");
                String description = rs.getString("description");
                Collection<Task> tasks = new ArrayList<>();
                tasks.add(new Task("1","2","3",STATUS.FINISHED));
                TaskList taskList = new TaskList(id, title,tasks, description);
                taskLists.add(taskList);
            }
            return taskLists;
        }
    }
}