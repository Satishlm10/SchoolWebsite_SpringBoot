package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.model.HolidayDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {
    @RequestMapping("/holidays")
    public String displayHolidays(Model model){
        List<HolidayDto> holidays = Arrays.asList(
                new HolidayDto("Jan 1","New Year", HolidayDto.Type.FEDERAL),
                new HolidayDto("Oct 31","Halloween", HolidayDto.Type.FESTIVAL),
                new HolidayDto("Nov 24","Thanks Giving", HolidayDto.Type.FESTIVAL),
                new HolidayDto("Dec 25","Christmas", HolidayDto.Type.FESTIVAL),
                new HolidayDto("Jan 17","Martin Luther King Day", HolidayDto.Type.FEDERAL),
                new HolidayDto("July 4","Independence day", HolidayDto.Type.FEDERAL),
                new HolidayDto("May 1","Labor Day", HolidayDto.Type.FEDERAL),
                new HolidayDto("Nov 11","Veteran's Day", HolidayDto.Type.FEDERAL)
        );

        HolidayDto.Type[] types = HolidayDto.Type.values();
        for(HolidayDto.Type type : types){
            model.addAttribute(type.toString(),(holidays.stream().filter(holidayDto -> holidayDto.getType().equals(type))).collect(Collectors.toList()));
        }
        return "holidays.html";
    }
}
