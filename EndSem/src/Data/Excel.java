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
            System.out.println("createworkbook.xlsx written successfully");
            out.close();
            
            }
            

        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
            return workbook;
    }
//    public static void createSheets(WritableWorkbook workbook,String sheetName, int sheetNumber)
//    {
//        WritableSheet excelSheet = workbook.createSheet(sheetName, sheetNumber);
//    }
//    
//    public static void closeBook(WritableWorkbook workbook) throws IOException, WriteException
//    {
//        workbook.close();
//    }
    public static void main(String args[])
    {
        createWorkbook("try");
    }
}
