package com.zviproject.statisticservice.listener;

import org.apache.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class StatisticListener {

    private static Logger LOGGER = Logger.getLogger(StatisticListener.class);

    private static volatile long getRequestCount = 0;

    private static volatile long changeRequestCount = 0;

    @RabbitListener(queues = {"create", "update", "delete"})
    public void changeListener(String message){
        changeRequestCount++;
        LOGGER.info("///////////////////////////////" + message);
    }

    @RabbitListener(queues = {"get_all", "get_single"})
    public void  getListener(String message){
        getRequestCount++;
        LOGGER.info("*******************************\t" + message);
    }

    public long getGetRequestCount() {
        return getRequestCount;
    }

    public long getChangeRequestCount() {
        return changeRequestCount;
    }
}
