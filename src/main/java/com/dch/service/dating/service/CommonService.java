package com.dch.service.dating.service;

import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class CommonService {

    public List<DateTime> getDayListOfMonth(int year, int month) {
        log.debug("getDayListOfMonth year {}, month {}", year, month);
        List<DateTime> list = new ArrayList<>();
        DateTime stime = new DateTime(year, month, 1, 0, 0, 0);
        DateTime etime = stime.dayOfMonth().withMaximumValue();
        while (stime.isBefore(etime) || stime.isEqual(etime)) {
            list.add(stime);
            stime = stime.plusDays(1);
        }
        return list;
    }

}
