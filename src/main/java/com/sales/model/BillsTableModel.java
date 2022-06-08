
package com.sales.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class BillsTableModel extends AbstractTableModel{
    private ArrayList<bills> Bills;
    private String[] colums ={"No.", "Date","Customer","Total"};

    public BillsTableModel(ArrayList<bills> bills) {
        this.Bills = bills;
    }

    @Override
    public int getRowCount() {
        return Bills.size();
    }

    @Override
    public int getColumnCount() {
        return colums.length;
    }
    @Override
   public String getColumnName(int column){
        return colums[column];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        bills bills = Bills.get(rowIndex);
        switch (columnIndex){
            case 0: return bills.getNumber();
            case 1: return bills.getDate();
            case 2: return bills.getCustmor();
            case 3: return bills.getbillsTotal();
            default : return "";
        }
    }
}