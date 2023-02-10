package com.hasd.service;

import com.hasd.entity.UserDetailExpand;
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
 * @since : 2023/2/8 19:27
 **/

@Service
public class MyUserDetailService implements UserDetailsService {
    @Resource
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        userMapper.selectOne()
//        User user = new User();
        //数据先写死
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("all", "p1");
        UserDetailExpand userDetailExpand = new UserDetailExpand("hutao", new BCryptPasswordEncoder().encode("123"), authorityList);
        userDetailExpand.setSkill("唱跳rap");
        return userDetailExpand;
//        return User.withUsername("hutao")
//                .password(new BCryptPasswordEncoder().encode("123"))
//                .authorities("all", "p1").build();

        
    }
}
