
package com.sales.model;

import java.util.ArrayList;


public class bills {
    private int number;
    private String date;
    private String custmor;
    private ArrayList<products> products;
    private double billsTotal;

    public bills() {
    }
public double getbillsTotal(){
    double totalbills = 0.0;
    for (products products : getProducts()){
        totalbills =totalbills+ products.getproductsTotal();
    }
    return totalbills;   
}

    public bills(int number, String date, String custmor) {
        this.number = number;
        this.date = date;
        this.custmor = custmor;
    }

    public ArrayList<products> getProducts() {
        if (products == null){
            products = new ArrayList<>();
        }
        return products;
    }

    public String getCustmor() {
        return custmor;
    }

    public void setCustmor(String custmor) {
        this.custmor = custmor;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "bills{" + "number=" + number + ", date=" + date + ", custmor=" + custmor + '}';
    }
   public String returnAsCSV(){
       return number+","+date+","+custmor;
   } 
}
