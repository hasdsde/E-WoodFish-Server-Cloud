package com.hasd.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.hasd.entity.MyUserDetail;
import com.hasd.entity.User;
import com.hasd.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/16 19:37
 **/

@Service
public class MyUserDetailService implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("username", s));
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList(user.getRole());
        MyUserDetail userDetail = new MyUserDetail(s, new BCryptPasswordEncoder().encode(user.getPassword()), authorityList);
        return userDetail;
    }
}
