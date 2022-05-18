
package com.sales.control;

import com.sales.homepage.billsTableWindow1;
import com.sales.homepage.productTableWindow1;
import com.sales.homepage.salesFrame;
import com.sales.model.BillsTableModel;
import com.sales.model.productTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import com.sales.model.bills;
import com.sales.model.products;
import java.io.FileWriter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Controller implements ActionListener, ListSelectionListener {
    private salesFrame frame;
    private billsTableWindow1 billsTableWindow1 ; 
    private productTableWindow1 productTableWindow1;

    public Controller(salesFrame frame){
        this.frame = frame;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String actioncommand = e.getActionCommand();
        System.out.println("Action "+actioncommand);
        switch(actioncommand){
            case "Load File":
                LoadFile();
            break;
            
            case "Save File":
                SaveFile();
            break;
            
            case "Create New Invoice":
                CreateNewInvoice();
            break;
            
            case "Delete Invoice":
                DeleteInvoice();
            break;
            
            case "Save":
                Save();
            break;
            
            case "Cancel":
                Cancel();
            break;
            case "createBillsCancel":
                createBillsCancel();
                break;
            case "createBillsOK":
                createBillsOK();
                break;
                
                case "createproductOK":
                createproductOK();
                break;
                
                case "createproductCancel":
                createproductCancel();
                break;
          
    } 
}
    
@Override
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = frame.getBillsTable().getSelectedRow();
        if (selectedIndex != -1){
        System.out.println("you have selected : "+selectedIndex);
        bills currentbill = frame.getBills().get(selectedIndex);
        frame.getInvoiceNumber().setText(""+currentbill.getNumber());
        frame.getInvoiceDate().setText(currentbill.getDate());
        frame.getCustomerName().setText(currentbill.getCustmor());
        frame.getInvoiceTotal().setText(""+currentbill.getbillsTotal());
        productTableModel productsTableModel = new productTableModel(currentbill.getProducts());
        frame.getProductTable().setModel(productsTableModel);
        productsTableModel.fireTableDataChanged();
    }
    }
    private void LoadFile() {
      JFileChooser fc=new JFileChooser();
      try {
     int outcome= fc.showOpenDialog(frame);
     if(outcome==JFileChooser.APPROVE_OPTION){
         File billsFile = fc.getSelectedFile();
         Path billsPath= Paths.get(billsFile.getAbsolutePath());
        List<String> headerLines = Files.readAllLines(billsPath);
         System.out.println("invoices have brrn read");
         ArrayList<bills> invoiceArray = new ArrayList<> ();
         for (String headerLine: headerLines){
           String[] headerParts =  headerLine.split(",");
           int invoiceNum= Integer.parseInt( headerParts[0]);
           String invoiceDate = headerParts[1];
           String customerName = headerParts[2];
           bills invoice=new bills(invoiceNum, invoiceDate, customerName);
           invoiceArray.add(invoice); 
         }
         
         
         System.out.println("checkpoint");
         outcome = fc.showOpenDialog(frame);
         if(outcome == JFileChooser.APPROVE_OPTION){
             File lineFile = fc.getSelectedFile();
             Path linePath = Paths.get(lineFile.getAbsolutePath());
             List<String> lineLines = Files.readAllLines(linePath);
                      System.out.println("lines have brrn read");
                      for(String lineLine : lineLines){
                          String lineParts[] =lineLine.split(",");
                          int invoiceNum = Integer.parseInt(lineParts[0]);
                          String itemName = lineParts[1];
                          double itemPrice = Double.parseDouble(lineParts[2]);
                          int count = Integer.parseInt(lineParts[3]);
                          bills inv = null;
                          for(bills invoice : invoiceArray){
                              if(invoice.getNumber() == invoiceNum){
                                  inv = invoice ;
                                  break;
                              }
                          }
                          products line=new products(invoiceNum, itemName, itemPrice, count, inv);
                          inv.getProducts().add(line);
                      }
             System.out.println("check point");
         }
         frame.setBills(invoiceArray);
         BillsTableModel billsTableModel= new BillsTableModel(invoiceArray);
         frame.setBillsTableModel(billsTableModel);
         frame.getBillsTable().setModel(billsTableModel);
         frame.getBillsTableModel().fireTableDataChanged();
     }
      } catch (IOException ex){
          ex.printStackTrace();
      }
    }

    private void SaveFile() {
        ArrayList<bills> Bills =frame.getBills();
        String headers="";
        String lines ="";
        for (bills bills : Bills){
            String invCSV = bills.returnAsCSV();
            headers = headers + invCSV;
            headers += "\n";
            
            for(products products: bills.getProducts()){
                String lineCSV = products.returnAsCSV();
                lines += lineCSV;
                lines +="\n";
            }
    }
        System.out.println("checked line");
        try{
        JFileChooser fc = new JFileChooser();
        int result =    fc.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION){
            File headerFile = fc.getSelectedFile();
            FileWriter hfw = new FileWriter(headerFile);
            hfw.write(headers);
            hfw.flush();
            hfw.close();
            
            result = fc.showSaveDialog(frame);
            if(result == JFileChooser.APPROVE_OPTION){
                File lineFile = fc.getSelectedFile();
                FileWriter lfw = new FileWriter(lineFile);
            lfw.write(lines);
            lfw.flush();
            lfw.close();
                
                
            }
        }
        } catch (Exception ex){
            
        }
    }
    private void CreateNewInvoice() {
    billsTableWindow1 = new billsTableWindow1(frame);
        billsTableWindow1.setVisible(true);
    
    }

    private void DeleteInvoice() {
      int selectedRow =  frame.getBillsTable().getSelectedRow();
     if(selectedRow != -1){
         frame.getBills().remove(selectedRow);
         frame.getBillsTableModel().fireTableDataChanged();
         
     }
    }

    private void Save() {
        productTableWindow1 = new productTableWindow1(frame);
        productTableWindow1.setVisible(true);
    
    }

    private void Cancel() {
        int selectedRow =  frame.getProductTable().getSelectedRow();
        
     if(selectedRow != -1){
         productTableModel productsTableModel = (productTableModel) frame.getProductTable().getModel();
         productsTableModel.getProducts().remove(selectedRow);
         productsTableModel.fireTableDataChanged();
         frame.getBillsTableModel().fireTableDataChanged();
     }
    
    }

    private void createBillsCancel() {
    billsTableWindow1.setVisible(false);
    billsTableWindow1.dispose();
    billsTableWindow1 = null;
    }

    private void createproductOK() {
        String item = productTableWindow1.getProducrtNameField().getText();
        String countStr = productTableWindow1.getProductCountField().getText();
        String priceStr = productTableWindow1.getProductPriceField().getText();
        int count =Integer.parseInt(countStr);
        double price = Double.parseDouble(priceStr);
        int selectedRow = frame.getBillsTable().getSelectedRow();
        if(selectedRow != -1){
        bills bills =frame.getBills().get( selectedRow );
        products products =new products(item,price, count,bills); 
        bills.getProducts().add(products);
        productTableModel productTableModel =(productTableModel) frame.getProductTable().getModel();
       // productTableModel.getProducts().add(products);
        productTableModel.fireTableDataChanged();
        frame.getBillsTableModel().fireTableDataChanged();
        }
        
        
        
    productTableWindow1.setVisible(false);
        productTableWindow1.dispose();
        productTableWindow1 = null;
    }
    
    private void createBillsOK() {
        String date = billsTableWindow1.getBillsDateField().getText();
        String customer =billsTableWindow1.getCustNameField().getText();
        int num = frame.getNextBillNum();
        bills bills  = new bills(num, date, customer);
        frame.getBills().add(bills);
        frame.getBillsTableModel().fireTableDataChanged();
        billsTableWindow1.setVisible(false);
        billsTableWindow1.dispose();
        billsTableWindow1 = null;
    }

    private void createproductCancel() {
        productTableWindow1.setVisible(false);
        productTableWindow1.dispose();
        productTableWindow1 = null;
    
    }

    
}
