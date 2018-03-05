package com.example.teacher.controllers;

import com.example.teacher.models.Student;
import com.example.teacher.models.Teacher;
import com.example.teacher.reposotories.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(path = "/teacher")

public class TeacherController {
    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("/main")
    public String allTeacher(Model model){
        List<Teacher> teacher = (List<Teacher>) teacherRepository.findAll();
        model.addAttribute("teacher",teacher);
        return "teacher";
    }
    @GetMapping("/add")
    public String TeacherForm(Model model){
        model.addAttribute("teacher",new Teacher());
        return "teacher_add_form";
    }
    @PostMapping("/add")
    public String add(@ModelAttribute Teacher teacher){
        teacherRepository.save(teacher);
        return "redirect:/teacher/main";
    }
    @RequestMapping(value = "/deleteTeacher",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        teacherRepository.deleteById(idd);
        return new ModelAndView("redirect:/teacher/main");
    }

}
