import org.dom4j.DocumentException;
import org.junit.Test;
import week3.BeanException;
import week3.MyApplication;
import week3.SetterDI;
import week3.TestClass.StudentDao;
import week3.TestClass.StudentService;

import static org.junit.Assert.assertEquals;

public class MiniApplicationContextTest {
    @Test
    public void test() throws BeanException, DocumentException {
        String filename="D:\\myJavaSpace\\JAVAEE\\assignment\\week3_homework\\src\\main\\resources\\ApplictionContext.xml";
        MyApplication b=new MyApplication();
        b.getBeans(filename);
        for (String key : b.beans.keySet()) {
            if(key.equals("studentDao")){
                Object value = b.beans.get(key);
                StudentDao m=(StudentDao)value;
                m.setNum(100);
                assertEquals(100,m.getNum());
            }
        }
    }
}
