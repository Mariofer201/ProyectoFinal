package co.edu.unbosque.Proyectos.model;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
public class PdfGenerator {
	 public static void generatePDF(String filename, String content) {
	        Document document = new Document();
	        try {
	            PdfWriter.getInstance(document, new FileOutputStream(filename));
	            document.open();
	            document.add(new Paragraph(content));
	            document.close();
	            System.out.println("PDF generado exitosamente.");
	        } catch (DocumentException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
	}

