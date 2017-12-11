package com.chenchen;

import com.chenchen.dao.UserMapper;
import com.chenchen.models.Sex;
import com.chenchen.models.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;

import javax.sql.DataSource;
import java.io.Reader;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static SqlSessionFactory sqlSessionFactory;
    private static Reader reader;

    static {
        try {
            reader = Resources.getResourceAsReader("mybatis-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
            sqlSessionFactory.getConfiguration().addMapper(UserMapper.class);
        } catch (Exception e) {
            System.out.println("Catch exception");
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSession() {
        return sqlSessionFactory;
    }

    /**
     * Building SqlSessionFactory without XML
     */
//    public static SqlSessionFactory getSqlSessionFactory() {
//        DataSource dataSource = BlogDataSourceFactory.getBlogDataSource();
//        TransactionFactory transactionFactory =
//                new JdbcTransactionFactory();
//        Environment environment =
//                new Environment("development", transactionFactory, dataSource);
//        Configuration configuration = new Configuration(environment);
//        configuration.addMapper(BlogMapper.class);
//        SqlSessionFactory sqlSessionFactory =
//                new SqlSessionFactoryBuilder().build(configuration);
//
//        RoundingMode mode = RoundingMode.CEILING;
//
//        return sqlSessionFactory;
//    }

    /**
     * @param args
     */
    public static void main(String[] args) {

        SqlSession session = sqlSessionFactory.openSession();
        try {
            UserMapper userMapper = session.getMapper(UserMapper.class);
            User user = userMapper.getUserByID(1);
            if (user != null) {
                System.out.println(user);
            }

            User user1 = new User();
            user1.setName("new");
            user1.setSex2(Sex.Man);
            userMapper.insetUser(user1);

            session.commit();

        } catch (Exception e) {
            System.out.println("Catch exception");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
