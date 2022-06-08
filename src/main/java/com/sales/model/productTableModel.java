
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class productTableModel extends AbstractTableModel {
    private ArrayList <products> Products;
     private String[] columns ={"No.", "Item Nme","Item Price","Count", "Item Total"};

    public productTableModel(ArrayList<products> Products) {
        this.Products = Products;
    }

    public ArrayList<products> getProducts() {
        return Products;
    }


    @Override
    public int getRowCount() {
        return Products.size();
    } 

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public String getColumnName(int y) {
        return columns[y];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
    products products = Products.get(rowIndex);
    switch (columnIndex){
        case 0: return products.getBills().getNumber();
        case 1: return products.getModule();
        case 2: return products.getCost();
        case 3: return products.getCount();
        case 4: return products.getproductsTotal();
        default : return "";

    }
    }
    
}