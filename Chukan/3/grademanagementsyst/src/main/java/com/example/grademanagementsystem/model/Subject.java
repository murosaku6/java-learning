package com.example.grademanagementsystem.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

/**
 * 科目情報を管理するモデルクラス
 */
public class Subject {

    /** 科目ID */
    private Long id;

    /** 科目コード */
    @NotBlank(message = "科目コードを入力してください")
    private String subjectCode;

    /** 科目名 */
    @NotBlank(message = "科目名を入力してください")
    private String subjectName;

    /** 単位数 */
    @Min(value = 1, message = "単位数は1以上を入力してください")
    private int credits;

    /**
     * デフォルトコンストラクタ
     */
    public Subject() {
    }

    /**
     * コンストラクタ
     */
    public Subject(Long id,
                   String subjectCode,
                   String subjectName,
                   int credits) {

        this.id = id;
        this.subjectCode = subjectCode;
        this.subjectName = subjectName;
        this.credits = credits;
    }

    // Getter

    public Long getId() {
        return id;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCredits() {
        return credits;
    }

    // Setter

    public void setId(Long id) {
        this.id = id;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    @Override
    public String toString() {
        return "Subject{" +
                "id=" + id +
                ", subjectCode='" + subjectCode + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", credits=" + credits +
                '}';
    }
}