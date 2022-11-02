package todoitem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todoitem.dao.TodoRepository;
import todoitem.entity.TodoItem;
import todoitem.exception.TodoAdminException;

@Service
public class TodoService {
    @Autowired
    TodoRepository todoRepository;

    //添加代办
    public TodoItem addTodo(TodoItem todo){
        todoRepository.saveAndFlush(todo);
        return todoRepository.findById(todo.getId()).get();
    }

    public TodoItem getTodo(long id)  throws TodoAdminException {
        if(todoRepository.existsById(id)==false){
            throw new TodoAdminException("查询商品失败：商品不存在");
        }
        return todoRepository.findById(id).get();
    }



    public void updateTodo(long id, TodoItem todo)  throws TodoAdminException {
        if(todoRepository.existsById(id)==false){
            throw new TodoAdminException("修改商品失败：商品不存在");
        }
        todoRepository.saveAndFlush(todo);
    }

    public void deleteTodo(long id) throws TodoAdminException {
        if(todoRepository.existsById(id)==false){
            throw new TodoAdminException("删除商品失败：商品不存在");
        }
        todoRepository.deleteById(id);
    }

}
