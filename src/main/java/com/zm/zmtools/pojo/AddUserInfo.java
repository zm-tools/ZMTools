package com.zm.zmtools.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
@Data
@Accessors(chain = true)
public class AddUserInfo implements Serializable {

    private String username;

    private String phone;

    private Integer age;





}
