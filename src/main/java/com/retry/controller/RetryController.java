package com.retry.controller;

import com.retry.client.RetryAnnoService;
import com.retry.client.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RetryController {

    @Autowired
    private RetryService retryService;
    @Autowired
    private RetryAnnoService retryAnnoService;

    @RequestMapping(value = "/retry", method = RequestMethod.GET)
    public String retry(String number) {
        System.out.println(number);
        retryAnnoService.service();
        return "20";
    }
}
