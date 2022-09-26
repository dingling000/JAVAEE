package week3;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import week3.TestClass.StudentDao;
import week3.TestClass.StudentService;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author huawei
 */
public class SetterDI {
    private MyApplication m=new MyApplication();

    public SetterDI(String path){
        try {
            m.getBeans(path);
        } catch (BeanException e) {
            throw new RuntimeException(e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public String cla;
    public Object getRef(String path) throws BeanException {
        Document doc = null;
        try {
            SAXReader saxReader = new SAXReader();
            doc = saxReader.read(path);
            // 读取XML文件,获得document对象
            Element root = doc.getRootElement();
            List<Element> nodes = root.elements("bean");
            for (Element node : nodes) {

                if(node.element("property")!=null){
                    String ref = node.element("property").attributeValue("ref");

                    if (ref != null) {
                        for (String key : m.beans.keySet()) {
                            if (key.equals(ref)) {
                                Object value = m.beans.get(key);
                                this.cla=node.attributeValue("class");
                                return value;
                            }
                        }
                    }
                }
            }


        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    public void setter(Object obj){

        Class<?> cls = null;
        try {
            if(cla!=null){
                cls = Class.forName(cla);
                Constructor ClsCons = cls.getConstructor();
                StudentService ser= (StudentService) ClsCons.newInstance();
                ser.setStudentDao((StudentDao) obj);
                ser.save();
            }

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
