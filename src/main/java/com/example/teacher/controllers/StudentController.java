package com.example.teacher.controllers;

import com.example.teacher.models.Student;
import com.example.teacher.reposotories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/demo")
public class StudentController {
    @Autowired
    private StudentRepository studentRepository;
    private long a;

    @RequestMapping("/add")
    public String showForm(Model model){
        model.addAttribute("student",new StudentController());
        return "student_add_form";
    }

    @RequestMapping("/addd")
    public String showFormm(Model model){
        model.addAttribute("student",new Student());
        return "update";
    }
    @PostMapping("/add")
    public String addStudent(@ModelAttribute Student student){
        studentRepository.save(student) ;

        return "redirect:/demo/all" ;
    }


    @GetMapping("/all2")
    public @ResponseBody Iterable<Student> allStudent1(){
        return studentRepository.findAll() ;
    }

    @GetMapping("/all")
    public String allStudent2(Model model){
        List<Student> student = (List<Student>) studentRepository.findAll();
        model.addAttribute("student", student) ;
        return "student" ;
    }

    @PostMapping("/adds")
    public String addsStudent(@ModelAttribute Student student){
        Student author1 = new Student();
        student.setId(a);
        student.setFirstName(student.getFirstName());
        student.setLastName(student.getLastName());
        student.setEmail(student.getEmail());
        studentRepository.save(student) ;

        return "redirect:/demo/all" ;
    }

    @RequestMapping(value = "/update",method = RequestMethod.GET)
    public ModelAndView updateStudent(Model model,@RequestParam("id") long id){
        a=id;
        Optional<Student> student = (Optional <Student> ) studentRepository.findById(id);
        model.addAttribute("student",student);
        return new ModelAndView("update");
    }
    @RequestMapping(value = "/deleteContact",method = RequestMethod.GET)
    public ModelAndView deleteContact(@RequestParam("id") long idd){
        studentRepository.deleteById(idd);
        return new ModelAndView("redirect:/demo/all");
    }
}
