package com.dch.service.dating.api;

import com.dch.service.dating.service.CommonService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Api(value = "/common", tags = {"common"})
@RequestMapping("/common")
@RestController
public class CommonApi {

    @Autowired
    private CommonService commonService;

    @ApiOperation(value = "获取日历日期集合")
    @GetMapping("/calendar")
    public List<Date> getDayListOfMonth(int year, int month) {
        List<DateTime> list = commonService.getDayListOfMonth(year, month);
        return list.stream().map(DateTime::toDate).collect(Collectors.toList());
    }

}
