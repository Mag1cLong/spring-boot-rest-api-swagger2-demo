package com.example.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 全局配置
 * Created by jcl on 2018/12/13
 */
@Component
@Order(1)
public class GlobalConfig {
    private static final Logger logger = LogManager.getLogger(GlobalConfig.class);

    public static final String HEADERS_X_TOTAL_COUNT = "X-Total-Count";//分页查询返回的总条数


    public GlobalConfig() {
        loadConfig();//初始化的时候先加载一次
        logger.info("初始化配置信息成功......");
    }


    /**
     * 定时刷新配置
     */
    @Scheduled(cron = "0 */1 * * * ?")//每隔1分钟执行一次
    private void refresh() {
        loadConfig();
        logger.info("刷新配置信息成功......");
    }

    /**
     * 加载配置
     */
    private void loadConfig() {

    }
}
