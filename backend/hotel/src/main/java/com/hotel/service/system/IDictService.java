package com.hotel.service.system;

import com.hotel.entity.system.Dict;

import java.util.List;

/**
 * @author 小李哞哞
 * @date 2023/5/23
 */
public interface IDictService {

    List<Dict> queryLikePageList(Long pageNum, Long pageSize, Dict dict);

    /**
     * 查询指定类型的所有字典信息
     * @param type
     * @return
     */
    List<Dict> listByType(String type);

    boolean updateOrInsert(Dict dict);

    boolean rmById(Integer id);

    boolean rmBatch(List<Integer> ids);
}
