/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package dpa;

import com.mysql.cj.xdevapi.Statement;
import java.awt.HeadlessException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rohan
 */
public class Invoice extends javax.swing.JPanel {

    public static String barcode_c ="0";
    public static String  cus_id = "0";
    public double stcok_qty=0.0;
    
    public Invoice() {
        initComponents();
        data_load();
        
    }
    public void data_load()
    {
    //load customer
        try {
            
            java.sql.Statement s = db.mycon().createStatement();
           ResultSet rs =  s.executeQuery("SELECT * FROM customer ");
           Vector v =new Vector();
           
            while ( rs.next()) { 
                v.add(rs.getString("customer_name"));
                
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                
                com_cus.setModel(com);
            }              
        } catch (SQLException e) {
            System.out.println(e); 
            
        }
            
          //load Product
        try {
            
            java.sql.Statement s = db.mycon().createStatement();
           ResultSet rs =  s.executeQuery("SELECT * FROM product ");
           Vector v =new Vector();
           
            while ( rs.next()) { 
                v.add(rs.getString("Product_Name"));
                
                DefaultComboBoxModel com = new DefaultComboBoxModel(v);
                
                com_pro.setModel(com);
            }              
        } catch (SQLException e) {
            System.out.println(e); 
       }
        // load last invoice number
        try {
            java.sql.Statement s = db.mycon().createStatement();
             ResultSet rs = s.executeQuery("SELECT * FROM extra WHERE exid=1");
             if (rs.next()) {
                 
                inid.setText(rs.getString(	"val"));
            }
             
             
             
        } catch (Exception e) {
        }
        //pluss new invoice 
        int i = Integer.valueOf(inid.getText());
        i++;
        inid.setText(String.valueOf(i));
        
    }
    
    public void pro_tot_cal(){
    // product calculation
            Double qt = Double.valueOf(p_qty.getText());
            Double price = Double.valueOf(u_price.getText());             
            Double tot;
            
            tot = qt*price;
            tot_price.setText(String.valueOf(tot));
    }
    
    public void cart_total(){
    int numofrow = jTable1.getRowCount();
    
       double total = 0;
        for (int i = 0; i <numofrow  ; i++) {
            
            double value = Double.valueOf(jTable1.getValueAt(i, 5).toString());
            total += value;
        }    
         bill_tot.setText(Double.toString(total));
         
       // total quantity count 
         int numofrows = jTable1.getRowCount();
    
       double totals = 0;
        for (int i = 0; i <numofrows ; i++) {
            
            double values = Double.valueOf(jTable1.getValueAt(i, 3).toString());
            totals += values ;
        }    
       tot_qty.setText(Double.toString(totals));
          
         
         
         
    }

     public void tot(){
     
        Double paid  = Double.valueOf(paid_amt.getText());
        Double tot = Double.valueOf(bill_tot.getText());
        Double due ;
        
        
        due = paid - tot;
        balance.setText(String.valueOf(due));
     }   
   public void stockup(){
       
       //get all table product id and sell qty
       
       DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
       int rc = dt.getRowCount();
       
       for (int i = 0; i < rc; i++) {
           
           String bcode = dt.getValueAt(i, 2).toString();//id or barcode
           String sell_qty = dt.getValueAt(i, 3).toString();//id or barcode
           
           System.out.println(bcode);
           System.out.println(sell_qty);
           
           try {
               
               java.sql.Statement s =db.mycon().createStatement();
               ResultSet rs = s.executeQuery("SELECT Qty FROM product WHERE Barcode = '"+bcode+"'");
               
               if (rs.next()) {
                   stcok_qty = Double.valueOf(rs.getString("Qty"));
               }
               
           } catch (Exception e) {
               System.out.println(e);
           }
         
           Double st_qty =stcok_qty;
           Double sel_qty =Double.valueOf(sell_qty);
           Double new_qty = st_qty-sel_qty; //new qty = stock qty - sell quantity
           String nqty = String.valueOf(new_qty);
           
           try {
               java.sql.Statement ss =  db.mycon().createStatement();
           ss.executeUpdate("UPDATE product SET Qty ='"+nqty+"' WHERE Barcode = '"+bcode+"'");
               
           } catch (SQLException e) {
               System.out.println(e);
           }
           
           
           
           
       }
       
       
   }  
   
@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        inid = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        com_pro = new javax.swing.JComboBox<>();
        p_qty = new javax.swing.JTextField();
        com_cus = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        br_code1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        u_price = new javax.swing.JTextField();
        tot_price = new javax.swing.JTextField();
        br_code = new javax.swing.JTextField();
        l_stqty = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        paid_amt = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        bill_tot = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        balance = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        tot_qty = new javax.swing.JTextField();

        setBackground(new java.awt.Color(0, 204, 0));

        jPanel1.setBackground(new java.awt.Color(0, 204, 0));

        jPanel2.setBackground(new java.awt.Color(0, 204, 0));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setBackground(new java.awt.Color(0, 204, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("INVOICE NO:");

        inid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inid.setForeground(new java.awt.Color(255, 255, 255));
        inid.setText("01");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inid)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(inid))
                .addContainerGap(28, Short.MAX_VALUE))
        );

        jPanel18.setBackground(new java.awt.Color(0, 204, 0));
        jPanel18.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel3.setBackground(new java.awt.Color(0, 204, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Customer :");

        jLabel4.setBackground(new java.awt.Color(0, 204, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Qty :");

        jLabel5.setBackground(new java.awt.Color(0, 204, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Product :");

        jLabel6.setBackground(new java.awt.Color(0, 204, 0));
        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Unit Price :");

        com_pro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        com_pro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select " }));
        com_pro.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                com_proInputMethodTextChanged(evt);
            }
        });
        com_pro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_proActionPerformed(evt);
            }
        });
        com_pro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                com_proKeyReleased(evt);
            }
        });

        p_qty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        p_qty.setText("0");
        p_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                p_qtyKeyReleased(evt);
            }
        });

        com_cus.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        com_cus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "select " }));
        com_cus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                com_cusActionPerformed(evt);
            }
        });

        jLabel7.setBackground(new java.awt.Color(0, 204, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Total price :");

        br_code1.setBackground(new java.awt.Color(0, 204, 0));
        br_code1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        br_code1.setText("Barcode :");

        jLabel12.setBackground(new java.awt.Color(0, 204, 0));
        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setText("Stock Qty:");

        u_price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        u_price.setText("0");
        u_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                u_priceKeyReleased(evt);
            }
        });

        tot_price.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tot_price.setText("0");
        tot_price.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tot_priceKeyReleased(evt);
            }
        });

        br_code.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        br_code.setText("0");
        br_code.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                br_codeKeyReleased(evt);
            }
        });

        l_stqty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        l_stqty.setText("0");
        l_stqty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                l_stqtyKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(com_cus, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addGap(4, 4, 4)
                .addComponent(com_pro, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(l_stqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(1, 1, 1))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel7))
                .addGap(4, 4, 4)
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tot_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(u_price, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addComponent(br_code1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(br_code, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(com_pro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(p_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(com_cus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(u_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(l_stqty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7)
                            .addComponent(tot_price, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel18Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(br_code1)
                            .addComponent(br_code, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jTable1.setBackground(new java.awt.Color(0, 204, 0));
        jTable1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jTable1.setForeground(new java.awt.Color(255, 255, 255));
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "INID", "Name", "Bar code ", "Qty", "Unit price ", "Total price "
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 800, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
        );

        jPanel5.setBackground(new java.awt.Color(0, 204, 0));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButton1.setText("Add to cart ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButton2.setText("Remove ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButton3.setText("Remove All");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        jButton4.setText("Pay");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(54, 54, 54)
                .addComponent(jButton1)
                .addGap(32, 32, 32)
                .addComponent(jButton2)
                .addGap(32, 32, 32)
                .addComponent(jButton3)
                .addGap(42, 42, 42)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(0, 204, 0));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setBackground(new java.awt.Color(0, 204, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Paid Amount :");

        paid_amt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        paid_amt.setText("0");
        paid_amt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                paid_amtKeyReleased(evt);
            }
        });

        jPanel7.setBackground(new java.awt.Color(0, 204, 0));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel9.setBackground(new java.awt.Color(0, 204, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel9.setText("Total Amount :");

        bill_tot.setBackground(new java.awt.Color(0, 204, 0));
        bill_tot.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bill_tot.setText("00.00");
        bill_tot.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel11.setBackground(new java.awt.Color(0, 204, 0));
        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel11.setText("Balance/Due :");

        balance.setBackground(new java.awt.Color(0, 204, 0));
        balance.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        balance.setText("00.00");
        balance.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(balance, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bill_tot, javax.swing.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(bill_tot))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(balance)
                    .addComponent(jLabel11))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        jLabel10.setBackground(new java.awt.Color(0, 204, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Tot Qty :");

        tot_qty.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tot_qty.setText("0");
        tot_qty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tot_qtyKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(paid_amt, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(99, 99, 99)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tot_qty, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGap(235, 235, 235)
                        .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(paid_amt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(tot_qty, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void p_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_p_qtyKeyReleased
        //key released in quantity button 
        pro_tot_cal();
        
    }//GEN-LAST:event_p_qtyKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // button of add to cart 
        
        Double sell_qty = Double.valueOf(p_qty.getText());
        Double stk_qty = Double.valueOf(l_stqty.getText());
        
        if (sell_qty<stk_qty) {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        Vector v = new Vector();
        v.add(inid.getText());//invoice id 
        v.add(com_pro.getSelectedItem().toString());//product name 
        v.add(br_code.getText());//barcode
        v.add(p_qty.getText());// quantity 
        v.add(u_price.getText());//unit price
        v.add(tot_price.getText());//total price
        
        dt.addRow(v);  
        cart_total();
         tot();
        }else{
            
            JOptionPane.showMessageDialog(balance, "Stock Have"+stk_qty+"Qty Only");
        }
        
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // selected reomve 
        try {
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rw =jTable1.getSelectedRow();
            
            
            dt.removeRow(rw);
            
        } catch (Exception e) {
            
        }
        cart_total();
         tot();
        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        //Remove all
        DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
        dt.setRowCount(0);
        
        cart_total();
         tot();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void paid_amtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paid_amtKeyReleased
       
        tot();
        
    }//GEN-LAST:event_paid_amtKeyReleased

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // data send to database
        try {
            //`cartid`, `INID`, `Product_Name`, `Bar_code`, `qty`, `Unit_Price`, `Total_Price`
            
            DefaultTableModel dt = (DefaultTableModel) jTable1.getModel();
            int rc = dt.getRowCount();
            
            for (int i = 0; i < rc ; i++) {
              String inid = dt.getValueAt(i, 0).toString();// get inid
              String P_name = dt.getValueAt(i, 1).toString();//get product name 
               String bar_code = dt.getValueAt(i, 2).toString();//get barcode
               String qty = dt.getValueAt(i, 3).toString();//get product qty 
               String un_price = dt.getValueAt(i, 4).toString();//get product unit price 
               String tot_price = dt.getValueAt(i, 5).toString();//get product total price
               
               //cart DB 
                java.sql.Statement s = db.mycon().createStatement();
                s.executeUpdate("INSERT INTO cart  (INID, Product_Name, Bar_code, qty, Unit_Price, Total_Price) VALUES ('"+inid+"','"+P_name+"','"+bar_code+"','"+qty+"','"+un_price+"','"+tot_price+"')");             
               
            }
            JOptionPane.showMessageDialog(null, "Data saved");
            
        } catch (HeadlessException | SQLException e) {
            System.out.println(e);
        }
        try {
            //sales DB            
                //`saleid`, `INID`, `Cid`, `Customer_Name`, `Total_Qty`, `Total_Bill`, `Status`, `Balance`
                String inv_id = inid.getText();
                String cname = com_cus.getSelectedItem().toString();
                String totqty = tot_qty.getText();
                String tot_bil = bill_tot.getText();
                String blnc = balance.getText();
                
                //paid check 
                Double tot = Double.valueOf(bill_tot.getText());
                Double pid = Double.valueOf(paid_amt.getText());
                String Status = null;
                if(pid.equals(0.0)){
                
                Status = "Unpaid";
                }else if(tot>pid)
                {
                Status = "Partial";
                }else if(tot<pid)
                {
                Status = "Paid";
                }
                 
                    
                
                 
                java.sql.Statement ss = db.mycon().createStatement();
                 ss.executeUpdate("INSERT INTO sales( INID ,Cid, Customer_Name, Total_Qty, Total_Bill, Status, Balance) VALUES('"+inv_id+"','"+cus_id+"','"+cname+"','"+totqty+"','"+tot_bil+"','"+Status+"','"+blnc+"')");

        } catch (NumberFormatException | SQLException e) {
            
            System.out.println(e);
        }
        
        // save last inid number  
        try {
            
            String id = inid.getText();
            java.sql.Statement s =db.mycon().createStatement();
            s.executeUpdate("UPDATE extra SET val='"+id+"' WHERE exid = 1");
        } catch (SQLException e) {
            System.out.println(e);  
        }
        
        stockup();
        
        
    }//GEN-LAST:event_jButton4ActionPerformed

    private void com_cusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_cusActionPerformed
        // get cid
        String name = com_cus.getSelectedItem().toString();
        try {
            java.sql.Statement s =db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT   	cid , customer_name FROM customer WHERE customer_name = '"+name+"'");
            if (rs.next()) {
              cus_id =(rs.getString("	cid "));
              
               
            }
            pro_tot_cal();
            
            
        } catch (SQLException e) {
            System.out.println("e");
        }
        
    }//GEN-LAST:event_com_cusActionPerformed

    private void tot_qtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tot_qtyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tot_qtyKeyReleased

    private void u_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_u_priceKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_u_priceKeyReleased

    private void tot_priceKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tot_priceKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_tot_priceKeyReleased

    private void br_codeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_br_codeKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_br_codeKeyReleased

    private void l_stqtyKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_l_stqtyKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_l_stqtyKeyReleased

    private void com_proActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_com_proActionPerformed
        // TODO add your handling code here:
        String name = com_pro.getSelectedItem().toString();
        try {
            java.sql.Statement s =db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT   Barcode, Price,Qty FROM product WHERE Product_Name= '"+name+"'");
            if (rs.next()) {
                u_price.setText(rs.getString("Price"));
                br_code.setText(rs.getString("Barcode"));
                l_stqty.setText(rs.getString("Qty"));
            }
            pro_tot_cal();

        } catch (SQLException e) {
            System.out.println("e");
        }

    }//GEN-LAST:event_com_proActionPerformed

    private void com_proKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_com_proKeyReleased
        // key released
        String name = com_pro.getToolTipText();
        try {
            java.sql.Statement s =db.mycon().createStatement();
            ResultSet rs = s.executeQuery("SELECT   Barcode, Price,Qty FROM product WHERE Product_Name= '"+name+"'");
            if (rs.next()) {
                u_price.setText(rs.getString("Price"));
                br_code.setText(rs.getString("Barcode"));
                l_stqty.setText(rs.getString("Qty"));
            }
            pro_tot_cal();

        } catch (SQLException e) {
            System.out.println("e");
        }

    }//GEN-LAST:event_com_proKeyReleased

    private void com_proInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_com_proInputMethodTextChanged
        // TODO add your handling code here
    }//GEN-LAST:event_com_proInputMethodTextChanged


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel balance;
    private javax.swing.JLabel bill_tot;
    private javax.swing.JTextField br_code;
    private javax.swing.JLabel br_code1;
    private javax.swing.JComboBox<String> com_cus;
    private javax.swing.JComboBox<String> com_pro;
    private javax.swing.JLabel inid;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField l_stqty;
    private javax.swing.JTextField p_qty;
    private javax.swing.JTextField paid_amt;
    private javax.swing.JTextField tot_price;
    private javax.swing.JTextField tot_qty;
    private javax.swing.JTextField u_price;
    // End of variables declaration//GEN-END:variables

}
