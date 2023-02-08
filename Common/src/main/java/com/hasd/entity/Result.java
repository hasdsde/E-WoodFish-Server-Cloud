package com.hasd.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 15:27
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {
    private String code;
    private String msg;
    private Object data;

    public static Result success() {
        return new Result(Code.CODE_SUCCESS, "操作成功", null);
    }

    public static Result success(Object data) {
        return new Result(Code.CODE_SUCCESS, "成功", data);
    }

    public static Result success(String msg) {
        return new Result(Code.CODE_SUCCESS, msg, null);
    }

    public static Result success(String code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result error(String code, String msg) {
        return new Result(code, msg, null);
    }
}
