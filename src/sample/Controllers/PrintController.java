package sample.Controllers;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import sample.Models.DAO.TaskDAO;
import sample.Models.Task;
import sample.MySQL;


import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;


public class PrintController implements Initializable {
    @FXML
    JFXButton print, printall;
    @FXML
    JFXDatePicker fechainicio, fechafin;

    public static final String DEST = "reports/ReportTasksPartial.pdf";
    public static final String DEST2 = "reports/ReportTasksTotal.pdf";
    TaskDAO taskDAO = new TaskDAO(MySQL.getConnection());


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        print.setOnAction(listener);
        printall.setOnAction(listener);
    }

   EventHandler<ActionEvent> listener = new EventHandler<ActionEvent>() {
       @Override
       public void handle(ActionEvent event) {
           if (event.getSource() == print) {
               try {
                   createDocument(DEST);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
           else {
               try {
                   createDocumentall(DEST2);
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }
   };

    public void createDocument(String dest) throws IOException{
        Date ini = Date.from(fechainicio.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        Date fin = Date.from(fechafin.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(new float[]{6, 6, 6, 6, 6,6});
        table.setWidthPercent(100);

        List<Task> List = taskDAO.findAll();
        for(int i = 0; i<List.size();i++){

            Task t = List.get(i);

            if(ini.before(t.getStarFrom()) && fin.after(t.getStarFrom())){
                table.addCell(new Cell().add(new Paragraph(""+t.getTitle()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(""+t.getCategory()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(""+t.getNotes()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(""+t.getEstimated_Time()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(""+t.getPriority()).setFont(font)));
                table.addCell(new Cell().add(new Paragraph(""+t.getStarFrom()).setFont(font)));
            }


        }

        //add pharagraph
        document.add(new Paragraph("Reporte de Tareas del  "+ini+"  al   "+fin).setFont(font));

        document.add(table);



        //Close document
        document.close();

        showMessage();
    }

    public void showMessage(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Exito");
        alert.setContentText("La accion se completo con exito");
        alert.show();
    }


    public void createDocumentall(String dest) throws IOException{
        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);

        Document document = new Document(pdf, PageSize.A4.rotate());
        document.setMargins(20, 20, 20, 20);

        PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA);
        PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD);
        Table table = new Table(new float[]{6, 6, 6, 6, 6,6});
        table.setWidthPercent(100);

        List<Task> List = taskDAO.findAll();
        for(int i = 0; i<List.size();i++){

            Task t = List.get(i);

            table.addCell(new Cell().add(new Paragraph(""+t.getTitle()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+t.getCategory()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+t.getNotes()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+t.getEstimated_Time()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+t.getPriority()).setFont(font)));
            table.addCell(new Cell().add(new Paragraph(""+t.getStarFrom()).setFont(font)));
        }

        //add pharagraph
        document.add(new Paragraph("Reporte de Tareas totales"));

        document.add(table);



        //Close document
        document.close();

        showMessage();
    }

}

