import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import week2.Main;
import week2.Student;

import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    Main mainTest;

    @BeforeEach
    public void setUp() {
        this.mainTest = new Main();
    }

    @AfterEach
    public void tearDown() {
        this.mainTest  = null;
    }

    @Test
   void testGetClass(){
        assertNotNull(mainTest.getProperties("/myapp.properties"));
        //assertNotNull(mainTest.getProperties("/fault.properties"));
        assertNotNull(mainTest.getObject(mainTest.getProperties("/myapp.properties")));
        //成功创建对象
        assertThrows(ClassNotFoundException.class, ()->{
            Class temp=Class.forName(mainTest.getProperties("/fault.properties"));
        });
        //类不存在的情况
   }
    @Test
    void testGetMethod(){
        Student st=new Student();
        assertTrue(mainTest.getMethod(st, 100));//对于参数正确的情况可以返回正确结果
        assertThrows(RuntimeException.class, ()->{
            boolean res;
            res = mainTest.getMethod(st, 0);
        });//当返回错误参数会显示异常，异常在main函数中调用invoke方法时抛出的是RuntimeException。
        //assertTrue(mainTest.getMethod(st, -1));
    }

}
