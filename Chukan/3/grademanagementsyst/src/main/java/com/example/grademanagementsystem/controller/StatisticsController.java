package com.example.grademanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.grademanagementsystem.service.ScoreService;
import com.example.grademanagementsystem.service.StudentService;
import com.example.grademanagementsystem.service.SubjectService;

/**
 * 成績統計画面を管理するコントローラー
 */
@Controller
public class StatisticsController {

    /** 成績情報を管理するサービス */
    private final ScoreService scoreService;

    /** 学生情報を管理するサービス */
    private final StudentService studentService;

    /** 科目情報を管理するサービス */
    private final SubjectService subjectService;

    /**
     * コンストラクタ
     *
     * @param scoreService 成績サービス
     * @param studentService 学生サービス
     * @param subjectService 科目サービス
     */
    public StatisticsController(
            ScoreService scoreService,
            StudentService studentService,
            SubjectService subjectService) {

        this.scoreService = scoreService;
        this.studentService = studentService;
        this.subjectService = subjectService;
    }

    /**
     * 統計画面を表示する
     *
     * @param model 画面へデータを渡すためのModel
     * @return 統計画面
     */
    @GetMapping("/statistics")
    public String showStatistics(Model model) {

        // 全体統計
        model.addAttribute(
                "averageScore",
                scoreService.getAverageScore()
        );

        model.addAttribute(
                "maxScore",
                scoreService.getMaxScore()
        );

        model.addAttribute(
                "minScore",
                scoreService.getMinScore()
        );

        // 学生別平均点
        model.addAttribute(
                "averageScoreByStudent",
                scoreService.getAverageScoreByStudent()
        );

        // 学生別ランキング
        model.addAttribute(
                "studentRanking",
                scoreService.getStudentRanking()
        );

        // 科目別平均点
        model.addAttribute(
                "averageScoreBySubject",
                scoreService.getAverageScoreBySubject()
        );

        // 学生一覧
        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        // 科目一覧
        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects()
        );
        return "statistics";
    }
}