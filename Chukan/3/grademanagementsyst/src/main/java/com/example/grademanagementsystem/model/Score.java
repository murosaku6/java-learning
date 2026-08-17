package com.example.grademanagementsystem.model;

import java.time.LocalDate;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
 * 成績情報を管理するモデルクラス
 */
public class Score {

    /** 成績ID */
    private Long id;

    /** 学生ID */
    @NotNull(message = "学生を選択してください")
    private Long studentId;

    /** 科目ID */
    @NotNull(message = "科目を選択してください")
    private Long subjectId;

    /** 点数 */
    @Min(value = 0, message = "0以上を入力してください")
    @Max(value = 100, message = "100以下を入力してください")
    private int score;

    /** 評価(A～F) */
    private String grade;

    /** 試験日 */
    @NotNull(message = "試験日を入力してください")
    private LocalDate examDate;

    /**
     * デフォルトコンストラクタ
     */
    public Score() {
        this.examDate = LocalDate.now();
    }

    /**
     * コンストラクタ
     */
    public Score(Long id, Long studentId, Long subjectId, 
        int score, String grade, LocalDate examDate) {
            this.id = id;
            this.studentId = studentId;
            this.subjectId = subjectId;
            this.score = score;
            this.grade = grade;
            this.examDate = examDate;
    }

    // Getter

    public Long getId() {
        return id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public Long getSubjectId() {
        return subjectId;
    }

    public int getScore() {
        return score;
    }

    public String getGrade() {
        return grade;
    }

    public LocalDate getExamDate() {
        return examDate;
    }

    // Setter

    public void setId(Long id) {
        this.id = id;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setExamDate(LocalDate examDate) {
        this.examDate = examDate;
    }

    @Override
    public String toString() {
        return "Score{" + "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", score=" + score +
                ", grade='" + grade + '\'' +
                ", examDate=" + examDate + '}';
    }
}