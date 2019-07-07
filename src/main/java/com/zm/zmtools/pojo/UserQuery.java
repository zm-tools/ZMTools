package com.zm.zmtools.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName UserQuery
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/6  19:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserQuery implements Serializable {

    private String username;
    private String password;

}
