package com.jinqi.mybatis.io;

import java.io.InputStream;

/**
 * 使用类加载器读取配置文件的类
 */
public class Resources {

    /**
     * 根据传入的参数，获得一个字节输入流
     * @param filePath 传入的文件路径
     * @return 返回一个输入流
     */
    public static InputStream getResourceAsStream(String filePath){

        return Resources.class.getClassLoader().getResourceAsStream(filePath);
    }

}
