package todoitem.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import todoitem.entity.TodoItem;
import todoitem.exception.TodoAdminException;
import todoitem.service.TodoService;

@Api("待办事项管理")
@RestController
@RequestMapping("todos")
public class TodoController {
    @Autowired
    TodoService todoService;

    @ApiOperation("根据Id查询待办事项")
    @GetMapping("/get/{id}")
    public TodoItem getTodoById(@PathVariable long id) throws TodoAdminException {
        return todoService.getTodo(id);
    }

    @ApiOperation("添加待办事项")
    @PostMapping("/add")
    public TodoItem addTodo(@RequestBody TodoItem todo){
        return todoService.addTodo(todo);
    }

    @ApiOperation("修改代办事项")
    @PutMapping("/update/{id}")
    public void updateProduct(@PathVariable long id,@RequestBody TodoItem todo) throws TodoAdminException {
        todoService.updateTodo(id, todo);
    }

    @ApiOperation("删除待办事项")
    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable long id) throws TodoAdminException{
        todoService.deleteTodo(id);
    }

}
