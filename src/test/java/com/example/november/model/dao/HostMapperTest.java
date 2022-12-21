package com.example.november.model.dao;

import github.messnoob.november.model.mapper.HostMapper;
import github.messnoob.november.model.pojo.Host;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class HostMapperTest {

    @Autowired
    private HostMapper hostMapper;
    @Test
    public void test() throws IOException {
//        //1. 生成 sqlsession factory biulder 对象
//        SqlSessionFactoryBuilder sfb = new SqlSessionFactoryBuilder();
//        //2. 加载配置文件作为一个输入流
//        //这里 Resources 使用的包是 ibatis 包
//        InputStream resourceAsStream;
//        resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
//        //3. 通过会话工厂构造器对象和配置文件流构建一个会话构造工厂
//        SqlSessionFactory factory = sfb.build(resourceAsStream);
//        //4. 通过 SQL 会话工厂 //true 设置 mybatis 事务自动提交
//        SqlSession sqlSession = factory.openSession(true);
//        HostMapper hostMapper = sqlSession.getMapper(HostMapper.class);
        List<Host> hostList = hostMapper.getHostRecords();
        System.out.println(hostList);
    }
}
