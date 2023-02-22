package com.hasd.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/22 15:20
 **/

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @TableId(value = "username")
    private String username;

    @TableId(value = "password")
    private String password;
    
    @TableId(value = "enable")
    private Integer enable;

    @TableId(value = "score")
    private Integer score;
}
