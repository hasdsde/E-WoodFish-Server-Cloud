package com.hasd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hasd.entity.User;

import java.util.List;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 15:32
 **/


public interface UserMapper extends BaseMapper<User> {

    //你报什么错啊
    List<User> selectTop();

}
