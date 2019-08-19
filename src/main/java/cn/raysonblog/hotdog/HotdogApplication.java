package cn.raysonblog.hotdog;

import cn.raysonblog.hotdog.core.util.SpringContextHolder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;

import java.net.InetAddress;
import java.net.UnknownHostException;

@SpringBootApplication
public class HotdogApplication implements ApplicationListener<WebServerInitializedEvent> {
    static Logger log = LogManager.getLogger(HotdogApplication.class);
    private static WebServerInitializedEvent event;
    public static void main(String[] args) {

        SpringApplication.run(HotdogApplication.class, args);
        String host = null;
        try {
            host = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        String application = SpringContextHolder.getApplicationContext().getApplicationName();
        int port = event.getWebServer().getPort();
        log.info("项目访问路径: http://{}:{}{}", host, port, application);
        log.info("swagger-ui访问路径：http://{}:{}{}/swagger-ui.html", host, port, application);
    }

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        // TODO Auto-generated method stub
        this.event = event;
    }
}
