package com.heitian.ssm.dao;

/**
 * Created by Zhang Jingzhuo on 2017/6/10.
 */

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import com.heitian.ssm.model.User;

import java.util.List;

//为什么要给方法的参数添加@Param注解呢？是因为该方法有两个或以上的参数，一定要加，不然mybatis识别不了。上面的BookDao接口的queryById方法和reduceNumber方法只有一个参数book_id，所以可以不用加 @Param注解，当然加了也无所

@Repository
public interface UserDao {

    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAllUser();

    List<User> selectUserByIdAndPassword(@Param("userId") String userId,@Param("userPassword") String userPassword);

    List<User> selectUserByUniversityAndSchool(@Param("userUniversity") Integer userUniversity,@Param("userSchool") Integer userSchool);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}