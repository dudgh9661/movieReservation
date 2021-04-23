package com.yeongho.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
    @GetMapping("/reservation-confirm")
    public String reservaitonCheck(Model model) {

        return "reservation-confirm";
    }
}
