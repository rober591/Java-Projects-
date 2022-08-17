/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.Dto;

import java.util.Objects;

/**
 *
 * @author elizabeth
 */
public class Product {
    int ID;
    private String name;
    private int quanity;
    private String price;
    
    Product(int ID ,String name , int quanity ,String price){
        this.ID = ID;
        this.name = name;
        this.quanity = quanity;
        this.price = price;
    }
    
   public  Product(){
       this.ID = 0;
        this.name = "empty";
        this.quanity = 0;
        this.price = "0.00";
    }
   
 
    public int getID(){
        return this.ID;
    }
   
   public String getName(){
        return this.name;
    }
    
    public int getQuanity(){
        return this.quanity;
    }
    
    public String getPrice(){
        return this.price;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setQuantity(int quantity){
        this.quanity = quantity;
    }
    
    public void setPrice(String price){
        this.price = price;
    }
    
    public void setID(int ID){
                this.ID = ID;
    }
    
    @Override
public int hashCode() {
    int hash = 7;
    hash = 89 * hash + Objects.hashCode(this.ID);
    hash = 89 * hash + Objects.hashCode(this.name);
    hash = 89 * hash + Objects.hashCode(this.price);
    hash = 89 * hash + Objects.hashCode(this.quanity);
    
    return hash;
}

@Override
public boolean equals(Object obj) {
    if (this == obj) {
        return true;
    }
    if (obj == null) {
        return false;
    }
    if (getClass() != obj.getClass()) {
        return false;
    }
    final Product other = (Product) obj;
    if (!Objects.equals(this.name, other.name)) {
        return false;
    }
    if (!Objects.equals(this.price, other.price)) {
        return false;
    }
    if (!Objects.equals(this.ID, other.ID)) {
        return false;
    }
    if (!Objects.equals(this.quanity, other.quanity)) {
        return false;
    }
    return true;
}

@Override
public String toString() {
    return "Product{" + "name=" + name + ", price=" + price + ", id=" + ID + ", quantity=" + quanity + '}';
}
    
    
}
