/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Frames;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;

/**
 *
 * @author Kanav
 */
public class Sale_Ledger extends javax.swing.JFrame {

    String username;
    String bill_number;

    /**
     * Creates new form Sale
     */
    public Sale_Ledger() {

        try {
            initComponents();
            jLabel2.setVisible(false);
            jButton1.setVisible(false);
            jLabel2.setVisible(false);

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String current_date = formatter.format(date);
            jTextField2.setText(current_date);
            Class.forName("java.sql.DriverManager");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jvp", "root", "bhulgaya123");
            System.out.println("Connection is created successfully");
            Statement stmt = (Statement) con.createStatement();
            String query = "select acc_name from accounts where username = '" + username + "'";
            System.out.println("Fetching acc_name from database: jvp; table: accounts");
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Record fetched successfully.");
            for (;;) {
                if (rs.next()) {
                    String item = rs.getString(1);
                    jComboBox1.addItem(item);
                } else {
                    break;
                }

            }
            jComboBox1.setSelectedItem("");
            AutoCompleteDecorator.decorate(jComboBox1);

            username = new Login().user;
            bill_number = new LedgerAccounts().bill;

            jTextField1.setText(bill_number);

            DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
            System.out.println("Connection is created successfully");

            query = "select sum(taxable),sum(disc),sum(gst),count(s_no),acc_name,bill_amount from bill where bill_no = '" + Integer.parseInt(bill_number.substring(2)) + "'";
            System.out.println("Fetching items from database: jvp; table: bill");
            rs = stmt.executeQuery(query);
            System.out.println("Record fetched successfully.");
            float taxable = 0;
            float disc = 0;
            float gst_amount = 0;
            String acc_name = "";
            float bill_amount = 0;
            String item_id = "";
            String item_name = "";
            float pcs = 0;
            float quantity = 0;
            float rate = 0;
            float net_rate = 0;
            float amount = 0;
            float disc_perc = 0;
            int s_no = 0;
            float gst_perc = 0;
            if (rs.next()) {
                taxable = rs.getFloat(1);
                disc = rs.getFloat(2);
                gst_amount = rs.getFloat(3);
                s_no = rs.getInt(4);
                acc_name = rs.getString("acc_name");
                bill_amount = rs.getFloat("bill_amount");
            }

            jComboBox1.setSelectedItem("" + acc_name);
            jTextField7.setText("" + taxable);
            jTextField8.setText("" + disc);
            jTextField9.setText("" + (gst_amount / 2));
            jTextField10.setText("" + (gst_amount / 2));
            jTextField11.setText("" + s_no);
            jTextField12.setText("" + bill_amount);

            int i = 0;
            while (i <= s_no) {

                System.out.println(i);

                query = "select * from bill where bill_no = '" + Integer.parseInt(bill_number.substring(2)) + "' and s_no = '" + (i + 1) + "' ";
                System.out.println("Fetching items from database: jvp; table: bill");
                rs = stmt.executeQuery(query);
                System.out.println("Record fetched successfully.");
                if (rs.next()) {
                    taxable = rs.getFloat("taxable");
                    disc = rs.getFloat("disc");
                    gst_amount = rs.getFloat("gst");
                    s_no = rs.getInt("s_no");
                    acc_name = rs.getString("acc_name");
                    item_id = rs.getString("item_id");
                    item_name = rs.getString("item_name");
                    bill_amount = rs.getFloat("bill_amount");
                    pcs = rs.getInt("pcs");
                    quantity = rs.getFloat("quantity");
                    net_rate = rs.getFloat("net_rate");
                    rate = rs.getFloat("rate");
                    amount = rs.getFloat("amount");
                    disc_perc = rs.getFloat("disc_perc");
                    gst_perc = rs.getFloat("gst_perc");
                }
                model.addRow(new Object[]{i + 1, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
                model.setValueAt(s_no, i, 0);
                model.setValueAt(item_id, i, 1);
                model.setValueAt(item_name, i, 2);
                model.setValueAt(pcs, i, 3);
                model.setValueAt(quantity, i, 4);
                model.setValueAt(rate, i, 5);
                model.setValueAt(net_rate, i, 6);
                model.setValueAt(amount, i, 7);
                model.setValueAt(disc, i, 8);
                model.setValueAt(disc_perc, i, 9);
                model.setValueAt(taxable, i, 10);
                model.setValueAt(gst_perc, i, 11);
                model.setValueAt(gst_amount, i, 12);
                i++;
            }

            query = "select sum(debit),sum(credit) from transactions WHERE username = '" + username + "'";
            System.out.println("Adding all values of transactions from database: jvp, table: transactions");
            rs = stmt.executeQuery(query);
            System.out.println("Record count fetched successfully.");
            float credit_total = 0;
            float debit_total = 0;

            if (rs.next()) {
                debit_total = rs.getFloat(1);
                credit_total = rs.getFloat(2);

            }
            if (credit_total > debit_total) {
                jTextField4.setText("" + Math.abs(credit_total - debit_total) + " Cr");
            } else {
                jTextField4.setText("" + Math.abs(credit_total - debit_total) + " Dr");
            }

            query = "select * from accounts where username = '" + username + "' and acc_name = '" + acc_name + "'";
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String gst = rs.getString("gst");
                int pincode = rs.getInt("pincode");
                jTextField6.setText("" + address + "," + city + "," + state + " -" + pincode);
                jTextField5.setText(gst);

            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static float addgst(int gst, float rate) {
        float net_rate = 0;
        net_rate = rate + ((float) gst / 100) * rate;
        return net_rate;
    }

    public static void addItems(JComboBox combo, String username) {
        try {

            Class.forName("java.sql.DriverManager");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jvp", "root", "bhulgaya123");
            System.out.println("Connection is created successfully");
            Statement stmt = (Statement) con.createStatement();

            String query = "select item_name from stocks where username = '" + username + "'";
            System.out.println("Fetching items from database: jvp; table: stocks");
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Record fetched successfully.");

            for (;;) {
                if (rs.next()) {
                    String item = rs.getString(1);
                    combo.addItem(item);
                } else {
                    break;
                }
            }

            combo.setSelectedItem("");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void addItemNo(JComboBox combo, String username) {
        try {

            Class.forName("java.sql.DriverManager");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jvp", "root", "bhulgaya123");
            System.out.println("Connection is created successfully");
            Statement stmt = (Statement) con.createStatement();

            String query = "select item_id from stocks where username = '" + username + "'";
            System.out.println("Fetching items from database: jvp; table: stocks");
            ResultSet rs = stmt.executeQuery(query);
            System.out.println("Record fetched successfully.");

            for (;;) {
                if (rs.next()) {
                    String item = rs.getString(1);
                    combo.addItem("S-" + item);
                } else {
                    break;
                }
            }

            combo.setSelectedItem("");

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean isCellEditable(int row, int column) { // custom isCellEditable function
        boolean val = false;
        if (column == 7) {
            val = false;
        } else if (column == 10) {
            val = false;
        } else if (column == 11) {
            val = false;
        } else if (column == 12) {
            val = false;
        } else if (column == 0) {
            val = false;
        } else {
            val = true;
        }

        return val;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jTextField3 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField6 = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jTextField12 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(23, 35, 51));

        jLabel1.setText("Sale Bill");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addComponent(jLabel1)
                .addContainerGap(789, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 850, -1));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(null);

        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Bill No. :");
        jPanel1.add(jLabel9);
        jLabel9.setBounds(14, 14, 50, 24);

        jTextField1.setEditable(false);
        jTextField1.setBackground(new java.awt.Color(255, 255, 255));
        jTextField1.setForeground(new java.awt.Color(0, 0, 0));
        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField1);
        jTextField1.setBounds(82, 14, 90, 24);

        jLabel10.setForeground(new java.awt.Color(0, 0, 0));
        jLabel10.setText("Bill Date :");
        jPanel1.add(jLabel10);
        jLabel10.setBounds(209, 14, 52, 24);

        jTextField2.setEditable(false);
        jTextField2.setBackground(new java.awt.Color(255, 255, 255));
        jTextField2.setForeground(new java.awt.Color(0, 0, 0));
        jTextField2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField2ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField2);
        jTextField2.setBounds(279, 14, 90, 24);

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Select Account" }));
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });
        jPanel1.add(jComboBox1);
        jComboBox1.setBounds(20, 64, 320, 31);

        jTextField3.setEditable(false);
        jTextField3.setBackground(new java.awt.Color(255, 255, 255));
        jTextField3.setForeground(new java.awt.Color(0, 0, 0));
        jTextField3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField3ActionPerformed(evt);
            }
        });
        jPanel1.add(jTextField3);
        jTextField3.setBounds(483, 14, 90, 24);

        jLabel11.setForeground(new java.awt.Color(0, 0, 0));
        jLabel11.setText("Due Date :");
        jPanel1.add(jLabel11);
        jLabel11.setBounds(399, 14, 66, 24);

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Details");
        jLabel2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel2);
        jLabel2.setBounds(399, 64, 114, 31);

        jLabel3.setForeground(new java.awt.Color(0, 0, 0));
        jLabel3.setText("Address :");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(20, 114, 54, 16);

        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Cash/Credit :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(603, 18, 72, 16);

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Credit", "Cash" }));
        jPanel1.add(jComboBox2);
        jComboBox2.setBounds(705, 13, 98, 26);

        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Balance :");
        jPanel1.add(jLabel5);
        jLabel5.setBounds(603, 71, 60, 16);

        jTextField4.setEditable(false);
        jTextField4.setBackground(new java.awt.Color(255, 255, 255));
        jTextField4.setForeground(new java.awt.Color(0, 0, 0));
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(jTextField4);
        jTextField4.setBounds(705, 67, 121, 24);

        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        jLabel7.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jPanel1.add(jLabel7);
        jLabel7.setBounds(0, 148, 844, 16);

        jLabel6.setForeground(new java.awt.Color(0, 0, 0));
        jLabel6.setText("GST No. :");
        jPanel1.add(jLabel6);
        jLabel6.setBounds(603, 114, 70, 16);

        jTextField5.setEditable(false);
        jTextField5.setBackground(new java.awt.Color(255, 255, 255));
        jTextField5.setForeground(new java.awt.Color(0, 0, 0));
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(jTextField5);
        jTextField5.setBounds(705, 110, 121, 24);

        jScrollPane2.setBorder(null);
        jScrollPane2.setAutoscrolls(true);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "SNo", "Item ID", "Item Name", "Pcs", "Qty (kg)", "Rate", "Net Rate", "Amount", "Discount", "Disc (%)", "Taxable", "GST (%)", "GST"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jPanel1.add(jScrollPane2);
        jScrollPane2.setBounds(6, 170, 828, 170);

        jTextField6.setEditable(false);
        jTextField6.setBackground(new java.awt.Color(255, 255, 255));
        jTextField6.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jTextField6);
        jTextField6.setBounds(92, 112, 450, 30);

        jLabel15.setForeground(new java.awt.Color(0, 0, 0));
        jLabel15.setText("Item(s) :");
        jPanel1.add(jLabel15);
        jLabel15.setBounds(701, 356, 60, 16);

        jTextField11.setEditable(false);
        jTextField11.setBackground(new java.awt.Color(255, 255, 255));
        jTextField11.setForeground(new java.awt.Color(0, 0, 0));
        jTextField11.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(jTextField11);
        jTextField11.setBounds(764, 352, 70, 24);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 1, true));

        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Sub Total :");

        jTextField7.setEditable(false);
        jTextField7.setBackground(new java.awt.Color(255, 255, 255));
        jTextField7.setForeground(new java.awt.Color(0, 0, 0));
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel13.setForeground(new java.awt.Color(0, 0, 0));
        jLabel13.setText("SGST :");

        jTextField9.setEditable(false);
        jTextField9.setBackground(new java.awt.Color(255, 255, 255));
        jTextField9.setForeground(new java.awt.Color(0, 0, 0));
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel12.setForeground(new java.awt.Color(0, 0, 0));
        jLabel12.setText("Discount :");

        jTextField8.setEditable(false);
        jTextField8.setBackground(new java.awt.Color(255, 255, 255));
        jTextField8.setForeground(new java.awt.Color(0, 0, 0));
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        jLabel14.setForeground(new java.awt.Color(0, 0, 0));
        jLabel14.setText("CGST :");

        jTextField10.setEditable(false);
        jTextField10.setBackground(new java.awt.Color(255, 255, 255));
        jTextField10.setForeground(new java.awt.Color(0, 0, 0));
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.RIGHT);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel14)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addComponent(jLabel13)
                        .addGap(30, 30, 30)
                        .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel3);
        jPanel3.setBounds(10, 350, 487, 109);

        jLabel16.setForeground(new java.awt.Color(0, 0, 0));
        jLabel16.setText("――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――");
        jPanel1.add(jLabel16);
        jLabel16.setBounds(0, 490, 840, 16);

        jLabel17.setForeground(new java.awt.Color(0, 0, 0));
        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText("Edit");
        jLabel17.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        jLabel17.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel17MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel17);
        jLabel17.setBounds(20, 510, 110, 32);

        jLabel18.setForeground(new java.awt.Color(0, 0, 0));
        jLabel18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel18.setText("Generate Bill");
        jLabel18.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        jLabel18.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel18MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel18);
        jLabel18.setBounds(690, 510, 140, 32);

        jLabel19.setForeground(new java.awt.Color(0, 0, 0));
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Exit");
        jLabel19.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel19);
        jLabel19.setBounds(300, 510, 110, 32);

        jLabel20.setForeground(new java.awt.Color(0, 0, 0));
        jLabel20.setText("Bill Amount :");
        jPanel1.add(jLabel20);
        jLabel20.setBounds(549, 412, 70, 16);

        jTextField12.setEditable(false);
        jTextField12.setBackground(new java.awt.Color(255, 255, 255));
        jTextField12.setForeground(new java.awt.Color(0, 0, 0));
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.RIGHT);
        jPanel1.add(jTextField12);
        jTextField12.setBounds(653, 408, 181, 24);

        jButton1.setText("Add Item");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton1);
        jButton1.setBounds(570, 350, 90, 32);

        jLabel21.setForeground(new java.awt.Color(0, 0, 0));
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("Delete");
        jLabel21.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(102, 102, 102), 2, true));
        jPanel1.add(jLabel21);
        jLabel21.setBounds(160, 510, 110, 32);

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 850, 560));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jTextField2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void jTextField3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        username = new Login().user;
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = model.getRowCount();
        model.addRow(new Object[]{row + 1, null, null, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0});
        TableColumn col1 = jTable1.getColumnModel().getColumn(1);
        TableColumn col2 = jTable1.getColumnModel().getColumn(2);
        JComboBox combo1 = new JComboBox<String>();
        JComboBox combo2 = new JComboBox<String>();
        addItemNo(combo1, username);
        combo1.setSelectedItem("");
        AutoCompleteDecorator.decorate(combo1);
        addItems(combo2, username);
        combo2.setSelectedItem("");
        AutoCompleteDecorator.decorate(combo2);
        col1.setCellEditor(new DefaultCellEditor(combo1));
        col2.setCellEditor(new DefaultCellEditor(combo2));

        row = model.getRowCount();
        jTextField11.setText("" + row);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        String acc = (String) jComboBox1.getSelectedItem();
        String bill_date = jTextField2.getText();
        int credit_days = 0;

        try {
            // TODO add your handling code here:

            Class.forName("java.sql.DriverManager");
            Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jvp", "root", "bhulgaya123");
            Statement stmt = (Statement) con.createStatement();
            String query = "select * from accounts where username = '" + username + "' and acc_name = '" + acc + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                credit_days = rs.getInt("credit_days");
                String address = rs.getString("address");
                String city = rs.getString("city");
                String state = rs.getString("state");
                String gst = rs.getString("gst");
                int pincode = rs.getInt("pincode");
                jTextField6.setText("" + address + "," + city + "," + state + " -" + pincode);
                jTextField5.setText(gst);

            }
            query = "select sum(debit),sum(credit) from transactions WHERE username = '" + username + "' and acc_name = '" + acc + "'";
            System.out.println("Adding all values of transactions from database: jvp, table: transactions");
            rs = stmt.executeQuery(query);
            System.out.println("Record count fetched successfully.");
            float credit_total = 0;
            float debit_total = 0;

            if (rs.next()) {
                debit_total = rs.getFloat(1);
                credit_total = rs.getFloat(2);

            }
            if (credit_total > debit_total) {
                jTextField4.setText("" + Math.abs(credit_total - debit_total) + " Cr");
            } else {
                jTextField4.setText("" + Math.abs(credit_total - debit_total) + " Dr");
            }

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            date = formatter.parse(bill_date);
//            LocalDateTime.from(date.toInstant()).plusDays(credit_days);

            Calendar c = Calendar.getInstance();
            c.setTime(date);
            c.add(Calendar.DATE, credit_days);
            date = c.getTime();
            String final_date = formatter.format(date);
            jTextField3.setText(final_date);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountSetup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(AccountSetup.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void jLabel18MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel18MouseClicked
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        int row = model.getRowCount();
        model = (DefaultTableModel) jTable1.getModel();
        int i = 0;
        int bill_no = Integer.parseInt(jTextField1.getText().substring(2));
        username = new Login().user;
        float subtotal = 0;
        float discount_total = 0;
        float gst_total = 0;
        float grand_total = 0;
        String acc_name = (String) jComboBox1.getSelectedItem();
        String type = "sale";
        while (i < row) {
            int s_no = Integer.parseInt(model.getValueAt(i, 0).toString());
            System.out.println(0);
            int item_id = Integer.parseInt(model.getValueAt(i, 1).toString().substring(2));
            System.out.println(1);
            String item_name = (String) model.getValueAt(i, 2);
            System.out.println(2);
            int pcs = (int) model.getValueAt(i, 3);
            System.out.println(3);
            float quantity = Float.parseFloat(String.valueOf(model.getValueAt(i, 4)));
            System.out.println(4);
            float net_rate = Float.parseFloat(String.valueOf(model.getValueAt(i, 5)));
            float rate = Float.parseFloat(String.valueOf(model.getValueAt(i, 6)));
            float amount = Float.parseFloat(String.valueOf(model.getValueAt(i, 7)));
            float discount = Float.parseFloat(String.valueOf(model.getValueAt(i, 8)));
            float discount_perc = Float.parseFloat(String.valueOf(model.getValueAt(i, 9)));
            float taxable = Float.parseFloat(String.valueOf(model.getValueAt(i, 10)));
            float gst_perc = Float.parseFloat(String.valueOf(model.getValueAt(i, 11)));
            float gst = Float.parseFloat(String.valueOf(model.getValueAt(i, 12)));

            subtotal += taxable;
            discount_total += discount;
            gst_total += gst;
            try {
                Class.forName("java.sql.DriverManager");
                Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/jvp", "root", "bhulgaya123");
                Statement stmt = (Statement) con.createStatement();
                String query = "INSERT INTO bill VALUES('" + bill_no + "','" + s_no + "','" + item_id + "','" + item_name + "','" + pcs + "','" + quantity + "','" + net_rate + "','" + rate + "','" + amount + "','" + discount + "','" + discount_perc + "','" + taxable + "','" + gst_perc + "','" + gst + "','" + acc_name + "','" + username + "','" + type + "');";

                stmt.executeUpdate(query);

            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(Sale_Ledger.class.getName()).log(Level.SEVERE, null, ex);
            }
            i++;
        }

    }//GEN-LAST:event_jLabel18MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1KeyReleased

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        this.setVisible(false);
        new LedgerAccounts().setVisible(true);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel17MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel17MouseClicked
        jLabel2.setVisible(true);
        jButton1.setVisible(true);
        jLabel2.setVisible(true);

        jTextField2.setEditable(true);
        jTextField3.setEditable(true);
        jTextField4.setEditable(true);
        jTextField6.setEditable(true);
        jTextField7.setEditable(true);


    }//GEN-LAST:event_jLabel17MouseClicked

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
            java.util.logging.Logger.getLogger(Sale_Ledger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Sale_Ledger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Sale_Ledger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Sale_Ledger.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Sale_Ledger().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
