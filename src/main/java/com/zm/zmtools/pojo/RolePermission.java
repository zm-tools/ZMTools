package com.zm.zmtools.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @ClassName RolePermission
 * @Description TODO
 * @Author zhaoluowei
 * @Date 2019/7/3  23:02
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class RolePermission implements Serializable {

    private Integer id;
    private Integer roleId;
    private Integer permissionId;


}
