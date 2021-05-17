/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import jxl.Workbook;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Kanav
 */
public class Excel {
    public static void createWorkbook(String name)
    {   
        System.out.println("Workbook method"+name + " " + new Frames.Login().user);
        String filepath = name + ".xlsx";
        WritableWorkbook workbook = null;
        try {

            workbook = Workbook.createWorkbook(new File(filepath));
            System.out.println("File created");
            WritableSheet excelSheet = workbook.createSheet("Sheet 1", 0);
            workbook.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        } catch (WriteException ex) {
            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public static void main(String args[])
    {
        Excel e = new Excel();
        e.createWorkbook("try");
    }
}
