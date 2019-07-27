package com.jinqi.mybatis.cfg;

import java.util.HashMap;
import java.util.Map;

/**
 * 自定义MyBatis的配置类
 * 封装了连接信息，和有着sql语句与resultType的map
 */
public class Configuration {

    private String driver;
    private String url;
    private String username;
    private String password;

    private Map<String,Mapper> mappers = new HashMap<String, Mapper>();

    public Map<String, Mapper> getMappers() {
        return mappers;
    }

    public void setMappers(Map<String, Mapper> mappers) {
        //将每次遍历到的值都追加存放进map中
        this.mappers.putAll(mappers);
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
