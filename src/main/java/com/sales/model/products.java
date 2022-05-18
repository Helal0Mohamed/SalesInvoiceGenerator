
package com.sales.model;


public class products {
    
    private int number;
    private int product;
    private String module;
    private double cost;
    private int count;
    private bills bills;

    public products() {
    }

    public products(String module, double cost, int count, bills bills) {
        this.bills = bills;
        this.module = module;
        this.cost = cost;
        this.count = count;
    }

    public products(int number, String module, double cost, int count, bills bills) {
        this.module = module;
        this.cost = cost;
        this.count = count;
        this.bills = bills;
    }
    public double getproductsTotal(){
        return cost*count;
    }
    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getProduct() {
        return product;
    }

    public void setProduct(int product) {
        this.product = product;
    }

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public bills getBills() {
        return bills;
    }

   
    @Override
    public String toString() {
        return "products{" + "number=" + bills.getNumber() + ", product=" + product + ", module=" + module + ", cost=" + cost + ", count=" + count + '}';
    }
    
    public String returnAsCSV(){
       return bills.getNumber()+","+module+","+cost+","+count;
   } 
}
