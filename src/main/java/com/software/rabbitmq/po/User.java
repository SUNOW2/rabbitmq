package com.software.rabbitmq.po;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 描述：
 *
 * @ClassName User
 * @Author 徐旭
 * @Date 2018/9/11 11:16
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {
    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String pass;
}
