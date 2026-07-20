package com.example.grademanagementsystem.controller;

import com.example.grademanagementsystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        return "students";
    }

}