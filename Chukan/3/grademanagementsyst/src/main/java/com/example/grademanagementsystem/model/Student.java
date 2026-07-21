package com.example.grademanagementsystem.model;
/**
 * 学生情報を管理するモデルクラス
 */
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
public class Student {
    /** 学生ID */
    private Long id;
    /** 学籍番号 */
    @NotBlank(message = "学籍番号を入力してください")
    private String studentNumber;
    /** 学生氏名 */
    @NotBlank(message = "氏名を入力してください")
    private String name;
    /** 学年 */
    @Min(value = 1, message = "学年は1以上です。")
    @Max(value = 6, message = "学年は6以下です。")
    private int grade;
    /** クラス名 */
    @NotBlank(message = "クラスを入力してください")
    private String className;
    /**
     * デフォルトコンストラクタ
     */
    public Student() {
    }

    /**
     * 学生情報を生成する
     *
     * @param id 学生ID
     * @param studentNumber 学籍番号
     * @param name 氏名
     * @param grade 学年
     * @param className クラス名
     */
    public Student(Long id, String studentNumber, String name, int grade, String className) {
        this.id = id;
        this.studentNumber = studentNumber;
        this.name = name;
        this.grade = grade;
        this.className = className;
    }

    /**
     * 学生IDを取得する
     *
     * @return 学生ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 学生IDを設定する
     *
     * @param id 学生ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 学籍番号を取得する
     *
     * @return 学籍番号
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * 学籍番号を設定する
     *
     * @param studentNumber 学籍番号
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * 氏名を取得する
     *
     * @return 氏名
     */
    public String getName() {
        return name;
    }

    /**
     * 氏名を設定する
     *
     * @param name 氏名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 学年を取得する
     *
     * @return 学年
     */
    public int getGrade() {
        return grade;
    }

    /**
     * 学年を設定する
     *
     * @param grade 学年
     */
    public void setGrade(int grade) {
        this.grade = grade;
    }

    /**
     * クラス名を取得する
     *
     * @return クラス名
     */
    public String getClassName() {
        return className;
    }

    /**
     * クラス名を設定する
     *
     * @param className クラス名
     */
    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentNumber='" + studentNumber +
                '\'' + ", name='" + name +
                '\'' + ", grade=" + grade + ", className='" + className +
                '\'' + '}';
    }
}