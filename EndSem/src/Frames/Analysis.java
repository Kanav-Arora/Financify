/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.awt.Color;
import java.awt.Dimension;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.NumberFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author shivam
 */
public class Analysis extends javax.swing.JFrame {

    String query = "";

    String username = "";

    /**
     * Creates new form Analysis
     */
    public Analysis() {
        initComponents();
       // username = Login.user;
        username="kgp";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        Date date = new Date();
        int month = date.getMonth();
        float jan_s = 0, feb_s = 0, march_s = 0, april_s = 0, may_s = 0, june_s = 0, july_s = 0, august_s = 0, sep_s = 0, oct_s = 0, nov_s = 0, dec_s = 0;
        float jan_p = 0, feb_p = 0, march_p = 0, april_p = 0, may_p = 0, june_p = 0, july_p = 0, august_p = 0, sep_p = 0, oct_p = 0, nov_p = 0, dec_p = 0;
        try {
            String bill_no = "";
            int month_backend = 0;
            float bill_amount = 0;
            String type = "";
            Class.forName("java.sql.DriverManager");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jvp", "root", "bhulgaya123");
            System.out.println("Connection success!!");
            Statement stmt = (Statement) con.createStatement();
            System.out.println("Statement success!!");
            query = "SELECT DISTINCT(bill_no), month(date), bill_amount, type FROM bill where status = '" + "cleared" + "' and username = '" + username + "' ";
            System.out.println("Query success!!");
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("ResultSet success!!");

            for (;;) {
                if (rs.next()) {
                    bill_no = rs.getString(1);
                    month_backend = rs.getInt(2);
                    bill_amount = rs.getFloat(3);
                    type = rs.getString(4);
                    if (month_backend == 1) {
                        if (type.equals("sale")) {
                            jan_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            jan_p += bill_amount;
                        }
                    } else if (month_backend == 2) {
                        if (type.equals("sale")) {
                            feb_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            feb_p += bill_amount;
                        }
                    } else if (month_backend == 3) {
                        if (type.equals("sale")) {
                            march_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            march_p += bill_amount;
                        }
                    } else if (month_backend == 4) {
                        if (type.equals("sale")) {
                            april_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            april_p += bill_amount;
                        }
                    } else if (month_backend == 5) {
                        if (type.equals("sale")) {
                            may_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            may_p += bill_amount;
                        }
                    } else if (month_backend == 6) {
                        if (type.equals("sale")) {
                            june_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            june_p += bill_amount;
                        }
                    } else if (month_backend == 7) {
                        if (type.equals("sale")) {
                            july_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            july_p += bill_amount;
                        }
                    } else if (month_backend == 8) {
                        if (type.equals("sale")) {
                            august_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            august_p += bill_amount;
                        }
                    } else if (month_backend == 9) {
                        if (type.equals("sale")) {
                            sep_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            sep_p += bill_amount;
                        }
                    } else if (month_backend == 10) {
                        if (type.equals("sale")) {
                            oct_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            oct_p += bill_amount;
                        }
                    } else if (month_backend == 11) {
                        if (type.equals("sale")) {
                            nov_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            nov_p += bill_amount;
                        }
                    } else if (month_backend == 12) {
                        if (type.equals("sale")) {
                            dec_s += bill_amount;
                        } else if (type.equals("purchase")) {
                            dec_p += bill_amount;
                        }
                    }

                } else {
                    break;
                }
            }
            
            
        float sales_total=0;
        float purchase_total=0;
        float gst_total=0;
        float pending_payment=0;
        String status="";
        type="";
        
            query = "select * from bill where username = '" + username + "'";
            System.out.println("Fetching items from database: jvp; table: bill");
            rs = stmt.executeQuery(query);
            System.out.println("Record fetched successfully.");
            for(;;)
            {   
                if(rs.next())
                {   status = rs.getString("status");
                    type = rs.getString("type");
                    float taxable= rs.getFloat("taxable");
                    float gst = rs.getFloat("gst");
                    if(status.equals("pending") && type.equals("sale"))
                    {
                        pending_payment+=taxable+gst;
                    }
                    if(status.equals("cleared") && type.equals("sale"))
                    {
                        sales_total+=taxable+gst;
                    }
                    if(status.equals("cleared") && type.equals("purchase"))
                    {
                        purchase_total+=taxable+gst;
                    }
                    if(status.equals("cleared"))
                    {
                        gst_total+=gst;
                    }
                    
                }
                else
                {
                    break;
                }
                
            }
            
                DecimalFormat formatter = new DecimalFormat("##,##,###.00");
                
                jLabel7.setText("Rs."+formatter.format(sales_total));
                jLabel8.setText("Rs."+formatter.format(purchase_total));
                jLabel9.setText("Rs."+formatter.format(pending_payment));
                jLabel10.setText("Rs."+formatter.format(gst_total));
            
       
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Analysis.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Analysis.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Dataset success!!");
        String series1 = "Sale";
        dataset.addValue(april_s, series1, "April");
        dataset.addValue(may_s, series1, "May");
        dataset.addValue(june_s, series1, "June");
        dataset.addValue(july_s, series1, "July");
        dataset.addValue(august_s, series1, "August");
        dataset.addValue(sep_s, series1, "September");
        dataset.addValue(oct_s, series1, "October");
        dataset.addValue(nov_s, series1, "November");
        dataset.addValue(dec_s, series1, "December");
        dataset.addValue(jan_s, series1, "January");
        dataset.addValue(feb_s, series1, "February");
        dataset.addValue(march_s, series1, "March");
        String series2 = "Purchase";
        dataset.addValue(april_p, series2, "April");
        dataset.addValue(may_p, series2, "May");
        dataset.addValue(june_p, series2, "June");
        dataset.addValue(july_p, series2, "July");
        dataset.addValue(august_p, series2, "August");
        dataset.addValue(sep_p, series2, "September");
        dataset.addValue(oct_p, series2, "October");
        dataset.addValue(nov_p, series2, "November");
        dataset.addValue(dec_p, series2, "December");
        dataset.addValue(jan_p, series2, "January");
        dataset.addValue(feb_p, series2, "February");
        dataset.addValue(march_p, series2, "March");
        CategoryPlot plot = new CategoryPlot();
        plot.setDomainAxis(new CategoryAxis("Month"));
        plot.setRangeAxis(new NumberAxis("Amount (in Rs.)"));
        JFreeChart chart = ChartFactory.createBarChart(" ",
                "Month",
                "Amount (in Rs.)",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);
        plot.setRangeGridlinePaint(Color.BLACK);
        ChartPanel chPanel = new ChartPanel(chart);
        chPanel.setPreferredSize(new Dimension(733, 300));
        chPanel.setMouseWheelEnabled(true);
        jPanel3.setLayout(new java.awt.BorderLayout());
        jPanel3.add(chPanel);
        jPanel3.setVisible(true);
        jPanel3.validate();
        
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(226, 225, 225));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Monospaced", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("Sales and Purchase Distribution");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 733, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 156, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 540, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Monospaced", 1, 20)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Purchase");
        jPanel6.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 10, 190, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Monospaced", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Sale");
        jPanel6.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 180, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Monospaced", 1, 20)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Sales Unrealized");
        jPanel6.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 10, 250, -1));

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Monospaced", 1, 20)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("GST");
        jPanel6.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 10, 160, -1));

        jLabel7.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(153, 0, 153));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("jLabel7");
        jPanel6.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 180, -1));

        jLabel8.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 0, 0));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("jLabel7");
        jPanel6.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 50, 190, -1));

        jLabel9.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 102, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("jLabel7");
        jPanel6.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 250, -1));

        jLabel10.setFont(new java.awt.Font("Copperplate Gothic Light", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(153, 153, 0));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("jLabel7");
        jPanel6.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 50, 160, -1));

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 13, 105));

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 13, 105));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jPanel6.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 0, 13, 105));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 31, 850, 560));

        jPanel2.setBackground(new java.awt.Color(23, 35, 51));

        jLabel1.setText("Analysis Dashboard");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(731, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, -1));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Analysis.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Analysis().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator5;
    // End of variables declaration//GEN-END:variables
}
