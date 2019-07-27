package com.jinqi.mybatis.sqlsession;

public interface SqlSessionFactory {

    /**
     * 用于打开一个新的session对象
     * @return
     */
    SqlSession openSession();
}
