import java.sql.SQLException;

public class Test {
    public static void main(String[] args){
        StudentManagement studentManagement= null;

        try {
            studentManagement = new StudentManagement();
            //添加班级
            studentManagement.addClass(2,"计算机与大数据学院");
            studentManagement.addClass(5,"化学学院");
            studentManagement.addClass(4,"梅努斯学院");
            //修改班级年级
            studentManagement.updateClassGrade(3,3);
            //修改班级所属学院
            studentManagement.updateClassCollage(3, "外国语学院");
            //删除班级
            studentManagement.deleteClass(4);

            //添加学生
            studentManagement.addStudent("男",2,"14712345678","小楼");
            studentManagement.addStudent("女",3,"10841345678","小欧");
            //修改学生姓名
            studentManagement.updateStudentName(2, "大楼");
            //修改学生班级
            studentManagement.updateStudentClass(2, 1);
            //修改学生电话
            studentManagement.updateStudentPhone(2, "10912345678");
            //修改学生性别
            studentManagement.updateStudentSexual(2,"女");
            //删除学生
            studentManagement.deleteStudent(2);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            studentManagement.release();
        }
    }
}
