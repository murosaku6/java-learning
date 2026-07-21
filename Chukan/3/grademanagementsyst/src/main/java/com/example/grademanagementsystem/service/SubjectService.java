package com.example.grademanagementsystem.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.grademanagementsystem.model.Subject;

/**
 * 科目情報を管理するサービスクラス
 */
@Service
public class SubjectService {

    /** 科目一覧 */
    private final List<Subject> subjects = new ArrayList<>();

    /** 次に割り当てる科目ID */
    private Long nextId = 1L;

    /**
     * コンストラクタ
     */
    public SubjectService() {

        addSubject(new Subject(
                null,
                "JP",
                "国語",
                2));

        addSubject(new Subject(
                null,
                "MT",
                "数学",
                2));

        addSubject(new Subject(
                null,
                "EN",
                "英語",
                2));
    }

    /**
     * 科目を登録する
     *
     * @param subject 登録する科目
     */
    public void addSubject(Subject subject) {

        subject.setId(nextId);
        nextId++;

        subjects.add(subject);
    }

    /**
     * 科目一覧を取得する
     *
     * @return 科目一覧
     */
    public List<Subject> getAllSubjects() {
        return subjects;
    }

    /**
     * IDから科目を検索する
     *
     * @param id 科目ID
     * @return 科目情報
     */
    public Subject findSubjectById(Long id) {

        for (Subject subject : subjects) {

            if (subject.getId().equals(id)) {
                return subject;
            }
        }

        return null;
    }

    /**
     * 科目情報を更新する
     *
     * @param subject 更新後の科目
     */
    public void updateSubject(Subject subject) {

        Subject target = findSubjectById(subject.getId());

        if (target == null) {
            return;
        }

        target.setSubjectCode(subject.getSubjectCode());
        target.setSubjectName(subject.getSubjectName());
        target.setCredits(subject.getCredits());
    }

    /**
     * 科目を削除する
     *
     * @param id 科目ID
     */
    public void deleteSubject(Long id) {

        Subject subject = findSubjectById(id);

        if (subject != null) {
            subjects.remove(subject);
        }
    }
}