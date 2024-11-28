package com.itheima.mp.domain.query;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class pageQurey {
    public Integer pageNo = 1;
    public Integer PageSiez = 5;
    public String sortBy;
    public Boolean isAsc = false;

    public <T> Page<T> getPage(OrderItem... orderItem) {
        //构建查询条件
        Page<T> page = Page.of(pageNo, PageSiez);
        //排序
        if (StrUtil.isNotBlank(sortBy)) {
            //不空
            page.addOrder(new OrderItem(sortBy, isAsc));
        } else if (orderItem.length > 0) {
            page.addOrder(orderItem);
        }
        return page;
    }
}
