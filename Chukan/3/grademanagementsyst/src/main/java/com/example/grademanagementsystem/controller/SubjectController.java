package com.example.grademanagementsystem.controller;

import com.example.grademanagementsystem.model.Subject;
import com.example.grademanagementsystem.service.SubjectService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 科目管理画面を制御するコントローラー
 */
@Controller
public class SubjectController {

    /** 科目管理サービス */
    private final SubjectService subjectService;

    /**
     * コンストラクタ
     *
     * @param subjectService 科目管理サービス
     */
    @Autowired
    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    /**
     * 科目一覧画面を表示する
     *
     * @param model Viewへ渡すデータ
     * @return 科目一覧画面
     */
    @GetMapping("/subjects")
    public String showSubjectList(Model model) {

        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects());

        return "subjects/list";
    }

    /**
     * 科目登録画面を表示する
     *
     * @param model Viewへ渡すデータ
     * @return 科目登録画面
     */
    @GetMapping("/subjects/new")
    public String showCreateForm(Model model) {

        model.addAttribute(
                "subject",
                new Subject());

        return "subjects/form";
    }

    /**
     * 科目を登録する
     *
     * @param subject 登録する科目
     * @param result バリデーション結果
     * @return 科目一覧
     */
    @PostMapping("/subjects")
    public String createSubject(
            @Valid @ModelAttribute Subject subject,
            BindingResult result) {

        if (result.hasErrors()) {
            return "subjects/form";
        }

        subjectService.addSubject(subject);

        return "redirect:/subjects";
    }

    /**
     * 科目編集画面を表示する
     *
     * @param id 科目ID
     * @param model Viewへ渡すデータ
     * @return 編集画面
     */
    @GetMapping("/subjects/edit/{id}")
    public String showEditForm(
            @PathVariable Long id,
            Model model) {

        Subject subject = subjectService.findSubjectById(id);

        model.addAttribute(
                "subject",
                subject);

        return "subjects/form";
    }

    /**
     * 科目情報を更新する
     *
     * @param subject 更新後の科目
     * @param result バリデーション結果
     * @return 科目一覧
     */
    @PostMapping("/subjects/update")
    public String updateSubject(
            @Valid @ModelAttribute Subject subject,
            BindingResult result) {

        if (result.hasErrors()) {
            return "subjects/form";
        }

        subjectService.updateSubject(subject);

        return "redirect:/subjects";
    }

    /**
     * 科目を削除する
     *
     * ※学生管理と同様、DB導入までは
     * return "subjects/list";
     * に変更しても構いません。
     *
     * @param id 科目ID
     * @param model Viewへ渡すデータ
     * @return 科目一覧
     */
    @GetMapping("/subjects/delete/{id}")
    public String deleteSubject(
            @PathVariable Long id,
            Model model) {

        subjectService.deleteSubject(id);

        model.addAttribute(
                "subjects",
                subjectService.getAllSubjects());

        return "subjects/list";
    }
}