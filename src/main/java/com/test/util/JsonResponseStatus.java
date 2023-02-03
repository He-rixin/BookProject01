package com.test.util;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum JsonResponseStatus {
    SUCCESS(200, "成功"),
    ERROR(100, "失败"),
    NULLACCOUNT(204, "账号不存在"),
    PWDERROR(205, "密码错误"),
    USEREXIST(305, "用户已存在"),
    ADDARTICLESSUCCESS(500, "添加成功"),
    ADDARTICLESERROR(504, "添加失败"),
    ADDARTICLESERRORESXIT(505, "添加失败,消息已存在"),
    UPDATEARTICLESERROR(604, "修改失败"),
    UPDATEARTICLESSUCCESS(600, "修改成功"),
    DELARTICLESERROR(704, "删除失败"),
    DELARTICLESSUCCESS(700, "删除成功"),
    SELECTARTICLESSUCCESS(800,"查询成功"),
    SELECTARTICLESERROR(804,"查询失败");
    private Integer code;
    private String msg;
}
