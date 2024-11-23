package com.itheima.mp;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.service.userService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MpDemoApplicationTests {
    @Autowired
    userService userService;
    @Test
    void TestPage() {
        Page<User> page = Page.of(1, 2);
        Page<User> userPage = userService.page(page);
        System.out.println(userPage.getRecords());
    }

}
