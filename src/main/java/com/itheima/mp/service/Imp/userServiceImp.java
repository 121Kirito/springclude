package com.itheima.mp.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.userService;
import org.springframework.stereotype.Service;

/**
 * 继承ISerivce中的方法
 */
@Service
public class userServiceImp extends ServiceImpl<UserMapper, User> implements userService {
    @Override
    public void deductMoney(Long id, Integer money) {

    }
}
