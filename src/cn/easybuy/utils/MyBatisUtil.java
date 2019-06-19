package cn.easybuy.utils;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisUtil {
    //创建一个工厂类对象
    public static SqlSessionFactory factory;
    static {
        try
        {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            factory = new SqlSessionFactoryBuilder().build(is);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库会话连接
     * @return
     */
    public static SqlSession createSqlSession(){
        //false意思是:需要手动提交事务，因此在执行增删改后，需要手动提交事务
        return  factory.openSession(false);
    }

    /**
     * 关闭数据库会话连接
     * @param session
     */
    public static void closeSqlSession(SqlSession session){
        session.close();
    }
}
