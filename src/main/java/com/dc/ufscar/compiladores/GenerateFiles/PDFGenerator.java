package com.dc.ufscar.compiladores.GenerateFiles;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;

//import java.awt.image.BufferedImage;
import java.io.File;

public class PDFGenerator {
    public static void generatePDF(double a, double b, String fileName) {
        try (PDDocument document = new PDDocument()) {
            PDPage page = new PDPage();
            document.addPage(page);

            JFreeChart graphImage = MathGraph.createGraph(a, b);

            // Save the image as a temporary file
            File tempFile = File.createTempFile("tempGraph", ".png");
            ChartUtils.saveChartAsPNG(tempFile, graphImage, 600, 400);
            PDImageXObject pdImage = PDImageXObject.createFromFile(tempFile.getAbsolutePath(), document);
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            float pageWidth = page.getMediaBox().getWidth();
            float pageHeight = page.getMediaBox().getHeight();
            float imageWidth = pdImage.getWidth();
            float imageHeight = pdImage.getHeight();
            float x = (pageWidth - imageWidth) / 2;
            float y = (pageHeight - imageHeight) / 2;

            contentStream.drawImage(pdImage, x,(float) y+y/2);
            contentStream.close();

            document.save(fileName+".pdf");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

