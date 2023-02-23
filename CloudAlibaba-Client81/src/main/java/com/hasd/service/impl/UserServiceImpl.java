package com.hasd.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hasd.entity.User;
import com.hasd.mapper.UserMapper;
import com.hasd.service.IUserService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户 服务实现类
 * </p>
 *
 * @author hasd
 * @since 2023-02-14
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
