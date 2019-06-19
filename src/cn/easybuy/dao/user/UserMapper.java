package cn.easybuy.dao.user;

import cn.easybuy.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {


    /**
     * 根据id或者名字获取用户
     * @param id
     * @param loginName
     * @return
     * @throws Exception
     */
    public User getUser(@Param("id") Integer id, @Param("loginName") String loginName) throws Exception;

    /**
     * 统计一共有多少个用户
     * @return
     * @throws Exception
     */
    public Integer count() throws Exception;

    /**
     * 查询用户列表
     * @param startNo 起始偏移量
     * @param pageSize 每页显示数量
     * @return
     * @throws Exception
     */
    public List<User> getUserList(@Param("startNo") Integer startNo,
                                  @Param("pageSize") Integer pageSize) throws Exception;


    /**
     * 更新用户
     * @param user
     * @return
     */
    public int update(User user);
}