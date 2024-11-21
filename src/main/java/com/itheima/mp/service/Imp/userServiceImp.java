package com.itheima.mp.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.userService;
import org.springframework.stereotype.Service;

import java.util.List;

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
        int remianBalance = user.getBalance() - money;
        lambdaUpdate().set(User::getBalance, remianBalance)
                .set(remianBalance == 0, User::getStatus, 2)
                .eq(User::getBalance, user.getBalance())//乐观锁
                .eq(User::getId, id).update();
    }


    //lambad查询
    @Override
    public List<User> listQuery(UserQuery userQuery) {
        return lambdaQuery().like(userQuery.getName() != null, User::getUsername, userQuery.getName())
                .eq(userQuery.getStatus() != null, User::getStatus, userQuery.getStatus())
                .ge(userQuery.getMaxBalance() != null, User::getBalance, userQuery.getMaxBalance())
                .le(userQuery.getMinBalance() != null, User::getBalance, userQuery.getMinBalance()).list();
    }

    @Override
    public UserVO queryUserAndAddress(Long id) {
        User user = getById(id);

        return null;
    }
}
