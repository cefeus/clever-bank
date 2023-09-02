package service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import service.PdfService;

import java.io.FileOutputStream;
import java.io.IOException;

import static utils.constants.CheckConstants.FONT_PATH;

public class PdfServiceImpl implements PdfService {
    @Override
    public void formPdf(String text, String path) {
        var doc = new Document();
        Font font = FontFactory.getFont(FONT_PATH, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 10);

        try (var fileOuputStream = new FileOutputStream(path)) {
            PdfWriter.getInstance(doc, fileOuputStream);
            doc.open();
            text.lines().toList()
                    .forEach(line -> {
                        try {
                            doc.add(new Paragraph(line, font));
                        } catch (DocumentException e) {
                            throw new RuntimeException(e);
                        }
                    });
            doc.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }
    }
}
