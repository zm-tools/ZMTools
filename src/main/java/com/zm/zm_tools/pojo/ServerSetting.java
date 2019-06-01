package com.zm.zm_tools.pojo;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource({"classpath:application.yml"})
@ConfigurationProperties(prefix = "TestCommon")
public class ServerSetting {

    private String text;

}
