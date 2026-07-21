package com.example.grademanagementsystem.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.grademanagementsystem.model.Student;

/**
 * 学生情報を管理するサービスクラス
 */
@Service
public class StudentService {

    /** 学生一覧 */
    private final List<Student> students = new ArrayList<>();

    /** 次に割り当てる学生ID */
    private Long nextId = 1L;

    public StudentService() {
        addStudent(new Student(
                null,
                "S001",
                "山田 太郎",
                1,
                "A"));

        addStudent(new Student(
                null,
                "S002",
                "佐藤 花子",
                2,
                "B"));
    }

    /**
     * 学生を登録する
     *
     * @param student 登録する学生
     */
    public void addStudent(Student student) {
        student.setId(nextId);
        nextId++;
        students.add(student);
    }

    /**
     * 学生一覧を取得する
     *
     * @return 学生一覧
     */
    public List<Student> getAllStudents() {
        return students;
    }

    /**
     * IDから学生を検索する
     *
     * @param id 学生ID
     * @return 学生情報
     */
    public Student findStudentById(Long id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    /**
     * 学生情報を更新する
     *
     * @param student 更新後の学生情報
     */
    public void updateStudent(Student student) {
        Student target = findStudentById(student.getId());
        if (target == null) {
            return;
        }
        target.setStudentNumber(student.getStudentNumber());
        target.setName(student.getName());
        target.setGrade(student.getGrade());
        target.setClassName(student.getClassName());
    }

    /**
     * 学生を削除する
     *
     * @param id 学生ID
     */
    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        if (student != null) {
            students.remove(student);
        }
    }
}