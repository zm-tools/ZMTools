package com.zm.zmtools.config;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @ClassName CustomSessionIdGenerator
 * @Description 自定义sessionId
 * @Author zhaoluowei
 * @Date 2019/7/7  15:24
 * @Version 1.0
 */

public class CustomSessionIdGenerator implements SessionIdGenerator {
    @Override
    public Serializable generateId(Session session) {
        return "zlw:"+ UUID.randomUUID().toString().replace("-","");
    }
}
