package com.zm.zmtools.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName Permission
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/3  23:01
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Permission implements Serializable {

    private Integer id;
    private String name;
    private String url;


}
