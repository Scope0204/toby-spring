package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {
    public void add(User user) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        // DB를 위한 커넥션을 가져옴
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/springbook", "spring", "book"
        );

        // SQL 을 담은 Statement
        PreparedStatement ps = c.prepareStatement(
                "insert into users(id, name, password) values(?,?,?)");
        ps.setString(1, user.getId());
        ps.setString(2, user.getId());
        ps.setString(3, user.getPassword());

        // 만들어진 Statement 실행
        ps.executeUpdate();

        // 작업 중에 생성된 리소스는 마친 후 종료해주도록 한다
        ps.close();
        c.close();
    }

    public User get(String id) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");

        // DB를 위한 커넥션을 가져옴
        Connection c = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/springbook", "spring", "book"
        );

        // SQL 을 담은 Statement
        PreparedStatement ps = c.prepareStatement(
                "select * from users where id = ?");
        ps.setString(1, id);

        // 만들어진 Statement 실행. 조회의 경우 쿼리의 실행 결과를 ResultSet 으로 전달 받아 오브젝트에 전달.
        ResultSet rs = ps.executeQuery();
        rs.next();

        User user = new User();
        user.setId(rs.getString("id"));
        user.setName(rs.getString("name"));
        user.setPassword(rs.getString("password"));

        // 작업 중에 생성된 리소스는 마친 후 종료해주도록 한다
        rs.close();
        ps.close();
        c.close();

        return user;
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        UserDao dao= new UserDao();

        User user = new User();
        user.setId("scope");
        user.setName("조준경");
        user.setPassword("1111");

        dao.add(user);

        System.out.println(user.getId() + "등록 성공!");

        User user2 = dao.get(user.getId());
        System.out.println(user2.getName());
        System.out.println(user2.getPassword());

        System.out.println(user2.getId() + "조회 성공!");

    }
}


