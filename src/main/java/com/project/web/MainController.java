package com.project.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

    @GetMapping("/qna")
    public String index() {
        return "qna";
    }
    @GetMapping("/")
    public String root() {
        return "redirect:/product/list";
    }
    
    
}