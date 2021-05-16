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
import org.apache.poi.xssf.usermodel.*;
/**
 *
 * @author Kanav
 */
public class Excel {
    public static void createWorkbook(String name)
    {   
        System.out.println("Workbook method"+name + " " + new Frames.Login().user);
//        FileOutputStream out = null;
//        try {
//            XSSFWorkbook workbook = new XSSFWorkbook();
//            String home = System.getProperty("user.home");
//            File file = new File(home+"/Documents/"+"Financify/"+new Frames.Login().user+"/"+name+".xlsx");
////File file = new File(home);
//            out = new FileOutputStream(file);
//            workbook.write(out);
//            out.close();
//            
//            System.out.println("createworkbook.xlsx written successfully");
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
//        } finally {
//            try {
//                out.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Excel.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
}
