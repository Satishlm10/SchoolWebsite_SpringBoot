package com.eazybytes.eazyschool.controller;

import com.eazybytes.eazyschool.entity.Holiday;
import com.eazybytes.eazyschool.model.HolidayDto;
import com.eazybytes.eazyschool.repository.HolidayRepository;
import com.eazybytes.eazyschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class HolidaysController {

    /* Using @RequestParam to extact information from query param
    @RequestMapping("/holidays")
    public String displayHolidays(@RequestParam (required = false) boolean festival,@RequestParam (required = false) boolean federal, Model model){
        model.addAttribute("festival",festival);
        model.addAttribute("federal",federal);
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

     */
/*
    @RequestMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable(required = false) String display, Model model){

        if(null != display && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }
        else if(null != display && display.equals("federal")){
            model.addAttribute("federal",true);
        }
        else if(null != display && display.equals("festival")){
            model.addAttribute("festival",true);
        }


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

 */

    @Autowired
    private HolidayRepository holidaysRepository;

    @GetMapping("/holidays/{display}")
    public String displayHolidays(@PathVariable String display,Model model) {
        if(null != display && display.equals("all")){
            model.addAttribute("festival",true);
            model.addAttribute("federal",true);
        }else if(null != display && display.equals("federal")){
            model.addAttribute("federal",true);
        }else if(null != display && display.equals("festival")){
            model.addAttribute("festival",true);
        }
        List<Holiday> holidays = holidaysRepository.findAllHolidays();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type type : types) {
            model.addAttribute(type.toString(),
                    (holidays.stream().filter(holiday -> holiday.getType().equals(type)).collect(Collectors.toList())));
        }
        return "holidays.html";
    }
}
