package cn.easybuy.service.user;

import cn.easybuy.dao.user.UserDao;
import cn.easybuy.dao.user.UserDaoImpl;
import cn.easybuy.dao.user.UserMapper;
import cn.easybuy.entity.User;
import cn.easybuy.utils.DataSourceUtil;
import cn.easybuy.utils.MyBatisUtil;
import cn.easybuy.utils.Pager;
import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

	private Logger logger = Logger.getLogger(UserServiceImpl.class);

	@Override
	public boolean add(User user){
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.add(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return  count>0;
		}
	}

	@Override
	public boolean update(User user) {
		boolean flag = false;
		SqlSession sqlSession = new MyBatisUtil().createSqlSession();

		int num = sqlSession.getMapper(UserMapper.class).update(user);

		if(num>0){
			flag = true;
		}
		//需要手动提交事务
		sqlSession.commit();
		MyBatisUtil.closeSqlSession(sqlSession);

		return flag;
	}

	@Override
	public boolean deleteUserById(Integer userId) {
		Connection connection = null;
		Integer count=0;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.deleteUserById(userId+"");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return  count>0;
		}
	}

	@Override
	public User getUser(Integer userId, String loginName) {
		User user = null;

		//登录功能改造
		SqlSession sqlSession = new MyBatisUtil().createSqlSession();
		try {
			user = sqlSession.getMapper(UserMapper.class).getUser(userId, loginName);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MyBatisUtil.closeSqlSession(sqlSession);

		return user;
	}

	@Override
	public List<User> getUserList(Integer currentPageNo, Integer pageSize) {
		//改造Service，使用MyBatis改造后的Dao层
		List<User> userList = new ArrayList<>();

		SqlSession sqlSession = new MyBatisUtil().createSqlSession();

		try {
			//执行dao层方法，获取数据总量
			int total = sqlSession.getMapper(UserMapper.class).count();
			//执行分页工具类的构造方法，得到一个分页工具
			Pager pager = new Pager(total, pageSize, currentPageNo);
			//通过分页工具获取到分页的起始偏移量
			int startNo = (pager.getCurrentPage() - 1) * pager.getRowPerPage();
			//执行dao层方法，获取指定位置的用户列表
			userList = sqlSession.getMapper(UserMapper.class).getUserList(startNo, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}

		MyBatisUtil.closeSqlSession(sqlSession);

		return  userList;
	}

	@Override
	public int count() {
		Connection connection = null;
		Integer count=null;
		try {
			connection = DataSourceUtil.openConnection();
			UserDao userDao = new UserDaoImpl(connection);
			count=userDao.count();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			DataSourceUtil.closeConnection(connection);
			return count;
		}
	}
}