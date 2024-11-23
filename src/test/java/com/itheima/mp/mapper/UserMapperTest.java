package com.itheima.mp.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

//    @Test
//    void testInsert() {
//        User user = new User();
//        user.setId(5L);
//        user.setUsername("Lucy");
//        user.setPassword("123");
//        user.setPhone("18688990011");
//        user.setBalance(200);
//        user.setInfo(new UserInfo(24,"英文老师","female"));
//        user.setCreateTime(LocalDateTime.now());
//        user.setUpdateTime(LocalDateTime.now());
//        userMapper.
//        System.out.println("save success");
//    }
//
//    @Test
//    void testSelectById() {
//        User user = userMapper.queryUserById(5L);
//        System.out.println("user = " + user);
//    }


//    @Test
//    void testQueryByIds() {
//        System.out.println("nihao");
//        List<User> users = userMapper.queryUserByIds(List.of(1L, 2L, 3L, 4L));
//        users.forEach(System.out::println);
//    }

//    @Test
//    void testUpdateById() {
//        User user = new User();
//        user.setId(5L);
//        user.setBalance(20000);
////        userMapper.updateUser(user);
//    }
//}

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

}