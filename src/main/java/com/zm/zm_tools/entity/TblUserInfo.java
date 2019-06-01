package com.zm.zm_tools.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@TableName("tbl_userinfo")
public class TblUserInfo extends Model<TblUserInfo> {

    private static final long serialVersionUID = 1L;

    /**
     * 自增id
     */
    @TableId(value="id", type= IdType.AUTO)
    private Integer id;

    private String username;

    private String phone;

    private Integer age;

    public static final String ID = "id";

    public static final String USERNAME = "username";

    public static final String AGE = "age";

    public static final String PHONE = "phone";


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
