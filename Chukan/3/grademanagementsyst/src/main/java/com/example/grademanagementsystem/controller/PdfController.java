package com.example.grademanagementsystem.controller;

import com.example.grademanagementsystem.service.StudentService;
import org.openpdf.text.Document;
import org.openpdf.text.Font;
import org.openpdf.text.PageSize;
import org.openpdf.text.Paragraph;
import org.openpdf.text.pdf.BaseFont;
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

/**
 * PDF出力画面およびPDF生成を制御するコントローラー
 */
@Controller
public class PdfController {

    /** 学生管理サービス */
    private final StudentService studentService;

    /**
     * コンストラクタ
     *
     * @param studentService 学生管理サービス
     */
    @Autowired
    public PdfController(StudentService studentService) {
        this.studentService = studentService;
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

        // PDF作成終了
        document.close();

        byte[] pdfBytes =
                outputStream.toByteArray();

        // PDFをブラウザに表示
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "inline; filename=\"student_" + id + ".pdf\"")
                .contentType(
                        MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}