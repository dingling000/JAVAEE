package week3;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;

import java.util.List;
import java.util.Map;

/**
 * @author huawei
 */
public class MyApplication {
    public Map<String,Object> beans = new HashMap<>();
    public void getBeans(String path) throws BeanException, DocumentException {
        Document doc = null;
        try {
            SAXReader saxReader = new SAXReader();
            doc = saxReader.read(path);
            // 读取XML文件,获得document对象
            if(doc==null){
                throw new BeanException(BeanException.ErrorType.FILE_NOT_EXITS, "没找到该路径下的文件");
            }
            Element root = doc.getRootElement();
            List<Element> nodes = root.elements("bean");
            if(nodes==null){
                throw new BeanException(BeanException.ErrorType.TAG_NOT_EXISTS, "没找到标签名为bean的节点");
            }
            for(Element node:nodes){
                BeanDefinition bean=new BeanDefinition();
                bean.id=node.attributeValue("id");
                if (bean.id == null)
                {
                    throw new BeanException(BeanException.ErrorType.PROPERTY_NOT_EXISTS, "没找到属性名为id的属性");
                }
                bean.beanClass=node.attributeValue("class");
                if (bean.beanClass == null)
                {
                    throw new BeanException(BeanException.ErrorType.PROPERTY_NOT_EXISTS, "没找到属性名为class的属性");
                }
                if(node.element("property")!=null){
                    bean.name=node.element("property").attributeValue("name");
                    bean.ref=node.element("property").attributeValue("ref");
                }

                Object obj=getObject(bean.beanClass);
                beans.put(bean.id, obj);
            }

        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }


    }
    public Object getObject(String className) throws BeanException {
        if(className!=null) {
            Class<?> cls = null;
            try {
                cls = Class.forName(className);
                if(cls==null){
                    throw new BeanException(BeanException.ErrorType.CLASS_NOT_FOUND,"未找到该类");
                }
                Constructor<?> stuCls = cls.getConstructor();
                Object stu = stuCls.newInstance();
                return stu;
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (InvocationTargetException e) {
                throw new RuntimeException(e);
            } catch (InstantiationException e) {
                throw new BeanException(BeanException.ErrorType.INSTANTIATION_FAIL,"创建对象错误");
            } catch (IllegalAccessException e) {
                throw new BeanException(BeanException.ErrorType.INSTANTIATION_FAIL,"创建对象错误");
            } catch (NoSuchMethodException e) {
                throw new BeanException(BeanException.ErrorType.METHOD_ERROR,"未找到方法");
            }

        }
        return null;
    }//根据类名创建具体的类的实例

}
