package springbook.user.dao;

public class DaoFactory {
    public UserDao userDao() {
        ConnectionMaker connectionMaker = new DConnectionMaker(); // UserDao가 사용할 ConnectionMaker 구현 클래스를 결정하고 오브젝트를 생성
        UserDao userDao = new UserDao(connectionMaker);
        return userDao;
    }
}
