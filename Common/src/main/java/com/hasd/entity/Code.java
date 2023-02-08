package com.hasd.entity;

/**
 * @author : hasd
 * @version 1.0.0
 * @since : 2023/2/8 15:28
 **/


public interface Code {
    String CODE_SUCCESS = "200";
    String CODE_BAD_REQUEST = "400";
    String CODE_AUTH_ERROR = "401";
    String CODE_FORBIDDEN = "403";
    String CODE_NOT_FOUND = "404";
    String CODE_TIME_OUT = "408";
    String CODE_SERVER_ERROR = "500";
    String CODE_SERVER_UNAVAILABLE = "503";
    String CODE_METHOD_NOT_ALLOWED = "405";

    String CODE_NO_TOKEN = "499";
}
