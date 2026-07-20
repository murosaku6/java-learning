package com.example.grademanagementsystem.model;
/**
 * 成績情報を管理するモデルクラス
 */
public class Score {
    /** 成績ID */
    private Long id;
    /** 学生ID */
    private Long studentId;
    /** 科目ID */
    private Long subjectId;
    /** 点数 */
    private int score;
    /**
     * デフォルトコンストラクタ
     */
    public Score() {
    }

    /**
     * 成績情報を生成する
     *
     * @param id 成績ID
     * @param studentId 学生ID
     * @param subjectId 科目ID
     * @param score 点数
     */
    public Score(Long id, Long studentId, Long subjectId, int score) {
        this.id = id;
        this.studentId = studentId;
        this.subjectId = subjectId;
        this.score = score;
    }

    /**
     * 成績IDを取得する
     *
     * @return 成績ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 成績IDを設定する
     *
     * @param id 成績ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 学生IDを取得する
     *
     * @return 学生ID
     */
    public Long getStudentId() {
        return studentId;
    }

    /**
     * 学生IDを設定する
     *
     * @param studentId 学生ID
     */
    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    /**
     * 科目IDを取得する
     *
     * @return 科目ID
     */
    public Long getSubjectId() {
        return subjectId;
    }

    /**
     * 科目IDを設定する
     *
     * @param subjectId 科目ID
     */
    public void setSubjectId(Long subjectId) {
        this.subjectId = subjectId;
    }

    /**
     * 点数を取得する
     *
     * @return 点数
     */
    public int getScore() {
        return score;
    }

    /**
     * 点数を設定する
     *
     * @param score 点数
     */
    public void setScore(int score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Score{" + "id=" + id +
                ", studentId=" + studentId +
                ", subjectId=" + subjectId +
                ", score=" + score + '}';
    }
}