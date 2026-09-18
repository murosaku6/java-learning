package com.example.grademanagementsystem.controller;

import com.example.grademanagementsystem.model.Score;
import com.example.grademanagementsystem.service.ScoreService;
import com.example.grademanagementsystem.service.StudentService;
import com.example.grademanagementsystem.service.SubjectService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class ScoreController {
    private final ScoreService scoreService;
    private final StudentService studentService;
    private final SubjectService subjectService;

    @Autowired
    public ScoreController(
            ScoreService scoreService,
            StudentService studentService,
            SubjectService subjectService) {
                this.scoreService = scoreService;
                this.studentService = studentService;
                this.subjectService = subjectService;
    }
    @GetMapping("/scores")
    public String showScoreList(
            @RequestParam(required = false) Long studentId,
            @RequestParam(required = false) Long subjectId,
            Model model) {

        // 検索条件が指定されていない場合は全件表示
        if (studentId == null && subjectId == null) {

            model.addAttribute(
                    "scores",
                    scoreService.getAllScores()
            );

        } else {

            // 検索条件に一致する成績だけ取得
            model.addAttribute(
                    "scores",
                    scoreService.searchScores(studentId, subjectId)
            );
        }

        // 検索フォームに表示する学生・科目一覧
        model.addAttribute(
                "students",
                studentService.getAllStudents()
        );

        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects()
        );

        // 現在選択されている検索条件
        model.addAttribute("selectedStudentId", studentId);
        model.addAttribute("selectedSubjectId", subjectId);

        return "scores/list";
    }

    @GetMapping("/scores/new")
    public String showCreateForm(Model model) {
        Score score = new Score();
        score.setExamDate(LocalDate.now());
        model.addAttribute("score", score);
        model.addAttribute("students",
                studentService.getAllStudents());
        model.addAttribute("subjects",
                subjectService.getAllSubjects());
        return "scores/form";
    }

    /**
     * 成績を登録する
     *
     * @param score 登録する成績
     * @return 成績一覧
     */
    @PostMapping("/scores")
    public String createScore(
            @Valid @ModelAttribute Score score,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "scores/form";
        }

        scoreService.addScore(score);

        model.addAttribute(
                "scores",
                scoreService.getAllScores());

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects());

        return "scores/list";
    }

    /**
     * 成績情報を更新する
     *
     * @param score 更新後の成績
     * @return 成績一覧
     */
    @PostMapping("/scores/update")
    public String updateScore(
            @Valid @ModelAttribute Score score,
            BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return "scores/form";
        }

        scoreService.updateScore(score);

        model.addAttribute(
                "scores",
                scoreService.getAllScores());

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects());

        return "scores/list";
    }

    /**
     * 成績を削除する
     *
     * @param id 成績ID
     * @return 成績一覧
     */
    @GetMapping("/scores/delete/{id}")
    public String deleteScore(@PathVariable Long id) {
        scoreService.deleteScore(id);
        return "redirect:/scores";
    }

    /**
     * 成績編集画面を表示する
     *
     * @param id 成績ID
     * @param model Viewへ渡すデータ
     * @return 成績編集画面
     */
    @GetMapping("/scores/edit/{id}")
    public String showEditForm(
        @PathVariable Long id,
        Model model) {
            model.addAttribute("score",
                scoreService.findScoreById(id));
            model.addAttribute("students",
                studentService.getAllStudents());
            model.addAttribute("subjects",
                subjectService.getAllSubjects());
            return "scores/form";
    }
}