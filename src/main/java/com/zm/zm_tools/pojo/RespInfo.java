package com.zm.zm_tools.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 返回类
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
public class RespInfo implements Serializable {

    private static final long serialVersionUID = 8862875864284900161L;

    /**
     * 返回状态码
     */
    private Integer code;
    /**
     * 返回消息
     */
    private String message;
    /**
     * 返回数据
     */
    private Object data;

    /**
     * 有数据的返回构造
     */
    public RespInfo(Integer code, String message, Object data) {
        super();
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * 无数据的返回构造
     */
    public RespInfo(Integer code, String message) {
        super();
        this.code = code;
        this.message = message;
        this.data = "";
    }


}
