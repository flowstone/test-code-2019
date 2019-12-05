package me.xueyao.utils;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

/**
 * PDF工具类
 * @author Simon.Xue
 * @date 2019-12-05 09:38
 **/
public class PDFUtil {
    public static void main(String[] args) throws Exception {
        PdfDocument pdf = new PdfDocument(new PdfWriter("/Users/simonxue/Desktop/1.pdf"));
        Document document = new Document(pdf);
        String line = "Hello!Welcome to iTextPdf";
        document.add(new Paragraph(line));
        document.close();
    }
}
