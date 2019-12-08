package org.vss.springmvc.services;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;
import org.vss.springmvc.model.Phone;
import org.vss.springmvc.model.User;

public class PdfView extends AbstractPdfView {

    @Override
    public void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
                                 HttpServletRequest request, HttpServletResponse response) throws Exception {

        @SuppressWarnings("unchecked")
        List<User> users = (List<User>) model.get("usersList");

        PdfPTable table = new PdfPTable(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        table.addCell("User Name");
        table.addCell("Phone");
        table.addCell("Phone Company");

        for (User user : users) {
            table.addCell(user.getName() + " " + user.getSurname());
            table.addCell("");
            table.addCell("");
            for (Phone phone : user.getPhoneNumbers()) {
                table.addCell("");
                table.addCell(phone.getNumber());
                table.addCell(phone.getCompanyName());
            }
        }
        document.add(table);

    }

}
