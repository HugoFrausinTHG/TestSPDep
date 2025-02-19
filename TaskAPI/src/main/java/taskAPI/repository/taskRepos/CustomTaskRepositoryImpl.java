package taskAPI.repository.taskRepos;

import taskAPI.domain.Task;
import taskAPI.enums.STATUS;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Repository;

@Repository
public class CustomTaskRepositoryImpl implements CustomTaskRepository {

    private final NamedParameterJdbcOperations jdbcTemplate;

    public CustomTaskRepositoryImpl(NamedParameterJdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Task findAndDelete(String id) {
        Task task = findById(id);
        jdbcTemplate.update("DELETE FROM tasks WHERE id = :id", new MapSqlParameterSource("id", id));
        return task;
    }

    @Override
    public Collection<Task> findByStatus(STATUS status) {
        String query = "Select id, title, description, status from tasks where status = :status";
        MapSqlParameterSource params =new MapSqlParameterSource("status", status.ordinal());
        var mapper = new TaskMapper();
        return jdbcTemplate.query(query, params, mapper);
    }

    private Task findById(String id) {
        String query = "Select id, title, description, status from tasks where id = :id";
        MapSqlParameterSource params =new MapSqlParameterSource("id", id);
        TaskMapper mapper = new TaskMapper();
        Collection<Task> tasks = jdbcTemplate.query(query, params, mapper);
        return tasks.stream().findFirst().orElse(null);
    }

    private static class TaskMapper implements ResultSetExtractor<Collection<Task>> {

        @Override
        public Collection<Task> extractData(ResultSet rs) throws SQLException, DataAccessException {
            Collection<Task> tasks = new ArrayList<>();
            while(rs.next()) {
                String id = rs.getString("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                STATUS status = STATUS.values()[rs.getInt("status")];
                Task task = new Task(id,title,description,status);
                tasks.add(task);
            }
            return tasks;
        }
    }
}