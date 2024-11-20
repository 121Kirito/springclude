package com.itheima.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.userService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "用户管理")
@RequestMapping("/users")
@RestController
public class Usercontroller {
    @Autowired
    private userService userService;

    @ApiOperation("新增用户接口")
    @PutMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO) {
        //拷贝对象数据
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        userService.save(user);
    }

    @ApiOperation("删除用户接口")
    @DeleteMapping("{id}")
    public void DeletUser(@ApiParam("用户id") @Param("id") Long id) {
        userService.removeById(id);
    }

    @ApiOperation("根据id查询")
    @GetMapping("{id}")
    public UserVO SelectUser(@ApiParam("用户id") @Param("id") Long id) {
        User byId = userService.getById(id);
        return BeanUtil.copyProperties(byId, UserVO.class);
    }

    @ApiOperation("根据id查询集合")
    @GetMapping
    public List<UserVO> SelectUser(@ApiParam("用户id集合") @RequestParam("ids") List<Long> id) {
        List<User> users = userService.listByIds(id);
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @ApiOperation("扣减用户余额接口")
    @PutMapping("/{id}/deduction/{money}")
    public void deductionMoney(@ApiParam("用户id") @PathVariable("id") Long id,
                               @ApiParam("扣减金额") @PathVariable("money") Integer money) {
        userService.deductMoney(id, money);
    }

    @GetMapping("/list")
    @ApiOperation("复杂查询")
    public List<User> selectAll(@RequestBody UserQuery userQuery) {
        List<User> users = userService.listQuery(userQuery);
        return users;
    }

}
