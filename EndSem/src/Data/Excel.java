/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Kanav
 */
public class Excel {
    public static XSSFWorkbook createWorkbook(String name)
    {   
        System.out.println("Workbook method"+name + " " + new Frames.Login().user);
        String filepath = name + ".xlsx";
        XSSFWorkbook workbook = new XSSFWorkbook(); 
        File f = new File(filepath);
        try {
            
            if(f.exists())
            {
                System.out.println("Workbook exists");
                FileInputStream existingFile = new FileInputStream(f);
                workbook = new XSSFWorkbook(existingFile);
                existingFile.close();
            }
            
            else
            {
            FileOutputStream out = new FileOutputStream(filepath);
            workbook.write(out);
            Sheet s1 = workbook.createSheet("Sheet 1");
            System.out.println("createworkbook.xlsx written successfully");
            out.close();
            
            }
            

        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
            return workbook;
    }
    public static XSSFSheet createSheets(XSSFWorkbook workbook,String sheetName)
    {
        XSSFSheet excelSheet = workbook.createSheet(sheetName);
        return excelSheet;
    }
    
//    write functions
    public static Row createRow(XSSFSheet excelSheet)
    {
        int rowCount = excelSheet.getLastRowNum();
        Row row = excelSheet.createRow(rowCount+1);
        
        return row;
    }
    
    public static void createCell(Row row, int cellCount, String value)
    {
        Cell cell = row.createCell(cellCount);
        cell.setCellValue(value);
    }
    
    public static void FileOutput(String name, XSSFWorkbook workbook)
    {
        try (FileOutputStream outputStream = new FileOutputStream(name + ".xlsx")) {
            workbook.write(outputStream);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
