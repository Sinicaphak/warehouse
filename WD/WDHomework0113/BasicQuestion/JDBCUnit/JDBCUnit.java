package JDBCUnit;

import java.sql.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * JDBCUnit类：封装了 配置文件的读取、获取连接、释放连接
 */
public class JDBCUnit {
    private static String driver =null;
    private static String url=null;
    private static String username=null;
    private static String password=null;

    static {
        try {
            //读取数据库信息
            InputStream inputStream= JDBCUnit.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(inputStream);

            driver=properties.getProperty("driver");
            url=properties.getProperty("url");
            username=properties.getProperty("username");
            password=properties.getProperty("password");
            //加载驱动
            Class.forName(driver);

        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    //获取连接
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url,username,password);
    }

    //释放连接
    public  static void release(Connection connection, Statement statement,ResultSet resultSet ) throws SQLException {
        if (resultSet!=null){
            resultSet.close();
        }
        if (statement!=null){
            statement.close();
        }
        if (connection!=null){
            connection.close();
        }
    }
}
