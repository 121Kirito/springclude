package com.itheima.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface userService extends IService<User> {
    void deductMoney(Long id, Integer money);

    List<User> listQuery(UserQuery userQuery);

    UserVO queryUserAndAddress(Long id);
}
