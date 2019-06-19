package cn.easybuy.test;

import cn.easybuy.utils.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;

public class TestUserMapper {
    SqlSession sqlSession = null;

    //1. 获取会话对象
    @Before
    public void beforeMethod(){
        sqlSession = MyBatisUtil.createSqlSession();
    }

    //3. 关闭会话对象
    @After
    public void afterMethod(){
        MyBatisUtil.closeSqlSession(sqlSession);
    }

}
