package com.zm.zmtools.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.stereotype.Component;

@Component
@Data
@PropertySource({"classpath:application.yml"})
@ConfigurationProperties(prefix = "testcommon")
public class ServerSetting {
	 private String text;
}
