package springbook.user.dao;

public class DaoFactory {
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }

    public AccountDao AacountDao() {
        return new AccountDao(connectionMaker());
    }

    // ConnectionMaker 타입 오브젝트를 선정하고 생성하는 중복 로직을 메서드로 분리
    private ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
