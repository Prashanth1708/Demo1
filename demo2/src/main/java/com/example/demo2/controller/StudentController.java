package com.example.demo2.controller;

import com.example.demo2.entity.Student;
import com.example.demo2.service.StudentService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller

public class StudentController {
    private StudentService studentService;
    
    Logger logger= LoggerFactory.getLogger(StudentController.class);

    public StudentController(StudentService studentService) {
        super();
        this.studentService = studentService;
    }
    
    @GetMapping("/index")
    public String listStudents(Model model)
    {
        model.addAttribute("students", studentService.getAllStudents());
        logger.info("get all students");
        return "index";
    }
    @GetMapping("/index/new")
    public String createStudentForm(Model model)
    {
        Student student=new Student();
        model.addAttribute("student",student);
        logger.info("creating a student");
        return "create_student";
    }
    @PostMapping("/index")
    public String saveStudent(@ModelAttribute("student") Student student,
                              Model model, RedirectAttributes redirectAttributes)
    {
        studentService.saveStudent(student);
        logger.info("student added");
        redirectAttributes.addFlashAttribute("message", "student added successfully");
        return "redirect:/index";
    }
    @GetMapping("/index/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        
        model.addAttribute("student", studentService.getStudentById(id));

        return "edit_student";
    }
    @PostMapping("/index/{id}")
    public String updateStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model, RedirectAttributes redirectAttributes ) {



        // get student from database by id
        Student existingStudent = studentService.getStudentById(id);
        existingStudent.setId(id);
        existingStudent.setFirstName(student.getFirstName());
        existingStudent.setLastName(student.getLastName());
        existingStudent.setEmail(student.getEmail());

        // save updated student object
        studentService.updateStudent(existingStudent);
        logger.info("student updated");
        redirectAttributes.addFlashAttribute("message", "student updated successfully");
        return "redirect:/index";
    }
    @GetMapping("/index/{id}")
    public String deleteStudent(@PathVariable Long id,
                                @ModelAttribute("student") Student student,
                                Model model, RedirectAttributes redirectAttributes)
    {
        studentService.deleteStudentById(id);
        logger.info("student deleted");
        redirectAttributes.addFlashAttribute("message", "student deleted successfully");
        return "redirect:/index";
    }
}