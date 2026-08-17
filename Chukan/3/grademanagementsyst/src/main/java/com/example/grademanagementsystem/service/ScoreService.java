package com.example.grademanagementsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.example.grademanagementsystem.model.Score;

/**
 * 成績情報を管理するサービスクラス
 */
@Service
public class ScoreService {

    /** 成績一覧 */
    private final List<Score> scores = new ArrayList<>();

    /** 次に割り当てる成績ID */
    private Long nextId = 1L;

    /**
     * コンストラクタ
     */
    public ScoreService() {
        addScore(new Score(
                null,
                1L,
                1L,
                95,
                null,
                LocalDate.now()));

        addScore(new Score(
                null,
                1L,
                2L,
                88,
                null,
                LocalDate.now()));

        addScore(new Score(
                null,
                2L,
                1L,
                76,
                null,
                LocalDate.now()));
    }

    /**
     * 成績を登録する
     *
     * @param score 登録する成績
     */
    public void addScore(Score score) {
        if (score.getScore() < 0 || score.getScore() > 100) {
            return;
        }
        score.setId(nextId++);
        score.setGrade(calculateGrade(score.getScore()));
        scores.add(score);
    }

    /**
     * 成績一覧を取得する
     *
     * @return 成績一覧
     */
    public List<Score> getAllScores() {
        return scores;
    }

    /**
     * IDから成績を検索する
     *
     * @param id 成績ID
     * @return 成績情報
     */
    public Score findScoreById(Long id) {
        for (Score score : scores) {
            if (score.getId().equals(id)) {
                return score;
            }
        }
        return null;
    }

    /**
     * 成績情報を更新する
     *
     * @param score 更新後の成績
     */
    public void updateScore(Score score) {
        Score target = findScoreById(score.getId());
        if (target == null) {
            return;
        }
        target.setStudentId(score.getStudentId());
        target.setSubjectId(score.getSubjectId());
        target.setScore(score.getScore());
        target.setGrade(calculateGrade(score.getScore()));
        target.setExamDate(score.getExamDate());
    }

    /**
     * 成績を削除する
     *
     * @param id 成績ID
     */
    public void deleteScore(Long id) {
        Score score = findScoreById(id);
        if (score != null) {
            scores.remove(score);
        }
    }

    /**
     * 点数から評価を算出する
     *
     * @param score 点数
     * @return 評価
     */
    private String calculateGrade(int score) {
        if (score >= 90) {
            return "A";
        }
        if (score >= 80) {
            return "B";
        }
        if (score >= 70) {
            return "C";
        }
        if (score >= 60) {
            return "D";
        }
        return "F";
    }

    /**
     * 平均点
     * 
     * @return
     */
    public double getAverageScore() {
        if (scores.isEmpty()) {
            return 0;
        }
        int total = 0;
        for (Score score : scores) {
            total += score.getScore();
        }
        return (double) total / scores.size();
    }

    /**
     * 最高点
     * 
     * @return
     */
    public int getMaxScore() {
        int max = 0;
        for (Score score : scores) {
            if (score.getScore() > max) {
                max = score.getScore();
            }
        }
        return max;
    }

    /**
     * 最低点
     */
    public int getMinScore() {
        if (scores.isEmpty()) {
            return 0;
        }
        int min = scores.get(0).getScore();
        for (Score score : scores) {
            if (score.getScore() < min) {
                min = score.getScore();
            }
        }
        return min;
    }
    /**
     * 学生ごとの平均点を取得する
     *
     * @return 学生IDをキー、平均点を値とするMap
     */
    public java.util.Map<Long, Double> getAverageScoreByStudent() {

        java.util.Map<Long, Integer> totalMap = new java.util.HashMap<>();
        java.util.Map<Long, Integer> countMap = new java.util.HashMap<>();

        for (Score score : scores) {
            Long studentId = score.getStudentId();
            totalMap.put(
                    studentId,
                    totalMap.getOrDefault(studentId, 0) + score.getScore()
            );
            countMap.put(
                    studentId,
                    countMap.getOrDefault(studentId, 0) + 1
            );
        }
        java.util.Map<Long, Double> result = new java.util.HashMap<>();
        for (Long studentId : totalMap.keySet()) {
            double average =
                    (double) totalMap.get(studentId)
                    / countMap.get(studentId);
            result.put(studentId, average);
        }
        return result;
    }
    /**
     * 科目ごとの平均点を取得する
     *
     * @return 科目IDをキー、平均点を値とするMap
     */
    public java.util.Map<Long, Double> getAverageScoreBySubject() {
        java.util.Map<Long, Integer> totalMap = new java.util.HashMap<>();
        java.util.Map<Long, Integer> countMap = new java.util.HashMap<>();
        for (Score score : scores) {
            Long subjectId = score.getSubjectId();
            totalMap.put(
                    subjectId,
                    totalMap.getOrDefault(subjectId, 0) + score.getScore()
            );
            countMap.put(
                    subjectId,
                    countMap.getOrDefault(subjectId, 0) + 1
            );
        }
        java.util.Map<Long, Double> result = new java.util.HashMap<>();
        for (Long subjectId : totalMap.keySet()) {
            double average =
                    (double) totalMap.get(subjectId)
                    / countMap.get(subjectId);
            result.put(subjectId, average);
        }
        return result;
    }
    /**
     * 学生IDと科目IDで成績を検索する
     *
     * @param studentId 学生ID
     * @param subjectId 科目ID
     * @return 検索条件に一致した成績一覧
     */
    public List<Score> searchScores(Long studentId, Long subjectId) {

        List<Score> result = new ArrayList<>();

        for (Score score : scores) {
            // 学生IDの条件を確認
            if (studentId != null && !studentId.equals(score.getStudentId())) {
                continue;
            }
            // 科目IDの条件を確認
            if (subjectId != null && !subjectId.equals(score.getSubjectId())) {
                continue;
            }
            result.add(score);
        }
        return result;
    }
    /**
     * 学生別の平均点ランキングを取得する
     *
     * @return 平均点の高い順に並んだ学生IDと平均点の一覧
     */
    public List<Map.Entry<Long, Double>> getStudentRanking() {

        Map<Long, Double> averageScores = getAverageScoreByStudent();

        List<Map.Entry<Long, Double>> ranking =
                new ArrayList<>(averageScores.entrySet());

        ranking.sort(
                (entry1, entry2) ->
                        Double.compare(entry2.getValue(), entry1.getValue())
        );

        return ranking;
    }
}