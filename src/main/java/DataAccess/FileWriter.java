package DataAccess;

import BLL.MenuItem;
import BLL.Order;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;

public class FileWriter {
    /**
     * This method creates the bill for an order
     *
     * @param order    - the order on which the bill will be created
     * @param menuItem - the ordered products
     */
    public void billPDF(Order order, MenuItem menuItem) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Document doc = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\ANUL2\\TP\\LAB\\Assigment4\\pt2022_30227_adrian_ciu_assignment_4\\Bill" + Order.curId + ".pdf"));
            doc.open();
            doc.add(new Paragraph("Client id:" + order.getClientId()));
            doc.add(new Paragraph("Order id:" + order.getOrderId()));
            doc.add(new Paragraph("Order date:" + formatter.format(order.getOrderDate())));
            for (MenuItem item : menuItem.getMenuItems())
                doc.add(new Paragraph(item.toString()));
            menuItem.computePrice();
            doc.add(new Paragraph("Total price: " + menuItem.getPrice() + "$"));
            doc.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates the bill for an order
     * @param report    - the send report
     * @param repNumber - the number of the report
     */
    public void reportPDF(String report, int repNumber) {
        Document doc = new Document();
        try {
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("D:\\ANUL2\\TP\\LAB\\Assigment4\\pt2022_30227_adrian_ciu_assignment_4\\Report" + repNumber + ".pdf"));
            doc.open();
            doc.add(new Paragraph(report));
            doc.close();
            writer.close();
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
