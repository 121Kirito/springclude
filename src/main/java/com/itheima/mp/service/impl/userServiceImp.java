package com.itheima.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.itheima.mp.domain.dto.PageDTO;
import com.itheima.mp.domain.po.Address;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.AddressVO;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.neums.UserStatus;
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
        if (user == null || user.getStatus() == UserStatus.FREEZE) {
            throw new RuntimeException("用户状态异常");
        }
        //检查用户余额
        if (user.getBalance() < money) {
            throw new RuntimeException("用户余额不足");
        }
        //业务代码
        int remianBalance = user.getBalance() - money;
        lambdaUpdate().set(User::getBalance, remianBalance)
                .set(remianBalance == 0, User::getStatus, UserStatus.FREEZE)
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

    public UserVO queryUserAndAddress(Long id) {
        System.out.println(id);
        User user = getById(id);
        UserVO userVO = BeanUtil.copyProperties(user, UserVO.class);
        List<Address> list = Db.lambdaQuery(Address.class).eq(Address::getUserId, id).list();
        if (list != null && list.size() > 0) {
            userVO.setAddress(BeanUtil.copyToList(list, AddressVO.class));
        }
        return userVO;
    }

    @Override
    public PageDTO<UserVO> userPageQuery(UserQuery pageQurey) {
        Page page = pageQurey.getPage(new OrderItem("update_time", false));
        //查询数据
        Page<User> userPage = lambdaQuery().like(pageQurey.getName() != null, User::getUsername, pageQurey.getName())
                .eq(pageQurey.getStatus() != null, User::getStatus, pageQurey.getStatus()).page(page);
        //封装返回实体
        PageDTO<UserVO> userVOPageDTO = new PageDTO<UserVO>();
        userVOPageDTO.setTotal(userPage.getTotal());
        userVOPageDTO.setPages(userPage.getPages());
        if (userPage.getRecords().size() <= 0) {
            return userVOPageDTO;
        }
        List<UserVO> userVOS = BeanUtil.copyToList(userPage.getRecords(), UserVO.class);
        userVOPageDTO.setList(userVOS);
        return userVOPageDTO;
    }
}
