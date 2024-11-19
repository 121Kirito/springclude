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
        //获取用户
        User user = getById(id);
        System.out.println(user);
        //检查用户状态
        if (user == null || user.getStatus() == 2) {
            throw new RuntimeException("用户状态异常");
        }
        //检查用户余额
        if (user.getBalance() < money) {
            throw new RuntimeException("用户余额不足");
        }
        //业务代码
        baseMapper.deductBlance(id, money);
    }
}
