package todoitem.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import todoitem.entity.TodoItem;

public interface TodoRepository extends JpaRepository<TodoItem, Long> {
}
