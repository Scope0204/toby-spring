package springbook.user.dao;

import springbook.user.domain.User;

import java.sql.*;

public class UserDao {
    private ConnectionMaker connectionMaker;

    public UserDao() {
        connectionMaker = new DConnectionMaker(); // 인터페이스를 구현한 클래스 이름이 남아있다.
    }

    public void add(User user) throws ClassNotFoundException, SQLException {
        // DB 연결을 위한 커넥션을 가져옴
        Connection c = connectionMaker.makeConnection();

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
        // DB 연결을 위한 커넥션을 가져옴
        Connection c = connectionMaker.makeConnection();

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
}


