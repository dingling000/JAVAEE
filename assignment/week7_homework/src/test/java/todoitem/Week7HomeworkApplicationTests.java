package todoitem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import todoitem.aspect.ApiAspect;
import todoitem.controller.TodoController;
import todoitem.entity.TodoItem;
import todoitem.exception.TodoAdminException;
import todoitem.service.TodoService;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class Week7HomeworkApplicationTests {

    @Test
    void contextLoads() {
    }
    @Autowired
    TodoService todoService;
    @Autowired
    ApiAspect apiAspect;

    @BeforeEach
    private void initData(){
        TodoItem todoItem1 = new TodoItem();
        todoItem1.setId(1);
        todoItem1.setName("JAVAEE作业");
        todoItem1.setComplete(true);
        todoService.addTodo(todoItem1);

        TodoItem todoItem2 = new TodoItem();
        todoItem2.setId(2);
        todoItem2.setName("windows作业");
        todoItem2.setComplete(false);
        todoService.addTodo(todoItem2);
    }

    @Test
    public void testAspect() throws TodoAdminException {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(3);
        todoItem.setName("计组作业");
        todoItem.setComplete(false);
        todoService.addTodo(todoItem);
        todoService.deleteTodo(1);
        todoService.deleteTodo(2);
        //todoService.updateTodo(100,todoItem);
        //todoService.getTodo(-1);
        todoService.getTodo(3);
        apiAspect.getMetricsTimes().forEach((k,v)->{
            System.out.println(k+"="+v);
        });
        apiAspect.getMetricsTime();
        //System.out.println("发生异常的次数为"+apiAspect.getExpress());
    }
@Test
    public void testAfterThrowing(){
        assertThrows(TodoAdminException.class, () -> {
            todoService.deleteTodo(-1);
        });
    assertThrows(TodoAdminException.class, () -> {
        todoService.getTodo(-1);
    });
    }

}
