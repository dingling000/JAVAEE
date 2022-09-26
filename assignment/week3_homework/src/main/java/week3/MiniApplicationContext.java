package week3;


import org.dom4j.DocumentException;

/**
 * @author huawei
 */
public class MiniApplicationContext {
    public static void main(String[] args) throws BeanException, DocumentException {
        String filename="D:\\myJavaSpace\\JAVAEE\\assignment\\week3_homework\\src\\main\\resources\\ApplictionContext.xml";
        MyApplication b=new MyApplication();
        b.getBeans(filename);
        SetterDI s=new SetterDI(filename);
        Object obj=s.getRef(filename);
        s.setter(obj);
    }

}
