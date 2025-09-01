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
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.pdf.BaseFont;

@Controller
public class PdfController {
    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/report")
    public String pdf(Model model) {
        List<Map<String, String>> rows = List.of(
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaa", "value", "bbbbbbbb"),
            Map.of("name", "aaaaaaaaaaaaaa", "value", "bbbbbbb")
        );
        model.addAttribute("rows", rows);
        return "report";
    }


    @GetMapping("/report/pdf")
    public ResponseEntity<byte[]> generatePdf() throws Exception {
        List<Map<String, String>> rows = List.of(
            Map.of("name", "cccccccccccccccc", "value", "cccccccccccccc"),
            Map.of("name", "cccccccccccccc", "value", "ccccccccccccc")
        );

        Context context = new Context();
        context.setVariable("rows", rows);
        String html = templateEngine.process("report", context);

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();

        String fontPath = getClass().getClassLoader().getResource("fonts/NotoSansJP-Regular.ttf").getPath();
        System.out.println("FONT PATH = " + fontPath);

        renderer.getFontResolver().addFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED);


        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(out);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("filename", "report.pdf");
        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    
















}
