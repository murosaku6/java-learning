package com.example.grademanagementsystem.controller;

import com.example.grademanagementsystem.model.Score;
import com.example.grademanagementsystem.model.Subject;
import com.example.grademanagementsystem.service.ScoreService;
import com.example.grademanagementsystem.service.StudentService;
import com.example.grademanagementsystem.service.SubjectService;

import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.BaseFont;
import org.openpdf.text.pdf.PdfPTable;
import org.openpdf.text.pdf.PdfWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.ByteArrayOutputStream;
import java.util.List;

/**
 * PDF出力画面およびPDF生成を制御するコントローラー
 */
@Controller
public class PdfController {

    /** 学生管理サービス */
    private final StudentService studentService;

    /** 成績管理サービス */
    private final ScoreService scoreService;

    /** 科目管理サービス */
    private final SubjectService subjectService;

    /**
     * コンストラクタ
     *
     * @param studentService 学生管理サービス
     * @param scoreService 成績管理サービス
     * @param subjectService 科目管理サービス
     */
    @Autowired
    public PdfController(
            StudentService studentService,
            ScoreService scoreService,
            SubjectService subjectService) {

        this.studentService = studentService;
        this.scoreService = scoreService;
        this.subjectService = subjectService;
    }

    /**
     * PDF出力用の学生一覧画面を表示する
     *
     * URL：/pdf/students
     *
     * @param model Viewへ渡すデータ
     * @return PDF出力用学生一覧画面
     */
    @GetMapping("/pdf/students")
    public String showPdfStudentList(Model model) {

        model.addAttribute(
                "students",
                studentService.getAllStudents());

        return "pdf/students";
    }

    /**
     * 指定した学生の成績表PDFを生成する
     *
     * URL：/pdf/students/{id}
     *
     * @param id 学生ID
     * @return PDFファイル
     * @throws Exception PDF生成時の例外
     */
    @GetMapping("/pdf/students/{id}")
    public ResponseEntity<byte[]> generateStudentPdf(
            @PathVariable Long id) throws Exception {

        // IDから学生を検索
        var student = studentService.findStudentById(id);

        // 学生が存在しない場合
        if (student == null) {
            return ResponseEntity.notFound().build();
        }

        // PDFをメモリ上に作成
        ByteArrayOutputStream outputStream =
                new ByteArrayOutputStream();

        Document document =
                new Document(PageSize.A4);

        PdfWriter.getInstance(document, outputStream);

        // 日本語フォントを読み込む
        ClassPathResource fontResource =
                new ClassPathResource(
                        "fonts/NotoSansJP-Regular.ttf");

        BaseFont baseFont =
                BaseFont.createFont(
                        fontResource.getURL().toString(),
                        BaseFont.IDENTITY_H,
                        BaseFont.EMBEDDED);

        Font font =
                new Font(baseFont, 12);

        Font titleFont =
                new Font(baseFont, 20, Font.BOLD);

        // PDF作成開始
        document.open();

        // タイトル
        document.add(
                new Paragraph("成績表", titleFont));

        // 学生情報
        document.add(
                new Paragraph(
                        "学生番号：" + student.getStudentNumber(),
                        font));

        document.add(
                new Paragraph(
                        "氏名：" + student.getName(),
                        font));

        document.add(
                new Paragraph(
                        "学年：" + student.getGrade(),
                        font));

        document.add(
                new Paragraph(
                        "クラス：" + student.getClassName(),
                        font));

        // 少し間隔を空ける
        document.add(new Paragraph(" ", font));

        // 成績一覧
        document.add(
                new Paragraph("成績一覧", font));

        // 学生の成績だけを抽出
        List<Score> studentScores = scoreService.getAllScores()
                .stream()
                .filter(score ->
                        id.equals(score.getStudentId()))
                .toList();

        // 成績表を作成
        PdfPTable table = new PdfPTable(5);

        table.setWidthPercentage(100);

        // ヘッダー
        table.addCell(
                new Paragraph("科目コード", font));

        table.addCell(
                new Paragraph("科目名", font));

        table.addCell(
                new Paragraph("得点", font));

        table.addCell(
                new Paragraph("評価", font));

        table.addCell(
                new Paragraph("試験日", font));

        // 成績データ
        for (Score score : studentScores) {

            // 成績に対応する科目を検索
            Subject subject = null;

            for (Subject currentSubject :
                    subjectService.getAllSubjects()) {

                if (currentSubject.getId()
                        .equals(score.getSubjectId())) {

                    subject = currentSubject;
                    break;
                }
            }

            // 科目が存在する場合
            if (subject != null) {

                table.addCell(
                        new Paragraph(
                                subject.getSubjectCode(),
                                font));

                table.addCell(
                        new Paragraph(
                                subject.getSubjectName(),
                                font));

            } else {

                table.addCell(
                        new Paragraph("-", font));

                table.addCell(
                        new Paragraph("-", font));
            }

            table.addCell(
                    new Paragraph(
                            String.valueOf(score.getScore()),
                            font));

            table.addCell(
                    new Paragraph(
                            score.getGrade(),
                            font));

            table.addCell(
                    new Paragraph(
                            String.valueOf(score.getExamDate()),
                            font));
        }

        // 成績がない場合
        if (studentScores.isEmpty()) {

            table.addCell(
                    new Paragraph(
                            "成績データがありません",
                            font));
        }

        // PDFへ表を追加
        document.add(table);

        // PDF作成終了
        document.close();

        byte[] pdfBytes =
                outputStream.toByteArray();

        // PDFをブラウザに表示
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"student_"
                                + id + ".pdf\"")
                .contentType(
                        MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}