package com.hotel.service.system.impl;


import cn.hutool.core.util.ObjUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hotel.common.StandardCode;
import com.hotel.entity.system.Dict;
import com.hotel.exception.ServiceException;
import com.hotel.mapper.system.DictMapper;
import com.hotel.service.system.IDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 小李哞哞
 * @date 2023/5/23
 */
@Service
public class DictService implements IDictService {

    @Autowired
    private DictMapper dictMapper;

    @Override
    public List<Dict> queryLikePageList(Long pageNum, Long pageSize, Dict dict) {
        if (ObjUtil.isEmpty(dict)) {
            throw new ServiceException(StandardCode.CODE_600, "参数异常");
        }

        Long start = (pageNum - 1) * pageSize;
        List<Dict> dicts = dictMapper.queryLikePageList(start, pageSize, dict);
        return dicts;
    }

    @Override
    public List<Dict> listByType(String type) {
        return dictMapper.listByType(type);
    }

    @Override
    public boolean updateOrInsert(Dict dict) {
        if (ObjUtil.isEmpty(dict)) {
            throw new ServiceException(StandardCode.CODE_400, "参数异常");
        }
        // 没有id则新增
        int count = 0;
        if (ObjUtil.isEmpty(dict.getId())) {
            count = dictMapper.insertOne(dict);
            if (count != 0) return true;
            return false;
        }
        // 有id则修改
        count = dictMapper.updateOne(dict);
        if (count != 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean rmById(Integer id) {
        int count = dictMapper.rmById(id);
        if (count != 0) return true;
        return false;
    }

    @Override
    public boolean rmBatch(List<Integer> ids) {
        int count = dictMapper.rmBatch(ids);
        if (count != 0) return true;
        return false;
    }
}
