package com.example.grademanagementsystem.model;
/**
 * 科目情報を管理するモデルクラス
 */
public class Subject {
    /** 科目ID */
    private Long id;
    /** 科目名 */
    private String subjectName;
    /** 担当教員名 */
    private String teacherName;
    /**
     * デフォルトコンストラクタ
     */
    public Subject() {
    }

    /**
     * 科目情報を生成する
     *
     * @param id 科目ID
     * @param subjectName 科目名
     * @param teacherName 担当教員名
     */
    public Subject(Long id, String subjectName, String teacherName) {
        this.id = id;
        this.subjectName = subjectName;
        this.teacherName = teacherName;
    }

    /**
     * 科目IDを取得する
     *
     * @return 科目ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 科目IDを設定する
     *
     * @param id 科目ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 科目名を取得する
     *
     * @return 科目名
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     * 科目名を設定する
     *
     * @param subjectName 科目名
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     * 担当教員名を取得する
     *
     * @return 担当教員名
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 担当教員名を設定する
     *
     * @param teacherName 担当教員名
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    @Override
    public String toString() {
        return "Subject{" + "id=" + id + ", subjectName='" + subjectName +
                '\'' + ", teacherName='" + teacherName +
                '\'' + '}';
    }
}