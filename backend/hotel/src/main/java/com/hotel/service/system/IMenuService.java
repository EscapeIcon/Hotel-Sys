package com.hotel.service.system;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.entity.system.Menu;

import java.util.List;

/**
 * @author 小李哞哞
 * @date 2023/5/23
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> queryList();

    boolean removeByIdPlus(Integer id);

    boolean removeBatchByIdsPlus(List<Integer> ids);
}
