package com.hotel.service.system;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.hotel.dto.UserDTO;
import com.hotel.entity.system.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author 小李哞哞
 * @date 2023/5/23
 */
public interface IUserService extends IService<User> {

    IPage<User> queryLikePageList(Long pageNum, Long pageSize, User user);

    UserDTO login(UserDTO userDTO);

    User uploadAvatar(MultipartFile avatar, User user);

    void setRole(Integer userId, Integer roleId);

    boolean saveOrUpdatePlus(User user);

    boolean removeByIdPlus(Integer id);

    boolean removeBatchByIdsPlus(List<Integer> ids);
}
