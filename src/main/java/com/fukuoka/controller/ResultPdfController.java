package com.fukuoka.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

@RestController
@RequestMapping("/api/report")
public class ResultPdfController {

    @Autowired
    private TemplateEngine templateEngine;

    // 条件が不要なら required=false にしておくと空ボディでも通る
    @PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestBody(required = false) ReportCondition cond) throws Exception {
        // 1) データ取得（ダミー）
        List<Map<String, String>> rows = fetchRows(cond);

        // 2) HTML生成
        Context ctx = new Context();
        ctx.setVariable("rows", rows);
        String html = templateEngine.process("report", ctx);

        // 3) PDF生成
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();

        // フォントは URL 文字列で渡すか、InputStreamで
        var fontUrl = getClass().getClassLoader().getResource("fonts/NotoSansJP-Regular.ttf");
        renderer.getFontResolver().addFont(fontUrl.toExternalForm(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

        renderer.setDocumentFromString(html /*, baseUrl 可 */);
        renderer.layout();
        renderer.createPDF(out);

        // 4) レスポンス
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    private List<Map<String,String>> fetchRows(ReportCondition cond) {
        return List.of(
            Map.of("name", "cccccccccccccccc", "value", "cccccccccccccc"),
            Map.of("name", "cccccccccccccc", "value", "ccccccccccccc")
        );
    }

    public static class ReportCondition {
        // 絞り込み条件があればプロパティ定義
    }
}


