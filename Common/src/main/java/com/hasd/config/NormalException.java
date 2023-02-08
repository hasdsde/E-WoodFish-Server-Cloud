package com.hasd.config;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 16:13
 **/

@Getter
@Setter
public class NormalException extends RuntimeException {
    private String code;
    private String msg;

    public NormalException() {
        super();
    }

    public NormalException(String msg) {
        super();
        this.msg = msg;
    }


    public NormalException(String msg, String code) {
        super();
        this.msg = msg;
        this.code = code;

    }

}
