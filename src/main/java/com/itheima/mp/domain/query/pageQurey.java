package com.itheima.mp.domain.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class pageQurey {
    public Integer pageNo;
    public Integer PageSie;
    public String sortBy;
    public Boolean isAsc;
}
