/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package robertoandrade.UI;

import java.util.List;
import robertoandrade.Dto.Product;

/**
 *
 * @author elizabeth
 */
public class UIView {
     private UserInterface io;
     
     
      public UIView(UserInterface io) {
    this.io = io;
}
      
      
      public void displayVendingMachineBanner () {
    io.print("==================================================");
    io.print("============ Virtual Vending Machine  ============");
    
}
      
      
      public int printMenuAndGetSelection() {
        io.print("---------------------------------------------------");
        io.print("------MAIN MENU--------");
        io.print("1. Add  Money");
        io.print("2. Exit");
        io.print("---------------------------------------------------");
        return io.readInt("-> Please select input a number from the above choices.", 1, 2);
    }
      
    public void displayProductList(List<Product> ProductList){
        for (Product currentProduct : ProductList) {
            if(currentProduct.getQuanity() > 0){
                String productInfo = String.format(" (%s) %s ...... $%s ",
              currentProduct.getID() ,
              currentProduct.getName() ,
              currentProduct.getPrice() );
              io.print(productInfo);
            }
        
        }
    //io.readString("Please hit enter to continue.");
    }
      
      public void displayExitBanner() {
    io.print("============  Good Bye!!! ============  ");
}
      
      public void displayUnknownCommandBanner() {
    io.print("Unknown Command!!!");
}
      
      public void displayTransactionBanner(){
          io.print("---------------------------------------------------");
          io.print("================ Transaction Complete ==============");
      }
      
      
      public int getUserSelection(List<Product> productList) {
          //return io.readInt("From the List Above enter the code# of the Product you want to purchase");
          
          io.print("=====================================================");
          int IdCode  = 0;
        boolean IsCodeInList = false;
          while(!IsCodeInList){
           IdCode = io.readInt("From the List Above enter the code# of the Product you want to purchase");
           for (Product currentProduct : productList) {
              if(currentProduct.getID() == IdCode && currentProduct.getQuanity() > 0){
                  IsCodeInList = true;
                  break;
              }
          }
          if(IsCodeInList == true){
              break;
          }else{
              io.print("****** That code is not in the list !!! ******");
              io.print("=====================================================");

          }
        }
        return IdCode;
          
      }
      
      
      
      
      
      public String getUserMoney() { 
    io.print( "======================================================");
    return io.readString("Please enter the amount of money that you want to put into the Machine  \n  "
            + "USE THE FOLLOWING FORMAT: $ XX.XX          (MAX = $100.00) ");
    
}
      
      public void displayErrorMessage(String errorMsg){
    io.print("=== ERROR ===");
    io.print(errorMsg);      }
      
      public void displayChangeMessage(String change){
    io.print("===== TAKE YOUR CHANGE ===== \n");
    io.print(change);      }
      
}
