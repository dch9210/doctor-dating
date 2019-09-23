package com.dch.service.dating.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/get/test", method = RequestMethod.GET)
    @ResponseBody
    public Map<String, String> getTest() {
        Map<String, String> map = new HashMap<>();
        map.put("Hello", "word");
        log.debug("map {}", map);
        return map;
    }

}
