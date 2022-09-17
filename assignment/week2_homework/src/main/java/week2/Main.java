package week2;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;



public class Main {
    public String getProperties(String path){
        InputStream inputStream = this.getClass().getResourceAsStream(path);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //System.out.println(className);
        return properties.getProperty("bootstrapClass");
    }//获取myapp.properties的内容并返回类名

    public Object getObject(String className){
        if(className!=null) {
            Class<?> cls = null;
            try {
                cls = Class.forName(className);
                Constructor<?> stuCls = cls.getConstructor();
                Object stu = stuCls.newInstance();
                return stu;
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
        return null;
    }//根据类名创建具体的类的实例
    public boolean getMethod(Object obj,int n){
        try {
            Class cls = obj.getClass();
            Constructor stuCls = cls.getConstructor();
            Method[] m = cls.getMethods();
            Object stu =stuCls.newInstance();
            for (Method mTemp : m) {
                if (mTemp.isAnnotationPresent(InitMethod.class)) {
                    //mTemp.getParameterTypes();
                    mTemp.invoke(stu,n);
                    return true;
                }
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


        return false;

    }//找到使用initMethod注解的方法并调用


    public static void main(String[] args) {
        Main obj=new Main();
        String path="/myapp.properties";
        String className=obj.getProperties(path);
        obj.getMethod(obj.getObject(className), 100);
    }
}
