// package com.fukuoka.controller;

// import java.io.ByteArrayOutputStream;
// import java.util.List;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.http.HttpHeaders;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.MediaType;
// import org.springframework.http.ResponseEntity;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RestController;
// import org.thymeleaf.TemplateEngine;
// import org.thymeleaf.context.Context;
// import org.xhtmlrenderer.pdf.ITextRenderer;

// import com.lowagie.text.pdf.BaseFont;

// @RestController
// @RequestMapping("/api/report")
// public class ResultPdfController {

//     @Autowired
//     private TemplateEngine templateEngine;

//     // 条件が不要なら required=false にしておくと空ボディでも通る
//     @PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
//     public ResponseEntity<byte[]> generatePdf(@RequestBody(required = false) ReportCondition cond) throws Exception {
//         // 1) データ取得（ダミー）
//         List<Map<String, String>> rows = fetchRows(cond);

//         // 2) HTML生成
//         Context ctx = new Context();
//         ctx.setVariable("rows", rows);
//         String html = templateEngine.process("hoge", ctx);

//         // 3) PDF生成
//         ByteArrayOutputStream out = new ByteArrayOutputStream();
//         ITextRenderer renderer = new ITextRenderer();

//         // フォントは URL 文字列で渡すか、InputStreamで
//         var fontUrl = getClass().getClassLoader().getResource("fonts/NotoSansJP-Regular.ttf");
//         renderer.getFontResolver().addFont(fontUrl.toExternalForm(), BaseFont.IDENTITY_H, BaseFont.EMBEDDED);

//         renderer.setDocumentFromString(html /*, baseUrl 可 */);
//         renderer.layout();
//         renderer.createPDF(out);

//         // 4) レスポンス
//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.APPLICATION_PDF);
//         headers.setContentDispositionFormData("attachment", "report.pdf");
//         return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
//     }

//     private List<Map<String,String>> fetchRows(ReportCondition cond) {
//         return List.of(
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "11111111", "value", "11111111111"),
//             Map.of("name", "22222222222", "value", "222222222")
//         );
//     }

//     public static class ReportCondition {
//         // 絞り込み条件があればプロパティ定義
//     }
// }

package com.fukuoka.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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

    @PostMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generatePdf(@RequestBody(required = false) ReportCondition cond) throws Exception {
        
        // 1) データ取得
        List<Map<String, String>> rows = fetchRows(cond);

        // 2) HTML生成
        Context ctx = new Context();
        ctx.setVariable("rows", rows);
        String html = templateEngine.process("hoge", ctx); // テンプレート名を明確に


        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        System.out.println(html);

        System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa");

        // 3) PDF生成
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        //renderer.getSharedContext().setPrint(true);
        //renderer.getSharedContext().setInteractive(false);

        // フォント設定（日本語対応）
        var fontUrl = getClass().getClassLoader().getResource("fonts/NotoSansJP-Regular.ttf");
        if (fontUrl != null) {
            renderer.getFontResolver().addFont(
                fontUrl.toExternalForm(), 
                BaseFont.IDENTITY_H, 
                BaseFont.EMBEDDED
            );
        }

        // HTMLをPDFに変換
        renderer.setDocumentFromString(html);
        renderer.layout();
        renderer.createPDF(out);
        renderer.finishPDF();

        // 4) レスポンス設定
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_PDF);
        headers.setContentDispositionFormData("attachment", "report.pdf");
        headers.setCacheControl("no-cache, no-store, must-revalidate");
        headers.setPragma("no-cache");
        headers.setExpires(0);

        return new ResponseEntity<>(out.toByteArray(), headers, HttpStatus.OK);
    }

    private List<Map<String, String>> fetchRows(ReportCondition cond) {
        // 実際の業務ではDBから取得
        return List.of(
            Map.of("name", "項目1", "value", "値1"),
            Map.of("name", "項目2", "value", "値2"),
            Map.of("name", "項目3", "value", "値3"),
            Map.of("name", "項目4", "value", "値4"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
                        Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),
            Map.of("name", "項目5", "value", "値5"),

            Map.of("name", "項目5", "value", "値5"),

            // ... 大量のデータ
            Map.of("name", "項目46", "value", "値46")
        );
    }

    public static class ReportCondition {
        // 検索条件のプロパティ
        private String keyword;
        private String dateFrom;
        private String dateTo;
        
        // getter/setter
        public String getKeyword() { return keyword; }
        public void setKeyword(String keyword) { this.keyword = keyword; }
        public String getDateFrom() { return dateFrom; }
        public void setDateFrom(String dateFrom) { this.dateFrom = dateFrom; }
        public String getDateTo() { return dateTo; }
        public void setDateTo(String dateTo) { this.dateTo = dateTo; }
    }
}

