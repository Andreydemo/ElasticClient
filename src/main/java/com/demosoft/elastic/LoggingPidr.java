package com.demosoft.elastic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;

/**
 * Created by admin on 7/5/17.
 */
@Component
public class LoggingPidr {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostConstruct
    void init(){
        new Thread(new LoggingTask(1000)).start();
    }

    public class LoggingTask implements Runnable {

        private long timeout;

        public LoggingTask(long timeout) {
            this.timeout = timeout;
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                //logger.info("Test log, time: " + new Date());
                try {
                    Thread.sleep(timeout);
                } catch (InterruptedException e) {
                    logger.error("Error ", e);
                }
            }
        }

        public long getTimeout() {
            return timeout;
        }

        public void setTimeout(long timeout) {
            this.timeout = timeout;
        }
    }
}
