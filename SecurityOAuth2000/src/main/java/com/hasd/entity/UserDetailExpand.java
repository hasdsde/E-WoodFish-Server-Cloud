package com.hasd.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/10 17:09
 **/

//org.springframework.security.core.userdetails.User
public class UserDetailExpand extends User {

    private String skill;//技能

    public UserDetailExpand(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }
}
