package com.zviproject.statisticservice.controller;

import com.zviproject.statisticservice.entity.Statistic;
import com.zviproject.statisticservice.listener.StatisticListener;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/v1/statistic/service")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticListener statisticListener;

    @GetMapping("/")
    public Statistic getStatistic(){
        return new Statistic(statisticListener.getGetRequestCount(),
                             statisticListener.getChangeRequestCount());
    }

}
