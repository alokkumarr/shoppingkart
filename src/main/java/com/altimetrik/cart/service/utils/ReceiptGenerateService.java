package com.altimetrik.cart.service.utils;

import com.altimetrik.cart.model.response.CheckOutResponse;
import com.altimetrik.cart.model.response.ProductItem;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Service
public class ReceiptGenerateService {
  private static final Logger LOGGER = LoggerFactory.getLogger(ReceiptGenerateService.class);

  public static void main(String[] args) {
    ReceiptGenerateService service = new ReceiptGenerateService();
    //System.out.println(service.getReceipt("test.pdf", null));
  }

  public ByteArrayInputStream getReceipt(String name, CheckOutResponse response) {
    Document document = new Document();
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try {

      PdfPTable table = new PdfPTable(9);
      table.setWidthPercentage(10);
      table.setWidths(new int[]{1,3,3,3,3,3,3,3,3});

      Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

      PdfPCell hcell;
      hcell = new PdfPCell(new Phrase("S.No.", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Title", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Description", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Price", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Qty", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("VAT", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Sales Tax", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Import Duties", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);

      hcell = new PdfPCell(new Phrase("Total Price", headFont));
      hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
      table.addCell(hcell);


      if (response != null && response.getReceiptDetails() != null) {
        List<ProductItem> itemList = response.getReceiptDetails().getProductItems();
        int sn = 1;
        for (ProductItem productItem : itemList) {

          // write data in cells starts
          PdfPCell cell;
          cell = new PdfPCell(new Phrase(String.valueOf(sn)));
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_CENTER);
          table.addCell(cell);

          cell = new PdfPCell(new Phrase(productItem.getName()));
          cell.setPaddingLeft(5);
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);

          cell = new PdfPCell(new Phrase(productItem.getPrice().toString()));
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          cell.setPaddingRight(5);
          table.addCell(cell);

          cell = new PdfPCell(new Phrase(productItem.getQty().toString()));
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          cell.setPaddingRight(5);
          table.addCell(cell);

          cell = new PdfPCell(new Phrase(productItem.getVat().toString()));
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          cell.setPaddingRight(5);
          table.addCell(cell);

          cell = new PdfPCell(new Phrase(productItem.getTax().toString()));
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          cell.setPaddingRight(5);
          table.addCell(cell);

          cell = new PdfPCell(new Phrase(productItem.getImportDuty().toString()));
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          cell.setPaddingRight(5);
          table.addCell(cell);

          cell = new PdfPCell(new Phrase(productItem.getTotalPrice().toString()));
          cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          cell.setPaddingRight(5);
          table.addCell(cell);

          sn++;
          // write data in cells ends
        }
      }
      PdfWriter.getInstance(document, out);
      document.open();
      document.add(table);

      // close the document
      document.close();
    } catch (DocumentException e) {
      LOGGER.error("Error while generating the pdf file {}", e.getMessage());
    }
    return new ByteArrayInputStream(out.toByteArray());
  }
}
