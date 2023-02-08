package com.hasd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hasd.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 19:34
 **/

@Mapper
public interface UserMapper extends BaseMapper<User> {

}
