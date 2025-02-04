package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itheima.mp.domain.po.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {


    User queryUserById(@Param("id") Long id);

    /*
    自定义sql自动拼接mpsql
     */
    List<User> queryUserByIds(@Param("ids") List<Long> ids);

    void updataByid(@Param("ew") QueryWrapper<User> queryWrapper, @Param("amount") int amount);

    @Update("update user set balance=balance-#{money} where id =#{id}")
    void deductBlance(@Param("id") Long id, @Param("money") Integer money);
}
