//package com.zyg.basicutils.mybatis;
//
//import org.apache.ibatis.io.Resources;
//import org.apache.ibatis.session.*;
//import org.springframework.web.context.ContextLoader;
//import org.springframework.web.servlet.DispatcherServlet;
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServlet;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.*;
//
///**
// * 用于在服务器启动时将测试数据写入数据库
// * Created by Mo on 2017/6/13.
// */
//public class BatchUtil extends HttpServlet {
//
//    /**
//     * 生成mybatis的SqSsessionFactory
//     * @return SqlSessionFactory
//     */
//    private SqlSessionFactory getTestSqlSessionFactory(){
//        //读取配置
//        System.out.println("开始读取mybatis配置");
//        String resource = "test-mybatis-config.xml";
//        InputStream inputStream = null;
//        try {
//            inputStream = Resources.getResourceAsStream(resource);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return new SqlSessionFactoryBuilder().build(inputStream);
//    }
//
//    @Override
//    public void init() throws ServletException {
//        super.init();
//        Random random = new Random();
//        SqlSessionFactory sqlSessionFactory = getTestSqlSessionFactory();
//        List datas = null;//生成公共数据
////        batchInsert(sqlSessionFactory,"com.iquma.testdata.TestMapper.insertUser",users);//插入测试数据
//    }
//
//    /**
//     * 批量插入
//     * @param sqlSessionFactory
//     * @param statement 在Mapper文件中定义的namespce加上Mapper中定义的标识符
//     * @param objList 要入库的数据列表
//     */
//    public static void batchInsert(SqlSessionFactory sqlSessionFactory, String statement, List<?> objList) {
//        long start = System.currentTimeMillis();
//        System.out.println("进入了批量插入方法，起始时间是" + start);
//        SqlSession session = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
//        try {
//            for (Object obj : objList) {
//                session.insert(statement, obj);
//            }
//            session.flushStatements();
//            session.commit();
//            session.clearCache();
//        } catch (Exception ex) {
//            ex.printStackTrace();
//            session.rollback();
//        } finally {
//            session.close();
//        }
//        long end = System.currentTimeMillis();
//        System.out.println("完成了批量插入，耗时" + (end - start));
//    }
//
//}
