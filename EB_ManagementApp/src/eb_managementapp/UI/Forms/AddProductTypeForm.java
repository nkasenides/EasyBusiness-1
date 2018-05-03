/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eb_managementapp.UI.Forms;

import Utilities.HTTPConnection;
import eb_managementapp.Entities.Producttypes;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import org.json.JSONArray;
import org.json.JSONObject;

public class AddProductTypeForm extends javax.swing.JFrame {

    final String TITLE = "Add Type";
    
    private ArrayList<Producttypes> productTypeList;
    private JFrame sender;
    
    public AddProductTypeForm(JFrame sender) {
        initComponents();
        
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\panay\\Desktop\\EasyBusiness\\EB_ManagementApp\\src\\eb_managementapp\\UI\\Images\\mini_logo.fw.png");
        setIconImage(imageIcon.getImage());

        this.sender = sender;

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

        addTypePanel = new javax.swing.JPanel();
        nameLabel = new javax.swing.JLabel();
        nameTypeTextField = new javax.swing.JTextField();
        buttonPanel = new javax.swing.JPanel();
        addButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        addTypePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Add Type", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 13), new java.awt.Color(153, 153, 153))); // NOI18N

        nameLabel.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        nameLabel.setText("Name:");

        nameTypeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nameTypeTextFieldActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout addTypePanelLayout = new javax.swing.GroupLayout(addTypePanel);
        addTypePanel.setLayout(addTypePanelLayout);
        addTypePanelLayout.setHorizontalGroup(
            addTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTypePanelLayout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(nameLabel)
                .addGap(18, 18, 18)
                .addComponent(nameTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addTypePanelLayout.setVerticalGroup(
            addTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addTypePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addTypePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nameTypeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        addButton.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        addButton.setText("Add");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        cancelButton.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonPanelLayout = new javax.swing.GroupLayout(buttonPanel);
        buttonPanel.setLayout(buttonPanelLayout);
        buttonPanelLayout.setHorizontalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buttonPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(cancelButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(addButton)
                .addGap(58, 58, 58))
        );
        buttonPanelLayout.setVerticalGroup(
            buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(cancelButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(addTypePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(addTypePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(buttonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(11, 11, 11))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        setVisible(false);
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void nameTypeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nameTypeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nameTypeTextFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        setVisible(false);
        addType();
        if(sender != null && (sender instanceof AddProductsForm)){
        AddProductsForm senderForm = (AddProductsForm) sender;
        senderForm.getProductTypes();
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void addType() {

        //Get field values:
        String name = nameTypeTextField.getText();

        //Check if the product name is valid
        if (name.trim().isEmpty()) {
            showMessageDialog(null, "Please provide a valid product type", "Invalid Product Type", JOptionPane.PLAIN_MESSAGE);
            this.setVisible(true);
            return;
        }
        
        //Make the call:
        String addTypeJSON = HTTPConnection.executePost(HTTPConnection.API_URL, "Producttypes", "Create",
                "SessionID=aa&ID=1&Name=" + name );
        try {
            JSONObject jsonObject = new JSONObject(addTypeJSON);
            final String status = jsonObject.getString("Status");
            final String title = jsonObject.getString("Title");
            final String message = jsonObject.getString("Message");

            showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);

            if (status.equals(HTTPConnection.RESPONSE_ERROR)) {
                System.out.println("Fail " + addTypeJSON);
            } else if (status.equals(HTTPConnection.RESPONSE_OK)) {
                //Reset fields:
                nameTypeTextField.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        getProductType();
    }


    public void getProductType() {
        productTypeList = new ArrayList<>();

        //Get sizes from api
        String sizesJSON = HTTPConnection.executePost(HTTPConnection.API_URL, "Producttypes", "GetMultiple", "SessionID=aa");
        try {
            JSONObject jsonObject = new JSONObject(sizesJSON);
            final String status = jsonObject.getString("Status");
            final String title = jsonObject.getString("Title");
            final String message = jsonObject.getString("Message");

            if (status.equals(HTTPConnection.RESPONSE_OK)) {
                JSONArray dataArray = jsonObject.getJSONArray("Data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject currentItem = dataArray.getJSONObject(i);

                    int typeID = currentItem.getInt("ID");
                    String name = currentItem.getString("Name");

                    Producttypes productTypes = new Producttypes(typeID, name);
                    productTypeList.add(productTypes);
                }
            } else {
                showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
                System.out.println("Fail " + sizesJSON);
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            java.util.logging.Logger.getLogger(AddProductTypeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProductTypeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProductTypeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProductTypeForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AddProductTypeForm(new JFrame()).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JPanel addTypePanel;
    private javax.swing.JPanel buttonPanel;
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTypeTextField;
    // End of variables declaration//GEN-END:variables
}
