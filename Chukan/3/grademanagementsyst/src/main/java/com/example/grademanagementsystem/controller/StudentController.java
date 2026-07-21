package com.example.grademanagementsystem.controller;

import com.example.grademanagementsystem.model.Student;
import com.example.grademanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import jakarta.validation.Valid;
import org.springframework.validation.BindingResult;

/**
 * 学生管理画面を制御するコントローラー
 */
@Controller
public class StudentController {

    /** 学生管理サービス */
    private final StudentService studentService;

    /**
     * コンストラクタ
     *
     * @param studentService 学生管理サービス
     */
    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    /**
     * 学生一覧画面を表示する
     *
     * URL：/students
     *
     * @param model Viewへ渡すデータ
     * @return 学生一覧画面
     */
    @GetMapping("/students")
    public String showStudentList(Model model) {
        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "students/list";
    }

    /**
     * 学生登録画面を表示する
     *
     * @param model Viewへ渡すデータ
     * @return 学生登録画面
     */
    @GetMapping("/students/new")
    public String showCreateForm(Model model) {
        model.addAttribute(
                "student",
                new Student());

        return "students/form";
    }

    /**
     * 学生を登録する
     *
     * @param student 登録する学生
     * @return 学生一覧
     */
    @PostMapping("/students")
    public String createStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if(result.hasErrors()){
            return "students/form";
        }
        studentService.addStudent(student);
        return "redirect:/students";
    }

    /**
     * 学生編集画面を表示する
     *
     * @param id 学生ID
     * @param model Viewへ渡すデータ
     * @return 学生編集画面
     */
    @GetMapping("/students/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student student = studentService.findStudentById(id);
        model.addAttribute("student", student);
        return "students/form";
    }

    /**
     * 学生情報を更新する
     *
     * @param student 更新後の学生
     * @return 学生一覧
     */
    @PostMapping("/students/update")
    public String updateStudent(@Valid @ModelAttribute Student student, BindingResult result) {
        if (result.hasErrors()) {
            return "students/form";
        }
        studentService.updateStudent(student);
        return "redirect:/students";
    }

    /**
     * 学生を削除する
     *
     * @param id 学生ID
     * @return 学生一覧画面
     */
    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, Model model) {
        studentService.deleteStudent(id);
        model.addAttribute("students", studentService.getAllStudents());
        return "students/list";
    }
}