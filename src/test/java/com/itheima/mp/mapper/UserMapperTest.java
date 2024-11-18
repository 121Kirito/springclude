package com.itheima.mp.mapper;

import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.saveUser(user);
        System.out.println("save success");
    }

    @Test
    void testSelectById() {
        User user = userMapper.queryUserById(5L);
        System.out.println("user = " + user);
    }


//    @Test
//    void testQueryByIds() {
//        System.out.println("nihao");
//        List<User> users = userMapper.queryUserByIds(List.of(1L, 2L, 3L, 4L));
//        users.forEach(System.out::println);
//    }

    @Test
    void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateUser(user);
    }
}

//自定义slq查询
//    @Test
//    void testqurqure() {
//        //更新条件
//        int amount = 100;
//
//        //创建更行条件
//        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
//        queryWrapper.in("id", List.of(1L, 2L, 3L, 4L));
//        //调用自定义sql
//        userMapper.updataByid(
//                queryWrapper, amount
//        );

