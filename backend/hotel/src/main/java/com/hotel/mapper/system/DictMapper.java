package com.hotel.mapper.system;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hotel.entity.system.Dict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 小李哞哞
 * @date 2023/5/23
 */
public interface DictMapper {

    List<Dict> listByType(String type);

    List<Dict> queryLikePageList(Long start, Long pageSize, @Param("dict") Dict dict);

    int insertOne(Dict dict);

    int updateOne(Dict dict);

    int rmById(Integer id);

    int rmBatch(List<Integer> ids);
}
