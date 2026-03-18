package jp.gihyo.pro.java.tasklist.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {
        record TaskItem(String id, String task, String deadline, boolean done) {
    }
    private List<TaskItem> taskItems = new ArrayList<>();
    @GetMapping("/list")
    String listItem(Model model){
        model.addAttribute("taskList", taskItems);
        return "home";
    }
}