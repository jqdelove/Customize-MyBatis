package com.jinqi.mybatis.sqlsession.defaults;

import com.jinqi.mybatis.cfg.Configuration;
import com.jinqi.mybatis.sqlsession.SqlSession;
import com.jinqi.mybatis.sqlsession.proxy.MapperProxy;
import com.jinqi.mybatis.utils.DataSourceUtil;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * SqlSession的实现类
 * 自定义MyBatis中和数据库交互的核心类
 * 它可以创建dao接口的代理对象
 */
public class DefaultSqlSession implements SqlSession {

    private Configuration cfg;

    private Connection conn;

    public DefaultSqlSession(Configuration cfg) {
        this.cfg = cfg;
        conn = DataSourceUtil.getConnection(cfg);
    }

    /**
     * 用于创建代理对象
     * @param daoInterfaceClass dao的接口字节码
     * @param <T>
     * @return
     */
    @Override
    public <T> T getMapper(Class<T> daoInterfaceClass) {
        //参数分别是类加载器，代理类要实现的接口的字节码数组，如何代理
        return (T) Proxy.newProxyInstance(daoInterfaceClass.getClassLoader(),new Class[]{daoInterfaceClass},new MapperProxy(cfg.getMappers(),conn));
    }

    /**
     * 释放资源
     */
    @Override
    public void close() {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
