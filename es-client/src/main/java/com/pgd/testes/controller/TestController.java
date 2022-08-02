package com.pgd.testes.controller;

import com.pgd.testes.service.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    EsService esService;

    @RequestMapping(value = "test")
    public Object getRec(String title) {
        return esService.list(title);
    }

}
