package week3.TestClass;

public class StudentService {
    StudentDao studentDao;

    public void setStudentDao(StudentDao studentDao){
        this.studentDao=studentDao;
    }

    public void save(){
       studentDao.save();
       System.out.println("studentService保存成功");
    }
}
