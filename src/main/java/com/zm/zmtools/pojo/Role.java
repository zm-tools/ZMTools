package com.zm.zmtools.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Role
 * @Description 角色表
 * @Author zhaoluowei
 * @Date 2019/7/3  23:00
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Role implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private List<Permission> permissionList;


}
