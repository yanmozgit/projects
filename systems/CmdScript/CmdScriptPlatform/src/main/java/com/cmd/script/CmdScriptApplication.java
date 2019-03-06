package com.cmd.script;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by wxp on 2016/11/15.
 */
@Configuration
@ComponentScan("com.cmd.script")
@EnableAutoConfiguration
@EnableScheduling //支持定时器
@EnableAspectJAutoProxy/*增添切面功能*/
@ServletComponentScan /*加上这个注解是为了扫描的servlet*/
@SpringBootApplication
public class CmdScriptApplication {

    //启动方法
    public static void main(String[] args) {
        SpringApplication.run(CmdScriptApplication.class, args);
    }

}
