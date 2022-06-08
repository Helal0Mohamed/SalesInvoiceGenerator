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
public class productTableWindow1 extends JDialog{
    private JTextField producrtNameField;
    private JTextField productCountField;
    private JTextField productPriceField;
    private JLabel productNameLbl;
    private JLabel productCountLbl;
    private JLabel productPriceLbl;
    private JButton okBtn;
    private JButton cancelBtn;
    
    public productTableWindow1(salesFrame frame) {
        producrtNameField = new JTextField(20);
        productNameLbl = new JLabel("Item Name");
        
        productCountField = new JTextField(20);
        productCountLbl = new JLabel("Item Count");
        
        productPriceField = new JTextField(20);
        productPriceLbl = new JLabel("Item Price");
        
        okBtn = new JButton("OK");
        cancelBtn = new JButton("Cancel");
        
        okBtn.setActionCommand("createproductOK");
        cancelBtn.setActionCommand("createproductCancel");
        
        okBtn.addActionListener(frame.getController());
        cancelBtn.addActionListener(frame.getController());
        setLayout(new GridLayout(4, 2));
        
        add(productNameLbl);
        add(producrtNameField);
        add(productCountLbl);
        add(productCountField);
        add(productPriceLbl);
        add(productPriceField);
        add(okBtn);
        add(cancelBtn);
        
        pack();
    }

    public JTextField getProducrtNameField() {
        return producrtNameField;
    }

    public JTextField getProductCountField() {
        return productCountField;
    }

    public JTextField getProductPriceField() {
        return productPriceField;
    }
}
