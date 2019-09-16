package cn.raysonblog.hotdog.module.job.constants;

import java.io.Serializable;

public class JobConstants implements Serializable {

    /**
     * 默认时间表达式
     * 每隔10分钟执行一次
     */
    public static final String DEFUALT_CRON = "0 0/20 * * * ?";

    public static final String DEFUALT_GROUP_NAME = "defualt_group";
}
