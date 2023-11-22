package Adapter;


import DAO.BookDAO;
import Model.Book;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ListToExcelAdapter implements DataSource {
    private BookDAO bookDAO;

    public  void exportToExcel(String filePath) {
        List<Book> books = bookDAO.getData();

        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Books");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Book ID");
            headerRow.createCell(1).setCellValue("Title");
            headerRow.createCell(2).setCellValue("Year");
            headerRow.createCell(3).setCellValue("Author");
            headerRow.createCell(4).setCellValue("Available");
            headerRow.createCell(5).setCellValue("Genre");

            int rowNum = 1;
            for (Book book : books) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(book.getId());
                row.createCell(1).setCellValue(book.getTitle());
                row.createCell(2).setCellValue(book.getYear());
                row.createCell(3).setCellValue(book.getAuthor());
                row.createCell(4).setCellValue(book.isAvailable());
                row.createCell(5).setCellValue(book.getGenre());

            }

            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

            System.out.println("Data exported to Excel successfully.");

        } catch (IOException e) {
            System.out.println(e);
        }
    }


    @Override
    public List<Book> getData() {
        return null;
    }
}
