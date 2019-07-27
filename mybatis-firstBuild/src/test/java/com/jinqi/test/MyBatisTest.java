package com.jinqi.test;

import com.jinqi.dao.UserDao;
import com.jinqi.domain.User;
import com.jinqi.mybatis.sqlsession.SqlSession;
import com.jinqi.mybatis.sqlsession.SqlSessionFactory;
import com.jinqi.mybatis.sqlsession.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.util.List;

public class MyBatisTest {

    /**
     * 测试Mybatis
     * @param args
     */
    public static void main(String[] args) throws Exception{

        //1、读取配置文件
        InputStream in = com.jinqi.mybatis.io.Resources.getResourceAsStream("SqlMapConfig.xml");
//        InputStream in = MyBatisTest.class.getClassLoader().getResourceAsStream("SqlMapConfig.xml");
        //2、创建SqlSessionFactory工厂（SqlSessionFactory是接口，不能直接创建对象，使用SqlSessionFactoryBuilder）
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(in);
        //3、使用工厂创建SqlSession对象
        SqlSession session = factory.openSession();
        //4、使用SqlSession创建dao接口的代理对象
        UserDao userDao = session.getMapper(UserDao.class);
        //5、使用代理对象执行方法（创建持久层接口的对象
        List<User> userList = userDao.findAll();
        for (User user: userList) {
            System.out.println(user);
        }
        //6、释放资源
        session.close();
        in.close();
    }
}
