package com.eazy.school.controller;

import com.eazy.school.model.Holiday;
import com.eazy.school.repository.HolidaysRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class HolidayController {

    @Autowired
    private HolidaysRepository holidaysRepository;

    @GetMapping("/holidays")
    public String displayHolidays(@RequestParam(required = false) boolean festival,
                                  @RequestParam(required = false) boolean federal,
                                  Model model) {
        model.addAttribute("festival", festival);
        model.addAttribute("federal", federal);
//        List<Holiday> holidays = Arrays.asList(
//                new Holiday(" Jan 1 ", "New Year's Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Oct 31 ", "Halloween", Holiday.Type.FESTIVAL),
//                new Holiday(" Nov 24 ", "Thanksgiving Day", Holiday.Type.FESTIVAL),
//                new Holiday(" Dec 25 ", "Chrismas", Holiday.Type.FESTIVAL),
//                new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Holiday.Type.FEDERAL),
//                new Holiday(" Jul 4 ", "Independence Day", Holiday.Type.FEDERAL),
//                new Holiday(" Sep 5 ", "Labor Day", Holiday.Type.FEDERAL),
//                new Holiday(" Nov 11 ", "Veterans Day", Holiday.Type.FEDERAL)
//        );
        Iterable<Holiday> all = holidaysRepository.findAll();
        List<Holiday> holidays = StreamSupport.stream(all.spliterator(), false).toList();
        Holiday.Type[] types = Holiday.Type.values();
        for (Holiday.Type t : types) {
            model.addAttribute(t.toString(),
                    holidays.stream().filter(h -> h.getType().equals(t))
                            .collect(Collectors.toList()));

        }
        return "holidays.html";
    }
}
