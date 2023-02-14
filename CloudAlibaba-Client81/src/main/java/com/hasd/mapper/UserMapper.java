package com.hasd.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hasd.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 * 用户 Mapper 接口
 * </p>
 *
 * @author hasd
 * @since 2023-02-14
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selecAll();
}
