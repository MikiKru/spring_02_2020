package com.example.customer_service.controller;

import com.example.customer_service.model.Category;
import com.example.customer_service.model.Task;
import com.example.customer_service.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;


@Controller
public class TaskFrontEndController {
    private TaskService taskService;
    @Autowired
    public TaskFrontEndController(TaskService taskService) {
        this.taskService = taskService;
    }
    @GetMapping("/addTask")
    public String addTask(Model model){
        model.addAttribute("task", new Task());                                 // pusty obiekt taska
        model.addAttribute("categories", taskService.getAllTaskCategories());   // lista wszystkich dostępnych w db kategorii
        return "addTask";
    }
    @PostMapping("/addTask")
    public String addTask(@ModelAttribute @Valid Task task, BindingResult bindingResult){
//        if(bindingResult.hasErrors()){
//            return "addTask";
//        }
        taskService.addTask(task.getContent(), task.getPrice(), new HashSet<>(), null);
        return "redirect:/";
    }
}