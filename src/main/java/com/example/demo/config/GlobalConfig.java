package com.example.demo.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 全局配置
 * Created by jcl on 2018/12/13
 */
@Component
public class GlobalConfig implements CommandLineRunner {
    private static final Logger logger = LogManager.getLogger(GlobalConfig.class);

    public static final String HEADERS_X_TOTAL_COUNT = "X-Total-Count";//分页查询返回的总条数


    @Override
    public void run(String... args) throws Exception {
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

    /**
     * 获取配置
     *
     * @param key
     * @return
     */
    public static <T> T get(String key) {
        if (StringUtils.isEmpty(key)) return null;

        return null;
    }
}
