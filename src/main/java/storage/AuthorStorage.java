package storage;

import exception.AuthorNotFoundException;
import model.Author;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class AuthorStorage {
    private Author[] array = new Author[10];
    private int size = 0;

    public void add(Author author) {
        if (size == array.length) {
            extend();
        }
        array[size++] = author;
    }

    private void extend() {
        Author[] temp = new Author[array.length + 10];
        System.arraycopy(array, 0, temp, 0, size);
        array = temp;
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(i + "." + array[i] + " ");
        }
    }

    public int getSize() {
        return size;
    }


    public void booksByAuthorName(String author) {
        for (int i = 0; i < size; i++) {
            if (array[i].getName().equals(author)) {
                System.out.println(array[i]);
            } else {
                System.out.println("Please input correct name");
            }
        }
    }

    public void delete(int index) {
        if (index >= 0 && index < size) {
            for (int i = index; i < size; i++) {
                array[index] = array[index + 1];
            }
            size--;
            System.out.println("Author deleted");
        } else {
            System.out.println("Index out of bound");
        }
    }

    public Author getAuthorByIndex(int authorIndex) throws AuthorNotFoundException {
        if (authorIndex < 0 || authorIndex > size) {
            throw new AuthorNotFoundException();
        } else {
            if (array[authorIndex] == null) {
                throw new AuthorNotFoundException();
            }
            return array[authorIndex];
        }
    }

    public void writeAuthorsToExcel(String fileDir) throws IOException {
        File directory = new File(fileDir);
        if (directory.isFile()) {
            throw new RuntimeException("fileDir must be a directory");
        }
        File excelFile = new File(directory, "authors_" + System.currentTimeMillis() + ".xlsx");
        excelFile.createNewFile();
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        Row headerRow = sheet.createRow(0);
        Cell cellName = headerRow.createCell(0);
        cellName.setCellValue("name");

        Cell cellSurname = headerRow.createCell(1);
        cellSurname.setCellValue("surname");

        Cell cellEmail = headerRow.createCell(2);
        cellEmail.setCellValue("email");

        Cell cellGender = headerRow.createCell(3);
        cellGender.setCellValue("gender");

        for (int i = 0; i < size; i++) {
            Author author = array[i];
            Row row = sheet.createRow(i + 1);
            row.createCell(0).setCellValue(author.getName());
            row.createCell(1).setCellValue(author.getSurname());
            row.createCell(2).setCellValue(author.getEmail());
            row.createCell(3).setCellValue(author.getGender().name());
        }
        workbook.write(Files.newOutputStream(excelFile.toPath()));
        System.out.println("File has been successfully uploaded.");
    }

}



