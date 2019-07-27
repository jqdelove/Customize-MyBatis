package com.jinqi.mybatis.utils;

import com.jinqi.mybatis.cfg.Mapper;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 负责执行sql语句，并且封装结果集
 */
public class Executor {

    public <E> List<E> selectList(Mapper mapper, Connection con){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        try{
            //1、取出mapper中的数据
            String queryString = mapper.getQueryString();//select * from user;
            String resultType = mapper.getResultType();//com.jinqi.domain.User
            Class domainClass = Class.forName(resultType);
            //2、获取PreparedStatement对象
            pstm = con.prepareStatement(queryString);
            //3、执行sql语句，获取结果集
            rs = pstm.executeQuery();
            //4、封装结果集
            List<E> list = new ArrayList<>();
            while (rs.next()){
                //实例化要封装的实体类对象
                E obj = (E) domainClass.newInstance();
                //取出结果集的元信息
                ResultSetMetaData rsmd = rs.getMetaData();
                //取出列总数
                int columnCount = rsmd.getColumnCount();
                //遍历列总数
                for (int i = 1; i <= columnCount ; i++) {
                    //获取每列的名称，列名的序号是从1开始的
                    String columnName = rsmd.getColumnName(i);
                    //根据得到的列名，获取每列的值
                    Object columnValue = rs.getObject(columnName);
                    //给obj赋值，使用Java内省机制（借用PropertyDescriptor实现属性的封装）
                    PropertyDescriptor pd = new PropertyDescriptor(columnName,domainClass);
                    //获取他的写入方法
                    Method writeMethod = pd.getWriteMethod();
                    //把获取的列的值，给对象赋值
                    writeMethod.invoke(obj,columnValue);
                }
                //把赋好值的对象添加到集合中
                list.add(obj);
            }
            return list;
        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            release(pstm,rs);
        }
    }

    /**
     * 释放资源方法
     * @param pstm
     * @param rs
     */
    private void release(PreparedStatement pstm, ResultSet rs) {
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (pstm!=null){
            try {
                pstm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
