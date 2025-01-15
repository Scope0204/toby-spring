package springbook.user.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }
    @Bean
    public MessageDao messageDao() {
        return new MessageDao(connectionMaker());
    }
    @Bean
    public AccountDao accountDao() {
        return new AccountDao(connectionMaker());
    }

    // ConnectionMaker 타입 오브젝트를 선정하고 생성하는 중복 로직을 메서드로 분리
    @Bean
    public ConnectionMaker connectionMaker() {
        return new DConnectionMaker();
    }
}
