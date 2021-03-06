/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eb_managementapp.UI.Forms;

import Utilities.HTTPConnection;
import static eb_managementapp.EB_ManagementApp.setUpForm;
import eb_managementapp.Entities.Suppliers;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddSuppliesForm extends javax.swing.JFrame {

    final String TITLE = "Add Supplies";

    private ArrayList<Suppliers> suppliersList;
    private JFrame sender;
    private String sessionID;

    public AddSuppliesForm(JFrame sender) {
        initComponents();
        
        sessionID = AdminForm.readSetting(LoginForm.SESSION_FILENAME);
        if (sessionID == null) {
            new LoginForm();
            this.setVisible(false);
        }
        
        this.sender = sender;

        ImageIcon imageIcon = new ImageIcon("C:\\Users\\panay\\Desktop\\EasyBusiness\\EB_ManagementApp\\src\\eb_managementapp\\UI\\Images\\mini_logo.fw.png");
        setIconImage(imageIcon.getImage());
        
        getSuppliers();
        setTitle(TITLE);
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        suppliesPanel = new javax.swing.JPanel();
        supplierLabel = new javax.swing.JLabel();
        supplierComboBox = new javax.swing.JComboBox<>();
        suppliesNameLabel = new javax.swing.JLabel();
        suppliesNameTextField = new javax.swing.JTextField();
        quantityLabel = new javax.swing.JLabel();
        quantitySpinner = new javax.swing.JSpinner();
        priceLabel = new javax.swing.JLabel();
        priceTextField = new javax.swing.JTextField();
        addSuppliesButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        buttonPanel = new javax.swing.JPanel();
        cancelButton = new javax.swing.JButton();
        nextButton = new javax.swing.JButton();

        setResizable(false);

        suppliesPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplies", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(153, 153, 153))); // NOI18N

        supplierLabel.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        supplierLabel.setText("Supplier:");

        supplierComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        supplierComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierComboBoxActionPerformed(evt);
            }
        });

        suppliesNameLabel.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        suppliesNameLabel.setText("Name:");

        suppliesNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                suppliesNameTextFieldActionPerformed(evt);
            }
        });

        quantityLabel.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        quantityLabel.setText("Quantity:");

        priceLabel.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        priceLabel.setText("Price: (€)");

        priceTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                priceTextFieldActionPerformed(evt);
            }
        });

        addSuppliesButton.setText("Add Supplies");
        addSuppliesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSuppliesButtonActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N
        jButton1.setAlignmentY(0.0F);
        jButton1.setPreferredSize(new java.awt.Dimension(49, 25));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout suppliesPanelLayout = new javax.swing.GroupLayout(suppliesPanel);
        suppliesPanel.setLayout(suppliesPanelLayout);
        suppliesPanelLayout.setHorizontalGroup(
            suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppliesPanelLayout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(suppliesPanelLayout.createSequentialGroup()
                        .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(suppliesNameLabel)
                            .addComponent(supplierLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(suppliesNameTextField)
                            .addComponent(supplierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(suppliesPanelLayout.createSequentialGroup()
                        .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, suppliesPanelLayout.createSequentialGroup()
                                .addComponent(priceLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                            .addGroup(suppliesPanelLayout.createSequentialGroup()
                                .addComponent(quantityLabel)
                                .addGap(12, 12, 12)))
                        .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSuppliesButton)
                            .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(quantitySpinner)
                                .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        suppliesPanelLayout.setVerticalGroup(
            suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, suppliesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(supplierComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(supplierLabel)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13)
                .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(suppliesNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(suppliesNameLabel))
                .addGap(15, 15, 15)
                .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(quantitySpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(quantityLabel))
                .addGap(15, 15, 15)
                .addGroup(suppliesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(priceLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addSuppliesButton)
                .addContainerGap())
        );

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        nextButton.setText("OK");
        nextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nextButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(nextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addGap(8, 8, 8)
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(nextButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(suppliesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(suppliesPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void suppliesNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_suppliesNameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_suppliesNameTextFieldActionPerformed

    private void priceTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_priceTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_priceTextFieldActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.setVisible(false);
        if (sender != null) {
            if (sender instanceof SetUpForm) {
                setUpForm = new SetUpForm(this);
            } else if (sender instanceof MainForm) {

            }
        }
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void addSuppliesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSuppliesButtonActionPerformed
        addSupplies();
    }//GEN-LAST:event_addSuppliesButtonActionPerformed

    private void supplierComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierComboBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_supplierComboBoxActionPerformed

    private void nextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nextButtonActionPerformed
        this.setVisible(false);
        if (sender != null) {
            if (sender instanceof SetUpForm) {
                setUpForm = new SetUpForm(this);
            } else if (sender instanceof MainForm) {

            }
        }
    }//GEN-LAST:event_nextButtonActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        getSuppliers();
    }//GEN-LAST:event_jButton1ActionPerformed

   

    private void addSupplies() {
        
        //Get field values:
        String name = suppliesNameTextField.getText().toString();
        String quantity = quantitySpinner.getValue().toString();
        String price = priceTextField.getText().toString();
        int suppliersID = suppliersList.get(supplierComboBox.getSelectedIndex()).getID();
        
          //Check if the supply name is valid
        if (name.trim().isEmpty()) {
            showMessageDialog(null, "Please provide a valid supply name", "Invalid Supply", JOptionPane.PLAIN_MESSAGE);
            return;
        }
        

        //Make the call:
        String addSuppliesJSON = HTTPConnection.executePost(HTTPConnection.API_URL, "Supplies", "Create",
                "SessionID=" + sessionID + "&ID=1&Name=" + name + "&SupplierID=" + suppliersID + "&Quantity=" + quantity + "&Price=" + price
        );
        try {
            JSONObject jsonObject = new JSONObject(addSuppliesJSON);
            final String status = jsonObject.getString("Status");
            final String title = jsonObject.getString("Title");
            final String message = jsonObject.getString("Message");

            showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);

            if (status.equals(HTTPConnection.RESPONSE_OK)) {
                //Reset fields:
                setVisible(true);
                supplierComboBox.setSelectedIndex(0);
                suppliesNameTextField.setText("");
                quantitySpinner.setValue(0);
                priceTextField.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void getSuppliers() {
        suppliersList = new ArrayList<>();
        supplierComboBox.removeAllItems();

        //Get customers from api
        String suppliersJSON = HTTPConnection.executePost(HTTPConnection.API_URL, "Suppliers", "GetMultiple", "SessionID=" + sessionID + "");
        try {
            JSONObject jsonObject = new JSONObject(suppliersJSON);
            final String status = jsonObject.getString("Status");
            final String title = jsonObject.getString("Title");
            final String message = jsonObject.getString("Message");

            if (status.equals(HTTPConnection.RESPONSE_OK)) {
                JSONArray dataArray = jsonObject.getJSONArray("Data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject currentItem = dataArray.getJSONObject(i);

                    int supplierID = currentItem.getInt("ID");
                    String name = currentItem.getString("Name");
                    int countryID = currentItem.getInt("CountryID");
                    String address = currentItem.getString("Address");
                    String telephone = currentItem.getString("Telephone");
                    String city = currentItem.getString("City");

                    Suppliers supplier = new Suppliers(supplierID, name, countryID, address, telephone, city);
                    suppliersList.add(supplier);
                }
            } else {
                showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        for (int i = 0; i < suppliersList.size(); i++) {
            supplierComboBox.addItem(suppliersList.get(i).getName());
        }

    }

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
            java.util.logging.Logger.getLogger(AddSuppliesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddSuppliesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddSuppliesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddSuppliesForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddSuppliesForm(new JFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addSuppliesButton;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton nextButton;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
    private javax.swing.JLabel quantityLabel;
    private javax.swing.JSpinner quantitySpinner;
    private javax.swing.JComboBox<String> supplierComboBox;
    private javax.swing.JLabel supplierLabel;
    private javax.swing.JLabel suppliesNameLabel;
    private javax.swing.JTextField suppliesNameTextField;
    private javax.swing.JPanel suppliesPanel;
    // End of variables declaration//GEN-END:variables
}
