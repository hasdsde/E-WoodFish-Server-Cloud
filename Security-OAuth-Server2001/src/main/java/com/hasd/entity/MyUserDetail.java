package com.hasd.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/16 19:48
 **/


public class MyUserDetail extends User { //org.springframework.security.core.userdetails.User
    String skill = "唱跳rap";

    public MyUserDetail(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
