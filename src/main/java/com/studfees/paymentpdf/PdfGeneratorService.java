package com.studfees.paymentpdf;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;
import com.studfees.entity.StudentPayment;
import com.studfees.repository.StudentPaymentREpository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    @Autowired
    StudentPaymentREpository repo;

    public byte[] generateFeeReceipt(Long id) throws Exception {

        StudentPayment payment = repo.findById(id).orElse(null);

        if(payment == null) return null;

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document doc = new Document();
        PdfWriter.getInstance(doc, out);

        doc.open();
        
        PdfPTable headerTable = new PdfPTable(1);
        headerTable.setWidthPercentage(100);
        headerTable.setSpacingAfter(20f);
        
        PdfPCell headerCell = new PdfPCell();
        headerCell.setBackgroundColor(new Color(41, 128, 185)); // Blue background
        headerCell.setBorder(Rectangle.NO_BORDER);
        headerCell.setPadding(15f);
        
        Font headerFont = new Font(Font.HELVETICA, 24, Font.BOLD, Color.WHITE);
        Paragraph header = new Paragraph("STUDENT FEE RECEIPT", headerFont);
        header.setAlignment(Element.ALIGN_CENTER);
        headerCell.addElement(header);
        
        headerTable.addCell(headerCell);
        doc.add(headerTable);
        
        PdfPTable detailsTable = new PdfPTable(2);
        detailsTable.setWidthPercentage(100);
        detailsTable.setWidths(new float[]{2f, 3f});
        detailsTable.setSpacingBefore(10f);
        detailsTable.setSpacingAfter(20f);
        
        Font labelFont = new Font(Font.HELVETICA, 12, Font.BOLD, new Color(52, 73, 94));
        Font valueFont = new Font(Font.HELVETICA, 12, Font.NORMAL, new Color(44, 62, 80));
        
        addDetailRow(detailsTable, "Student ID", String.valueOf(payment.getId()), labelFont, valueFont, true);
        addDetailRow(detailsTable, "Total Fee", "₹ " + String.format("%.2f", payment.getTotalFee()), labelFont, valueFont, false);
        addDetailRow(detailsTable, "Paid Fee", "₹ " + String.format("%.2f", payment.getPaidFee()), labelFont, valueFont, true);
        addDetailRow(detailsTable, "Pending", "₹ " + String.format("%.2f", payment.getPendingFee()), labelFont, valueFont, false);
        addDetailRow(detailsTable, "Payment Date", String.valueOf(payment.getPaymentDate()), labelFont, valueFont, true);
        
        doc.add(detailsTable);
        
        Paragraph footer = new Paragraph("Thank you for your payment!", 
            new Font(Font.HELVETICA, 10, Font.ITALIC, new Color(127, 140, 141)));
        footer.setAlignment(Element.ALIGN_CENTER);
        footer.setSpacingBefore(30f);
        doc.add(footer);
        
        Paragraph line = new Paragraph("_".repeat(80), 
            new Font(Font.HELVETICA, 8, Font.NORMAL, new Color(189, 195, 199)));
        line.setAlignment(Element.ALIGN_CENTER);
        doc.add(line);
        
        doc.close();

        return out.toByteArray();
    }
    
    private void addDetailRow(PdfPTable table, String label, String value, 
                             Font labelFont, Font valueFont, boolean shade) {
        
        Color bgColor = shade ? new Color(236, 240, 241) : Color.WHITE;
        
        PdfPCell labelCell = new PdfPCell(new Phrase(label, labelFont));
        labelCell.setBackgroundColor(bgColor);
        labelCell.setBorder(Rectangle.NO_BORDER);
        labelCell.setPadding(10f);
        
        PdfPCell valueCell = new PdfPCell(new Phrase(value, valueFont));
        valueCell.setBackgroundColor(bgColor);
        valueCell.setBorder(Rectangle.NO_BORDER);
        valueCell.setPadding(10f);
        
        table.addCell(labelCell);
        table.addCell(valueCell);
    }
}
