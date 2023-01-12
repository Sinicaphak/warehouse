
import java.sql.*;

/**
 * 学籍管理类
 */

public class StudentManagement {
    private Connection connection;
    private PreparedStatement preparedStatement=null;
    private ResultSet resultSet=null;
    //使用stringbuilder节约内存
    private StringBuilder stringBuilder=new StringBuilder();
    //待添加的学号
    private int newStudentNo;
    //待添加的班级编号
    private int newClassNo;

    public StudentManagement() throws SQLException {
        connection= JDBCUnit.getConnection();
        newStudentNo=getBiggestStudentNo()+1;
        newClassNo=getBiggestClassNo()+1;
    }


    //修改学生性别
    public void updateStudentSexual(int studentNo,String sexual){
        int isSuccess;
        try {
            //从学生表修改学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("update `student` set `student_sexual` = ? where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setString(1, sexual);
            preparedStatement.setInt(2, studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                System.err.println("修改失败");
                
                connection.setAutoCommit(true);
                return;
            }
            
            System.out.println("修改成功");
        } catch (SQLException e) {
            stringBuilder.setLength(0);
            throw new RuntimeException(e);
        }
    }
    //修改学生班级
    public void updateStudentClass(int studentNo,int classNo){
        int isSuccess;
        try {

            //关闭事务自动提交，确保班级表和学生表信息一致
            connection.setAutoCommit(false);

            //从学生表修改学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("update `student` set `class_no` = ? where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setInt(1, classNo);
            preparedStatement.setInt(2, studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                connection.rollback();
                System.err.println("修改失败");
                connection.setAutoCommit(true);
                return;
            }
            

            //从班级表修改学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("update `class` set `class_no` = ? where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setInt(1, classNo);
            preparedStatement.setInt(2, studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                connection.rollback();
                System.err.println("修改失败");
                connection.setAutoCommit(true);
                return;
            }

            stringBuilder.setLength(0);
            stringBuilder.append("update `class` set `admission_time` = ? where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setDate(1,new Date(System.currentTimeMillis()));
            preparedStatement.setInt(2, studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                connection.rollback();
                System.err.println("修改失败");
                connection.setAutoCommit(true);
                return;
            }
            

            //提交事务
            connection.commit();
            System.out.println("修改成功");
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }
    //修改学生电话
    public void updateStudentPhone(int studentNo,String phone){
        int isSuccess;
        try {
            //从学生表修改学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("update `student` set `phone` = ? where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setString(1,phone);
            preparedStatement.setInt(2, studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                System.err.println("修改失败");
                
                connection.setAutoCommit(true);
                return;
            }
            
            System.out.println("修改成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //修改学生名字
    public void updateStudentName(int studentNo,String name){
        int isSuccess;
        try {
            //从学生表修改学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("update `student` set `student_name` = ? where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setString(1,name);
            preparedStatement.setInt(2,studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                System.err.println("修改失败");
                
                connection.setAutoCommit(true);
                return;
            }
            
            System.out.println("修改成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //添加学生
    public void addStudent(String sexual,int classNo,String phone,String studentName){
        try {
            //关闭事务自动提交，确保班级表和学生表信息一致
            connection.setAutoCommit(false);

            //向学生表添加学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("insert into `student` (`student_sexual`,`class_no`,`phone`,`student_name`) " +
                    "VALUES (?,?,?,?) ");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setString(1, sexual);
            preparedStatement.setInt(2, classNo);
            preparedStatement.setString(3, phone);
            preparedStatement.setString(4, studentName);
            preparedStatement.executeUpdate();

            //向班级表添加学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("insert into `class` (`class_no`,`student_no`,`admission_time`) VALUES (?,?,?) ");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setInt(1,classNo);
            preparedStatement.setInt(2, newStudentNo);
            preparedStatement.setDate(3, new Date(System.currentTimeMillis()));
            preparedStatement.executeUpdate();
            //提交事务
            newStudentNo++;
            connection.commit();
            System.out.println("插入成功");
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                stringBuilder.setLength(0);
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

    }
    //删除学生
    public void deleteStudent(int studentNo){
        int isSuccess;
        try {
            //关闭事务自动提交，确保班级表和学生表信息一致
            connection.setAutoCommit(false);
            //从学生表删除学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("delete from `student` where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setInt(1, studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                connection.rollback();
                System.err.println("删除失败");
                connection.setAutoCommit(true);
                return;
            }
            //从班级表删除学生信息
            stringBuilder.setLength(0);
            stringBuilder.append("delete from `class` where `student_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setInt(1, studentNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                connection.rollback();
                System.err.println("删除失败");
                connection.setAutoCommit(true);
                return;
            }
            //提交事务
            connection.commit();
            System.out.println("删除成功");
        } catch (SQLException e) {
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }finally {
            try {
                stringBuilder.setLength(0);
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }


    //修改班级所属年级
    public void updateClassGrade(int classNo,int grade){
        int isSuccess;
        try {
                //从班级总表修改班级信息
            stringBuilder.setLength(0);
            stringBuilder.append("update `class_information` set `grade_no` = ? where `class_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setObject(1, grade);
            preparedStatement.setInt(2,classNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                connection.rollback();
                System.err.println("修改失败");

                connection.setAutoCommit(true);
                return;
            }
            
            System.out.println("修改成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //修改班级所属学院
    public void updateClassCollage(int classNo,String collageName){
        int isSuccess;
        try {
            //从班级总表修改班级信息
            stringBuilder.setLength(0);
            stringBuilder.append("update `class_information` set `collage_name` = ? where `class_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());

            preparedStatement.setObject(1, collageName);
            preparedStatement.setInt(2,classNo);

            isSuccess = preparedStatement.executeUpdate();

            if (isSuccess==0){
                System.err.println("修改失败");
                connection.setAutoCommit(true);
                return;
            }

            System.out.println("修改成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //添加班级
    public void addClass(int gradeNo,String collageName){
        try {
            //向班级总表添加班级信息
            stringBuilder.setLength(0);
            stringBuilder.append("insert into `class_information`(`class_no`,`grade_no`,`collage_name`) values (?,?,?)");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setInt(1,newClassNo);
            preparedStatement.setInt(2,gradeNo);
            preparedStatement.setString(3, collageName);
            preparedStatement.executeUpdate();
            
            newClassNo++;
            System.out.println("插入成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    //删除班级
    public void deleteClass(int classNo){
        int isSuccess;
        try {
            //从班级总表删除班级信息
            stringBuilder.setLength(0);
            stringBuilder.append("delete from `class_information` where `class_no` = ?");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            preparedStatement.setInt(1, classNo);
            isSuccess = preparedStatement.executeUpdate();
            if (isSuccess==0){
                System.err.println("删除失败");
                return;
            }
            
            System.out.println("删除成功");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    //获取最大的学号
    public int getBiggestStudentNo(){
        int studentNo;
        try {
            stringBuilder.setLength(0);
            stringBuilder.append("SELECT MAX(`student_no`) FROM `student`;");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            studentNo=resultSet.getInt("MAX(`student_no`)");
            
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
        return studentNo;
    }
    //获取最大的班级编号
    public int getBiggestClassNo(){
        int classNo;
        try {
            stringBuilder.setLength(0);
            stringBuilder.append("SELECT MAX(`class_no`) FROM `class_information`;");
            preparedStatement=connection.prepareStatement(stringBuilder.toString());
            resultSet=preparedStatement.executeQuery();
            resultSet.next();
            classNo=resultSet.getInt("MAX(`class_no`)");
            
        } catch (SQLException e) {
            
            throw new RuntimeException(e);
        }
        return classNo;
    }
    //释放连接
    public void release(){
        try {
            JDBCUnit.release(connection, preparedStatement, resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
