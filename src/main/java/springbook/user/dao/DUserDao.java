package springbook.user.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DUserDao extends UserDao{
    @Override
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        // D사의 DB Connection 생성 코드(상속을 통해 확장. 실제로는 각 서브 클래스 별 생성 알고리즘을 사용한다.)
        Class.forName("com.mysql.jdbc.Driver");
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/springbook", "spring", "book"
        );
        return c;
    }
}
