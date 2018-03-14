/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eb_managementapp.UI;

import Utilities.HTTPConnection;
import eb_managementapp.Entities.Customers;
import eb_managementapp.Entities.Users;
import org.json.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class MainForm extends javax.swing.JFrame {

    /**
     * Creates new form MainForm
     */
    public MainForm() {
        initComponents();

        getCustomers();
        
        getEmployees();
        
        
        

        customerScrollPanel.setViewportView(customerDetailsTable);

        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.setValue(80, "Marks", "Value 1");

        dataset.setValue(70, "Marks", "Value 2");

        dataset.setValue(75, "Marks", "Value 3");

        JFreeChart chart = ChartFactory.createBarChart("Student's Score", "Student's Name", "Marks", dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot p = chart.getCategoryPlot();

        p.setRangeGridlinePaint(Color.black);

        //Panel (same window):
        ChartPanel chartPanel = new ChartPanel(chart);
        statistics.setLayout(new java.awt.BorderLayout());
        statistics.add(chartPanel, BorderLayout.CENTER);
        statistics.validate();

        //Panel (same window):
        ChartPanel chartPanel2 = new ChartPanel(chart);
        customersGraphsPanel2.setLayout(new java.awt.BorderLayout());
        customersGraphsPanel2.add(chartPanel2, BorderLayout.CENTER);
        customersGraphsPanel2.validate();

        this.setExtendedState(JFrame.MAXIMIZED_BOTH);

        this.setVisible(true);

    }
    
    public void getCustomers() {
        ArrayList<Customers> customersList = new ArrayList<>();

        //Get customers from api
        String customersJSON = HTTPConnection.executePost(HTTPConnection.API_URL, "Customers", "GetMultiple", "Limit=2&SessionID=aa");
        try {
            JSONObject jsonObject = new JSONObject(customersJSON);
            final String status = jsonObject.getString("Status");
            final String title = jsonObject.getString("Title");
            final String message = jsonObject.getString("Message");

            if (status.equals(HTTPConnection.RESPONSE_OK)) {
                JSONArray dataArray = jsonObject.getJSONArray("Data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject currentItem = dataArray.getJSONObject(i);

                    int id = currentItem.getInt("ID");
                    String name = currentItem.getString("Name");
                    int countryID = currentItem.getInt("CountryID");
                    String city = currentItem.getString("City");
                    String telephone = currentItem.getString("Telephone");
                    String address = currentItem.getString("Address");
                    int customerProductsID = currentItem.getInt("CustomerProductsID");

                    Customers customer = new Customers(id, name, countryID, city, telephone, address, customerProductsID);
                    customersList.add(customer);
                }
            } else {
                showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
                System.out.println("Fail " + customersJSON);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create a new model for the table:
        DefaultTableModel customersTableModel = new DefaultTableModel();
        
        //Add the table columns:
        customersTableModel.addColumn("ID");
        customersTableModel.addColumn("Name");
        customersTableModel.addColumn("Telephone");
        customersTableModel.addColumn("Address");
        customersTableModel.addColumn("City");
        customersTableModel.addColumn("Country");
        
        //Add each item in the list as a row in the table:
        for (int i = 0; i < customersList.size(); i++) {
            Object[] currentRow = { 
                customersList.get(i).getID(), 
                customersList.get(i).getName(),
                customersList.get(i).getTelephone(),
                customersList.get(i).getAddress(),
                customersList.get(i).getCity(),
                customersList.get(i).getCountryID()
            };
            customersTableModel.addRow(currentRow);
        }
        customerDetailsTable.setModel(customersTableModel);
        
    }
    
    public void getEmployees() {
        ArrayList<Users> employeesList = new ArrayList<>();

        //Get customers from api
        String employeesJSON = HTTPConnection.executePost(HTTPConnection.API_URL, "Users", "GetMultiple", "Limit=2&SessionID=aa");
        try {
            JSONObject jsonObject = new JSONObject(employeesJSON);
            final String status = jsonObject.getString("Status");
            final String title = jsonObject.getString("Title");
            final String message = jsonObject.getString("Message");

            if (status.equals(HTTPConnection.RESPONSE_OK)) {
                JSONArray dataArray = jsonObject.getJSONArray("Data");
                for (int i = 0; i < dataArray.length(); i++) {
                    JSONObject currentItem = dataArray.getJSONObject(i);

                    int userID = currentItem.getInt("UserID");
                    String username = currentItem.getString("Username");
                    String firstname = currentItem.getString("Firstname");
                    String lastname = currentItem.getString("Lastname");
                    String password = currentItem.getString("Password");
                    
                    //TODO Check:
                    long dateHiredLong = currentItem.getLong("DateHired");
                    int dateHired = (int) dateHiredLong;

                    int countryID = currentItem.getInt("CountryID");
                    String city = currentItem.getString("City");
                    String telephone = currentItem.getString("Telephone");
                    String address = currentItem.getString("Address");
                    int userLevelID = currentItem.getInt("UserLevelID");

                    Users employees = new Users(userID, username,password,userLevelID,firstname, lastname, dateHired,city, address,telephone,countryID );
                    employeesList.add(employees);
                }
            } else {
                showMessageDialog(null, message, title, JOptionPane.PLAIN_MESSAGE);
                System.out.println("Fail " + employeesJSON);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Create a new model for the table:
        DefaultTableModel employeesTableModel = new DefaultTableModel();
        
        //Add the table columns:
        employeesTableModel.addColumn("ID");
        employeesTableModel.addColumn("Username");
        employeesTableModel.addColumn("Firstname");
        employeesTableModel.addColumn("Lastname");
        employeesTableModel.addColumn("Date Hired");
        employeesTableModel.addColumn("Telephone");
        employeesTableModel.addColumn("Address");
        employeesTableModel.addColumn("City");
        employeesTableModel.addColumn("Country");
        
        //Add each item in the list as a row in the table:
        for (int i = 0; i < employeesList.size(); i++) {
            Object[] currentRow = { 
                employeesList.get(i).getUserID(), 
                employeesList.get(i).getUsername(),
                employeesList.get(i).getFirstname(),
                employeesList.get(i).getLastname(),
                Users.DATE_FORMAT.format(new Date(employeesList.get(i).getDateHired())),
                employeesList.get(i).getTelephone(),
                employeesList.get(i).getAddress(),
                employeesList.get(i).getCity(),
                employeesList.get(i).getCountryID()
            };
            employeesTableModel.addRow(currentRow);
        }
        employeesTable.setModel(employeesTableModel);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPanel = new javax.swing.JTabbedPane();
        home = new javax.swing.JPanel();
        statistics = new javax.swing.JPanel();
        dialyProduction = new javax.swing.JPanel();
        productionScrollPane = new javax.swing.JScrollPane();
        dailyProductionTable = new javax.swing.JTable();
        moreProdDetailsBtn = new javax.swing.JButton();
        latestSales = new javax.swing.JPanel();
        salesScrollPanel = new javax.swing.JScrollPane();
        dailySalesTable = new javax.swing.JTable();
        moreSalesDetailsBtn = new javax.swing.JButton();
        latestPurchases = new javax.swing.JPanel();
        purchasesScrollPanel = new javax.swing.JScrollPane();
        dailyPurchasesTable = new javax.swing.JTable();
        morePurchDetailsBtn = new javax.swing.JButton();
        inventory = new javax.swing.JPanel();
        invenrotyTabPanel = new javax.swing.JTabbedPane();
        productsTab = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jList3 = new javax.swing.JList<>();
        suppliesITab = new javax.swing.JPanel();
        production = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        productNameTextField = new javax.swing.JLabel();
        productNameTextField1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        productNameTextField2 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jSpinner1 = new javax.swing.JSpinner();
        productNameTextField3 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        jButton1 = new javax.swing.JButton();
        productNameTextField4 = new javax.swing.JLabel();
        productNameTextField5 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        sales = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList<>();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jList2 = new javax.swing.JList<>();
        jPanel4 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        customers = new javax.swing.JPanel();
        noCustomerLb = new javax.swing.JLabel();
        customerDetailsPanel = new javax.swing.JPanel();
        expCusDetailsBtn = new javax.swing.JButton();
        printCusDetailsBtn = new javax.swing.JButton();
        importCustomerBtn = new javax.swing.JButton();
        searchCustomerTxt = new javax.swing.JTextField();
        refreshCusDetailsBtn = new javax.swing.JButton();
        customerScrollPanel = new javax.swing.JScrollPane();
        customerDetailsTable = new javax.swing.JTable();
        customerTabPanel = new javax.swing.JTabbedPane();
        custSalesPanel = new javax.swing.JPanel();
        importCustSalesBtn = new javax.swing.JButton();
        searchCustSalesTxt = new javax.swing.JTextField();
        exportCustSalesBtn = new javax.swing.JButton();
        printCustSalesBtn = new javax.swing.JButton();
        refreshCustSalesTable = new javax.swing.JButton();
        customersSalesTabPane = new javax.swing.JScrollPane();
        custSalesTable = new javax.swing.JTable();
        custProductsPanel = new javax.swing.JPanel();
        exportCustProductsBtn = new javax.swing.JButton();
        printCustProductsBtn = new javax.swing.JButton();
        searchCustProductsTxt = new javax.swing.JTextField();
        refreshCustProductsBtn = new javax.swing.JButton();
        importCustProductsBtn = new javax.swing.JButton();
        custProScrollPanel = new javax.swing.JScrollPane();
        custProductsTable = new javax.swing.JTable();
        customersGraphsPanel = new javax.swing.JPanel();
        customersGraphsPanel2 = new javax.swing.JPanel();
        employees = new javax.swing.JPanel();
        noEmployeesLb = new javax.swing.JLabel();
        employeeListPanel = new javax.swing.JPanel();
        employeeScrollPanel = new javax.swing.JScrollPane();
        employeesTable = new javax.swing.JTable();
        searchTxt = new javax.swing.JTextField();
        exportFilesBtn = new javax.swing.JButton();
        printBtn = new javax.swing.JButton();
        refreshBtn = new javax.swing.JButton();
        importEmplBtn = new javax.swing.JButton();
        suppliers = new javax.swing.JPanel();
        noSuppliesLb = new javax.swing.JLabel();
        suppliersDetailsPanel1 = new javax.swing.JPanel();
        expSupDetailsBtn1 = new javax.swing.JButton();
        printSupDetailsBtn1 = new javax.swing.JButton();
        importSupplierBtn = new javax.swing.JButton();
        searchSupplierTxt = new javax.swing.JTextField();
        refreshSuplTableBtn = new javax.swing.JButton();
        supplierScrollPanel = new javax.swing.JScrollPane();
        customerDetailsTable1 = new javax.swing.JTable();
        suppliesTab = new javax.swing.JTabbedPane();
        suppliesTabPanel = new javax.swing.JPanel();
        importSuppliesBtn = new javax.swing.JButton();
        searchSuppliesTxt = new javax.swing.JTextField();
        exportSuppliesFilesBtn = new javax.swing.JButton();
        printSuppliesBtn = new javax.swing.JButton();
        refreshSuppliesTableBtn = new javax.swing.JButton();
        suppliesTabPane = new javax.swing.JScrollPane();
        custSalesTable1 = new javax.swing.JTable();
        purchasesHistoryPanel = new javax.swing.JPanel();
        exportPurchFilesBtn = new javax.swing.JButton();
        printPurchasesBtn = new javax.swing.JButton();
        searchPurchasesTxt = new javax.swing.JTextField();
        refreshPurchasesTableBtn = new javax.swing.JButton();
        importPurchaseBtn = new javax.swing.JButton();
        purchHistoryScrollPanel = new javax.swing.JScrollPane();
        purchHistoryTable = new javax.swing.JTable();
        supplierGraphPanel = new javax.swing.JPanel();
        suppliesGraphPanel = new javax.swing.JPanel();
        menuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        view = new javax.swing.JMenu();
        options = new javax.swing.JMenu();
        tools = new javax.swing.JMenu();
        help = new javax.swing.JMenu();
        about = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tabPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        statistics.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Statistics", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout statisticsLayout = new javax.swing.GroupLayout(statistics);
        statistics.setLayout(statisticsLayout);
        statisticsLayout.setHorizontalGroup(
            statisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 302, Short.MAX_VALUE)
        );
        statisticsLayout.setVerticalGroup(
            statisticsLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        dialyProduction.setBorder(javax.swing.BorderFactory.createTitledBorder("Daily Production"));

        dailyProductionTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        productionScrollPane.setViewportView(dailyProductionTable);

        moreProdDetailsBtn.setText("+ More Details");
        moreProdDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreProdDetailsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout dialyProductionLayout = new javax.swing.GroupLayout(dialyProduction);
        dialyProduction.setLayout(dialyProductionLayout);
        dialyProductionLayout.setHorizontalGroup(
            dialyProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dialyProductionLayout.createSequentialGroup()
                .addGroup(dialyProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(productionScrollPane)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialyProductionLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(moreProdDetailsBtn)))
                .addContainerGap())
        );
        dialyProductionLayout.setVerticalGroup(
            dialyProductionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, dialyProductionLayout.createSequentialGroup()
                .addComponent(moreProdDetailsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(productionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        latestSales.setBorder(javax.swing.BorderFactory.createTitledBorder("Latest Sales"));

        dailySalesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        salesScrollPanel.setViewportView(dailySalesTable);

        moreSalesDetailsBtn.setText("+ More Details");
        moreSalesDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moreSalesDetailsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout latestSalesLayout = new javax.swing.GroupLayout(latestSales);
        latestSales.setLayout(latestSalesLayout);
        latestSalesLayout.setHorizontalGroup(
            latestSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, latestSalesLayout.createSequentialGroup()
                .addGroup(latestSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(latestSalesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(salesScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE))
                    .addGroup(latestSalesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(moreSalesDetailsBtn)))
                .addContainerGap())
        );
        latestSalesLayout.setVerticalGroup(
            latestSalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(latestSalesLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(moreSalesDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(salesScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        latestPurchases.setBorder(javax.swing.BorderFactory.createTitledBorder("Latest Purchases"));

        dailyPurchasesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        purchasesScrollPanel.setViewportView(dailyPurchasesTable);

        morePurchDetailsBtn.setText("+ More Details");
        morePurchDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                morePurchDetailsBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout latestPurchasesLayout = new javax.swing.GroupLayout(latestPurchases);
        latestPurchases.setLayout(latestPurchasesLayout);
        latestPurchasesLayout.setHorizontalGroup(
            latestPurchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(latestPurchasesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(latestPurchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(purchasesScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 722, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, latestPurchasesLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(morePurchDetailsBtn)))
                .addContainerGap())
        );
        latestPurchasesLayout.setVerticalGroup(
            latestPurchasesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(latestPurchasesLayout.createSequentialGroup()
                .addComponent(morePurchDetailsBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(purchasesScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout homeLayout = new javax.swing.GroupLayout(home);
        home.setLayout(homeLayout);
        homeLayout.setHorizontalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(latestPurchases, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(latestSales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dialyProduction, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        homeLayout.setVerticalGroup(
            homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(homeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(homeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(statistics, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(homeLayout.createSequentialGroup()
                        .addComponent(dialyProduction, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(latestSales, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(latestPurchases, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        tabPanel.addTab("Home", home);

        invenrotyTabPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jList3.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane3.setViewportView(jList3);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 579, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout productsTabLayout = new javax.swing.GroupLayout(productsTab);
        productsTab.setLayout(productsTabLayout);
        productsTabLayout.setHorizontalGroup(
            productsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(500, Short.MAX_VALUE))
        );
        productsTabLayout.setVerticalGroup(
            productsTabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productsTabLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(285, Short.MAX_VALUE))
        );

        invenrotyTabPanel.addTab("Products", productsTab);

        javax.swing.GroupLayout suppliesITabLayout = new javax.swing.GroupLayout(suppliesITab);
        suppliesITab.setLayout(suppliesITabLayout);
        suppliesITabLayout.setHorizontalGroup(
            suppliesITabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
        );
        suppliesITabLayout.setVerticalGroup(
            suppliesITabLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 654, Short.MAX_VALUE)
        );

        invenrotyTabPanel.addTab("Supplies", suppliesITab);

        javax.swing.GroupLayout inventoryLayout = new javax.swing.GroupLayout(inventory);
        inventory.setLayout(inventoryLayout);
        inventoryLayout.setHorizontalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(invenrotyTabPanel)
        );
        inventoryLayout.setVerticalGroup(
            inventoryLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(invenrotyTabPanel)
        );

        tabPanel.addTab("Inventory", inventory);

        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Add Production"));

        productNameTextField.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productNameTextField.setText("Product Name:");

        productNameTextField1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productNameTextField1.setText("Bottle Sizes:");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        productNameTextField2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productNameTextField2.setText("Product Quantity:");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        productNameTextField3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productNameTextField3.setText("Bottle Quantity:");

        jButton1.setText("Add Bottles Quanity");

        productNameTextField4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productNameTextField4.setText("Remaining Quantity:");

        productNameTextField5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        productNameTextField5.setText("example - 500 litres");

        jButton2.setText("Finish");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(productNameTextField2)
                    .addComponent(productNameTextField)
                    .addComponent(productNameTextField1)
                    .addComponent(productNameTextField3)
                    .addComponent(productNameTextField4))
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.Alignment.LEADING, 0, 100, Short.MAX_VALUE)
                                    .addComponent(jSpinner1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jSpinner2, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(74, 74, 74)
                                .addComponent(jButton1)))
                        .addContainerGap(483, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(jButton2))
                            .addComponent(productNameTextField5))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameTextField)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameTextField2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameTextField1)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameTextField3)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(productNameTextField4)
                    .addComponent(productNameTextField5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Supplies for each Production"));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 213, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout productionLayout = new javax.swing.GroupLayout(production);
        production.setLayout(productionLayout);
        productionLayout.setHorizontalGroup(
            productionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(productionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(156, Short.MAX_VALUE))
        );
        productionLayout.setVerticalGroup(
            productionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(productionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(187, Short.MAX_VALUE))
        );

        tabPanel.addTab("Production", production);

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Items Daily Sales"));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 782, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jList1.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item - Customer 1", "Item - Customer 2" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane1.setViewportView(jList1);

        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder("Customers Daily Sales"));

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 173, Short.MAX_VALUE)
        );

        jList2.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item - Orage Juice", "Item - Lemon Juice" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        jScrollPane2.setViewportView(jList2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)
                        .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 265, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(4, 4, 4)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTabbedPane1.addTab("Daily Sales", jPanel2);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 655, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Weekly Sales", jPanel4);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 655, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Monthly Sales", jPanel3);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1089, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 655, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Yearly Sales", jPanel5);

        javax.swing.GroupLayout salesLayout = new javax.swing.GroupLayout(sales);
        sales.setLayout(salesLayout);
        salesLayout.setHorizontalGroup(
            salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        salesLayout.setVerticalGroup(
            salesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        tabPanel.addTab("Sales", sales);

        noCustomerLb.setText("No. Customers: ###");

        customerDetailsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        expCusDetailsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/export.png"))); // NOI18N
        expCusDetailsBtn.setPreferredSize(new java.awt.Dimension(49, 25));
        expCusDetailsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expCusDetailsBtnActionPerformed(evt);
            }
        });

        printCusDetailsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/printer_hardware.png"))); // NOI18N

        importCustomerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/create employee.png"))); // NOI18N

        searchCustomerTxt.setText("Search....");
        searchCustomerTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustomerTxtActionPerformed(evt);
            }
        });

        refreshCusDetailsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N

        customerDetailsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        customerScrollPanel.setViewportView(customerDetailsTable);

        javax.swing.GroupLayout customerDetailsPanelLayout = new javax.swing.GroupLayout(customerDetailsPanel);
        customerDetailsPanel.setLayout(customerDetailsPanelLayout);
        customerDetailsPanelLayout.setHorizontalGroup(
            customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, customerDetailsPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(expCusDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printCusDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchCustomerTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshCusDetailsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(importCustomerBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(customerDetailsPanelLayout.createSequentialGroup()
                .addComponent(customerScrollPanel)
                .addContainerGap())
        );
        customerDetailsPanelLayout.setVerticalGroup(
            customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerDetailsPanelLayout.createSequentialGroup()
                .addGroup(customerDetailsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printCusDetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expCusDetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshCusDetailsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchCustomerTxt)
                    .addComponent(importCustomerBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(customerScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        customerTabPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        customerTabPanel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        customerTabPanel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        importCustSalesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/create employee.png"))); // NOI18N

        searchCustSalesTxt.setText("Search....");
        searchCustSalesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustSalesTxtActionPerformed(evt);
            }
        });

        exportCustSalesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/export.png"))); // NOI18N
        exportCustSalesBtn.setPreferredSize(new java.awt.Dimension(49, 25));
        exportCustSalesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCustSalesBtnActionPerformed(evt);
            }
        });

        printCustSalesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/printer_hardware.png"))); // NOI18N

        refreshCustSalesTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N

        custSalesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        customersSalesTabPane.setViewportView(custSalesTable);

        javax.swing.GroupLayout custSalesPanelLayout = new javax.swing.GroupLayout(custSalesPanel);
        custSalesPanel.setLayout(custSalesPanelLayout);
        custSalesPanelLayout.setHorizontalGroup(
            custSalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custSalesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(custSalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, custSalesPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(exportCustSalesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printCustSalesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchCustSalesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshCustSalesTable, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(importCustSalesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(customersSalesTabPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addContainerGap())
        );
        custSalesPanelLayout.setVerticalGroup(
            custSalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custSalesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(custSalesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printCustSalesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportCustSalesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshCustSalesTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchCustSalesTxt)
                    .addComponent(importCustSalesBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(customersSalesTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        customerTabPanel.addTab("Customer Sales", custSalesPanel);

        exportCustProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/export.png"))); // NOI18N
        exportCustProductsBtn.setPreferredSize(new java.awt.Dimension(49, 25));
        exportCustProductsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportCustProductsBtnActionPerformed(evt);
            }
        });

        printCustProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/printer_hardware.png"))); // NOI18N

        searchCustProductsTxt.setText("Search....");
        searchCustProductsTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchCustProductsTxtActionPerformed(evt);
            }
        });

        refreshCustProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N

        importCustProductsBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/create employee.png"))); // NOI18N

        custProductsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        custProScrollPanel.setViewportView(custProductsTable);

        javax.swing.GroupLayout custProductsPanelLayout = new javax.swing.GroupLayout(custProductsPanel);
        custProductsPanel.setLayout(custProductsPanelLayout);
        custProductsPanelLayout.setHorizontalGroup(
            custProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custProductsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(custProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, custProductsPanelLayout.createSequentialGroup()
                        .addGap(0, 475, Short.MAX_VALUE)
                        .addComponent(exportCustProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printCustProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchCustProductsTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshCustProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(importCustProductsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(custProScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addContainerGap())
        );
        custProductsPanelLayout.setVerticalGroup(
            custProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(custProductsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(custProductsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printCustProductsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportCustProductsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshCustProductsBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchCustProductsTxt)
                    .addComponent(importCustProductsBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(43, 43, 43)
                .addComponent(custProScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        customerTabPanel.addTab("Customer Prooducts", custProductsPanel);

        customersGraphsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customers Sales %", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout customersGraphsPanelLayout = new javax.swing.GroupLayout(customersGraphsPanel);
        customersGraphsPanel.setLayout(customersGraphsPanelLayout);
        customersGraphsPanelLayout.setHorizontalGroup(
            customersGraphsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );
        customersGraphsPanelLayout.setVerticalGroup(
            customersGraphsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        customersGraphsPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Customer Sales per month", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout customersGraphsPanel2Layout = new javax.swing.GroupLayout(customersGraphsPanel2);
        customersGraphsPanel2.setLayout(customersGraphsPanel2Layout);
        customersGraphsPanel2Layout.setHorizontalGroup(
            customersGraphsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        customersGraphsPanel2Layout.setVerticalGroup(
            customersGraphsPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout customersLayout = new javax.swing.GroupLayout(customers);
        customers.setLayout(customersLayout);
        customersLayout.setHorizontalGroup(
            customersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customersLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(customersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customersLayout.createSequentialGroup()
                        .addComponent(noCustomerLb)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(customersLayout.createSequentialGroup()
                        .addGroup(customersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(customerDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customerTabPanel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(customersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(customersGraphsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(customersGraphsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        customersLayout.setVerticalGroup(
            customersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noCustomerLb)
                .addGap(32, 32, 32)
                .addGroup(customersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(customersGraphsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(customerDetailsPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(customersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(customersLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(customerTabPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(customersLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(customersGraphsPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        tabPanel.addTab("Customers", customers);

        noEmployeesLb.setText("No. Employees: ###");

        employeeListPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "List Of Employess", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        employeesTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        employeeScrollPanel.setViewportView(employeesTable);

        searchTxt.setText("Search....");
        searchTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchTxtActionPerformed(evt);
            }
        });

        exportFilesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/export.png"))); // NOI18N
        exportFilesBtn.setPreferredSize(new java.awt.Dimension(49, 25));
        exportFilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportFilesBtnActionPerformed(evt);
            }
        });

        printBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/printer_hardware.png"))); // NOI18N

        refreshBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N

        importEmplBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/create employee.png"))); // NOI18N

        javax.swing.GroupLayout employeeListPanelLayout = new javax.swing.GroupLayout(employeeListPanel);
        employeeListPanel.setLayout(employeeListPanelLayout);
        employeeListPanelLayout.setHorizontalGroup(
            employeeListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeeListPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(employeeListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(employeeScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 1042, Short.MAX_VALUE)
                    .addGroup(employeeListPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(exportFilesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(importEmplBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        employeeListPanelLayout.setVerticalGroup(
            employeeListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, employeeListPanelLayout.createSequentialGroup()
                .addGroup(employeeListPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportFilesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchTxt)
                    .addComponent(importEmplBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(employeeScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout employeesLayout = new javax.swing.GroupLayout(employees);
        employees.setLayout(employeesLayout);
        employeesLayout.setHorizontalGroup(
            employeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(employeeListPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(employeesLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(noEmployeesLb)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        employeesLayout.setVerticalGroup(
            employeesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(employeesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noEmployeesLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(employeeListPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 515, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );

        tabPanel.addTab("Employees", employees);

        noSuppliesLb.setText("No. Suppliers: ###");

        suppliersDetailsPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier Details", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        expSupDetailsBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/export.png"))); // NOI18N
        expSupDetailsBtn1.setPreferredSize(new java.awt.Dimension(49, 25));
        expSupDetailsBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                expSupDetailsBtn1ActionPerformed(evt);
            }
        });

        printSupDetailsBtn1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/printer_hardware.png"))); // NOI18N

        importSupplierBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/create employee.png"))); // NOI18N
        importSupplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                importSupplierBtnActionPerformed(evt);
            }
        });

        searchSupplierTxt.setText("Search....");
        searchSupplierTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSupplierTxtActionPerformed(evt);
            }
        });

        refreshSuplTableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N

        customerDetailsTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        supplierScrollPanel.setViewportView(customerDetailsTable1);

        javax.swing.GroupLayout suppliersDetailsPanel1Layout = new javax.swing.GroupLayout(suppliersDetailsPanel1);
        suppliersDetailsPanel1.setLayout(suppliersDetailsPanel1Layout);
        suppliersDetailsPanel1Layout.setHorizontalGroup(
            suppliersDetailsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, suppliersDetailsPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(expSupDetailsBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(printSupDetailsBtn1, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchSupplierTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refreshSuplTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(2, 2, 2)
                .addComponent(importSupplierBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(suppliersDetailsPanel1Layout.createSequentialGroup()
                .addComponent(supplierScrollPanel)
                .addContainerGap())
        );
        suppliersDetailsPanel1Layout.setVerticalGroup(
            suppliersDetailsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppliersDetailsPanel1Layout.createSequentialGroup()
                .addGroup(suppliersDetailsPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printSupDetailsBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expSupDetailsBtn1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshSuplTableBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchSupplierTxt)
                    .addComponent(importSupplierBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(18, 18, 18)
                .addComponent(supplierScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                .addContainerGap())
        );

        suppliesTab.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        suppliesTab.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        suppliesTab.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        importSuppliesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/create employee.png"))); // NOI18N

        searchSuppliesTxt.setText("Search....");
        searchSuppliesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSuppliesTxtActionPerformed(evt);
            }
        });

        exportSuppliesFilesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/export.png"))); // NOI18N
        exportSuppliesFilesBtn.setPreferredSize(new java.awt.Dimension(49, 25));
        exportSuppliesFilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportSuppliesFilesBtnActionPerformed(evt);
            }
        });

        printSuppliesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/printer_hardware.png"))); // NOI18N

        refreshSuppliesTableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N

        custSalesTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        suppliesTabPane.setViewportView(custSalesTable1);

        javax.swing.GroupLayout suppliesTabPanelLayout = new javax.swing.GroupLayout(suppliesTabPanel);
        suppliesTabPanel.setLayout(suppliesTabPanelLayout);
        suppliesTabPanelLayout.setHorizontalGroup(
            suppliesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppliesTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(suppliesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, suppliesTabPanelLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(exportSuppliesFilesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printSuppliesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchSuppliesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshSuppliesTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(importSuppliesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(suppliesTabPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addContainerGap())
        );
        suppliesTabPanelLayout.setVerticalGroup(
            suppliesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppliesTabPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(suppliesTabPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printSuppliesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportSuppliesFilesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshSuppliesTableBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchSuppliesTxt)
                    .addComponent(importSuppliesBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(suppliesTabPane, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        suppliesTab.addTab("Supplies", suppliesTabPanel);

        exportPurchFilesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/export.png"))); // NOI18N
        exportPurchFilesBtn.setPreferredSize(new java.awt.Dimension(49, 25));
        exportPurchFilesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exportPurchFilesBtnActionPerformed(evt);
            }
        });

        printPurchasesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/printer_hardware.png"))); // NOI18N

        searchPurchasesTxt.setText("Search....");
        searchPurchasesTxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPurchasesTxtActionPerformed(evt);
            }
        });

        refreshPurchasesTableBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/Refresh_icon.svg.png"))); // NOI18N

        importPurchaseBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/eb_managementapp/UI/Images/create employee.png"))); // NOI18N

        purchHistoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        purchHistoryScrollPanel.setViewportView(purchHistoryTable);

        javax.swing.GroupLayout purchasesHistoryPanelLayout = new javax.swing.GroupLayout(purchasesHistoryPanel);
        purchasesHistoryPanel.setLayout(purchasesHistoryPanelLayout);
        purchasesHistoryPanelLayout.setHorizontalGroup(
            purchasesHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasesHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(purchasesHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, purchasesHistoryPanelLayout.createSequentialGroup()
                        .addGap(0, 475, Short.MAX_VALUE)
                        .addComponent(exportPurchFilesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(printPurchasesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchPurchasesTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshPurchasesTableBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2)
                        .addComponent(importPurchaseBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(purchHistoryScrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 712, Short.MAX_VALUE))
                .addContainerGap())
        );
        purchasesHistoryPanelLayout.setVerticalGroup(
            purchasesHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(purchasesHistoryPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(purchasesHistoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(printPurchasesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(exportPurchFilesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(refreshPurchasesTableBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(searchPurchasesTxt)
                    .addComponent(importPurchaseBtn, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(43, 43, 43)
                .addComponent(purchHistoryScrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(61, Short.MAX_VALUE))
        );

        suppliesTab.addTab("Purchases Records", purchasesHistoryPanel);

        supplierGraphPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplier purchases %", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout supplierGraphPanelLayout = new javax.swing.GroupLayout(supplierGraphPanel);
        supplierGraphPanel.setLayout(supplierGraphPanelLayout);
        supplierGraphPanelLayout.setHorizontalGroup(
            supplierGraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );
        supplierGraphPanelLayout.setVerticalGroup(
            supplierGraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        suppliesGraphPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Supplies purchases  per month", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

        javax.swing.GroupLayout suppliesGraphPanelLayout = new javax.swing.GroupLayout(suppliesGraphPanel);
        suppliesGraphPanel.setLayout(suppliesGraphPanelLayout);
        suppliesGraphPanelLayout.setHorizontalGroup(
            suppliesGraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        suppliesGraphPanelLayout.setVerticalGroup(
            suppliesGraphPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout suppliersLayout = new javax.swing.GroupLayout(suppliers);
        suppliers.setLayout(suppliersLayout);
        suppliersLayout.setHorizontalGroup(
            suppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppliersLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(suppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(suppliersLayout.createSequentialGroup()
                        .addComponent(noSuppliesLb)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(suppliersLayout.createSequentialGroup()
                        .addGroup(suppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(suppliersDetailsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(suppliesTab))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(suppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(supplierGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(suppliesGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        suppliersLayout.setVerticalGroup(
            suppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(suppliersLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(noSuppliesLb)
                .addGap(32, 32, 32)
                .addGroup(suppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(supplierGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(suppliersDetailsPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(suppliersLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(suppliersLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(suppliesTab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(suppliersLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(suppliesGraphPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(86, Short.MAX_VALUE))
        );

        tabPanel.addTab("Suppliers", suppliers);

        file.setText("File");
        menuBar.add(file);

        view.setText("View");
        menuBar.add(view);

        options.setText("Options");
        menuBar.add(options);

        tools.setText("Tools");
        menuBar.add(tools);

        help.setText("Help");
        menuBar.add(help);

        about.setText("About");
        menuBar.add(about);

        setJMenuBar(menuBar);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanel)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPanel)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void moreProdDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreProdDetailsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moreProdDetailsBtnActionPerformed

    private void moreSalesDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moreSalesDetailsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moreSalesDetailsBtnActionPerformed

    private void morePurchDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_morePurchDetailsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_morePurchDetailsBtnActionPerformed

    private void searchTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchTxtActionPerformed

    private void exportFilesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportFilesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportFilesBtnActionPerformed

    private void expCusDetailsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expCusDetailsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expCusDetailsBtnActionPerformed

    private void searchCustomerTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustomerTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCustomerTxtActionPerformed

    private void exportCustSalesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCustSalesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportCustSalesBtnActionPerformed

    private void searchCustSalesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustSalesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCustSalesTxtActionPerformed

    private void exportCustProductsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportCustProductsBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportCustProductsBtnActionPerformed

    private void searchCustProductsTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchCustProductsTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchCustProductsTxtActionPerformed

    private void expSupDetailsBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_expSupDetailsBtn1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_expSupDetailsBtn1ActionPerformed

    private void searchSupplierTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSupplierTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchSupplierTxtActionPerformed

    private void searchSuppliesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSuppliesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchSuppliesTxtActionPerformed

    private void exportSuppliesFilesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportSuppliesFilesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportSuppliesFilesBtnActionPerformed

    private void exportPurchFilesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exportPurchFilesBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exportPurchFilesBtnActionPerformed

    private void searchPurchasesTxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPurchasesTxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPurchasesTxtActionPerformed

    private void importSupplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_importSupplierBtnActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_importSupplierBtnActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu about;
    private javax.swing.JScrollPane custProScrollPanel;
    private javax.swing.JPanel custProductsPanel;
    private javax.swing.JTable custProductsTable;
    private javax.swing.JPanel custSalesPanel;
    private javax.swing.JTable custSalesTable;
    private javax.swing.JTable custSalesTable1;
    private javax.swing.JPanel customerDetailsPanel;
    private javax.swing.JTable customerDetailsTable;
    private javax.swing.JTable customerDetailsTable1;
    private javax.swing.JScrollPane customerScrollPanel;
    private javax.swing.JTabbedPane customerTabPanel;
    private javax.swing.JPanel customers;
    private javax.swing.JPanel customersGraphsPanel;
    private javax.swing.JPanel customersGraphsPanel2;
    private javax.swing.JScrollPane customersSalesTabPane;
    private javax.swing.JTable dailyProductionTable;
    private javax.swing.JTable dailyPurchasesTable;
    private javax.swing.JTable dailySalesTable;
    private javax.swing.JPanel dialyProduction;
    private javax.swing.JPanel employeeListPanel;
    private javax.swing.JScrollPane employeeScrollPanel;
    private javax.swing.JPanel employees;
    private javax.swing.JTable employeesTable;
    private javax.swing.JButton expCusDetailsBtn;
    private javax.swing.JButton expSupDetailsBtn1;
    private javax.swing.JButton exportCustProductsBtn;
    private javax.swing.JButton exportCustSalesBtn;
    private javax.swing.JButton exportFilesBtn;
    private javax.swing.JButton exportPurchFilesBtn;
    private javax.swing.JButton exportSuppliesFilesBtn;
    private javax.swing.JMenu file;
    private javax.swing.JMenu help;
    private javax.swing.JPanel home;
    private javax.swing.JButton importCustProductsBtn;
    private javax.swing.JButton importCustSalesBtn;
    private javax.swing.JButton importCustomerBtn;
    private javax.swing.JButton importEmplBtn;
    private javax.swing.JButton importPurchaseBtn;
    private javax.swing.JButton importSupplierBtn;
    private javax.swing.JButton importSuppliesBtn;
    private javax.swing.JTabbedPane invenrotyTabPanel;
    private javax.swing.JPanel inventory;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JList<String> jList1;
    private javax.swing.JList<String> jList2;
    private javax.swing.JList<String> jList3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JPanel latestPurchases;
    private javax.swing.JPanel latestSales;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JButton moreProdDetailsBtn;
    private javax.swing.JButton morePurchDetailsBtn;
    private javax.swing.JButton moreSalesDetailsBtn;
    private javax.swing.JLabel noCustomerLb;
    private javax.swing.JLabel noEmployeesLb;
    private javax.swing.JLabel noSuppliesLb;
    private javax.swing.JMenu options;
    private javax.swing.JButton printBtn;
    private javax.swing.JButton printCusDetailsBtn;
    private javax.swing.JButton printCustProductsBtn;
    private javax.swing.JButton printCustSalesBtn;
    private javax.swing.JButton printPurchasesBtn;
    private javax.swing.JButton printSupDetailsBtn1;
    private javax.swing.JButton printSuppliesBtn;
    private javax.swing.JLabel productNameTextField;
    private javax.swing.JLabel productNameTextField1;
    private javax.swing.JLabel productNameTextField2;
    private javax.swing.JLabel productNameTextField3;
    private javax.swing.JLabel productNameTextField4;
    private javax.swing.JLabel productNameTextField5;
    private javax.swing.JPanel production;
    private javax.swing.JScrollPane productionScrollPane;
    private javax.swing.JPanel productsTab;
    private javax.swing.JScrollPane purchHistoryScrollPanel;
    private javax.swing.JTable purchHistoryTable;
    private javax.swing.JPanel purchasesHistoryPanel;
    private javax.swing.JScrollPane purchasesScrollPanel;
    private javax.swing.JButton refreshBtn;
    private javax.swing.JButton refreshCusDetailsBtn;
    private javax.swing.JButton refreshCustProductsBtn;
    private javax.swing.JButton refreshCustSalesTable;
    private javax.swing.JButton refreshPurchasesTableBtn;
    private javax.swing.JButton refreshSuplTableBtn;
    private javax.swing.JButton refreshSuppliesTableBtn;
    private javax.swing.JPanel sales;
    private javax.swing.JScrollPane salesScrollPanel;
    private javax.swing.JTextField searchCustProductsTxt;
    private javax.swing.JTextField searchCustSalesTxt;
    private javax.swing.JTextField searchCustomerTxt;
    private javax.swing.JTextField searchPurchasesTxt;
    private javax.swing.JTextField searchSupplierTxt;
    private javax.swing.JTextField searchSuppliesTxt;
    private javax.swing.JTextField searchTxt;
    private javax.swing.JPanel statistics;
    private javax.swing.JPanel supplierGraphPanel;
    private javax.swing.JScrollPane supplierScrollPanel;
    private javax.swing.JPanel suppliers;
    private javax.swing.JPanel suppliersDetailsPanel1;
    private javax.swing.JPanel suppliesGraphPanel;
    private javax.swing.JPanel suppliesITab;
    private javax.swing.JTabbedPane suppliesTab;
    private javax.swing.JScrollPane suppliesTabPane;
    private javax.swing.JPanel suppliesTabPanel;
    private javax.swing.JTabbedPane tabPanel;
    private javax.swing.JMenu tools;
    private javax.swing.JMenu view;
    // End of variables declaration//GEN-END:variables
}
