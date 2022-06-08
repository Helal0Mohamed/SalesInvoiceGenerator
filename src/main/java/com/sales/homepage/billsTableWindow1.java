/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sales.homepage;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author DELL
 */
public class billsTableWindow1 extends JDialog {
    private JTextField custNameField;
    private JTextField billsDateField;
    private JLabel custNameLbl;
    private JLabel billsDateLbl;
    private JButton okBtn;
    private JButton cancelBtn;

    public billsTableWindow1(salesFrame frame) {
        custNameLbl = new JLabel("Customer Name:");
        custNameField = new JTextField(20);
        billsDateLbl = new JLabel("Bills Date:");
        billsDateField = new JTextField(20);
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("createBillsOK");
        cancelBtn.setActionCommand("createBillsCancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        
        add(billsDateLbl);
        add(billsDateField);
        add(custNameLbl);
        add(custNameField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
        
    }

    public JTextField getCustNameField() {
        return custNameField;
    }

    public JTextField getBillsDateField() {
        return billsDateField;
    }
    
}
