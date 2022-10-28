package week3.TestClass;

public class StudentDao {
    public int num;

    public void setNum(int n){
        num=n;
    }

    public int getNum() {
        return num;
    }

    public void save(){
        System.out.println("studentDao保存成功了");
    }
}
