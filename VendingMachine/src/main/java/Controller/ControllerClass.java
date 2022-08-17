/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import DAO.ClassDAOPersistenceException;
import VMachineServiceLayer.DataValidationException;
import VMachineServiceLayer.InsufficientFundsException;
import VMachineServiceLayer.ServiceInterface;
import java.math.BigDecimal;
import java.math.RoundingMode;

import robertoandrade.UI.UIView;
import java.util.*;
import robertoandrade.Dto.Product;


/**
 *
 * @author Roberto
 */
public class ControllerClass {
    
    private UIView view ;
    private ServiceInterface service;
        
    public ControllerClass(ServiceInterface service, UIView view) {
    //this.dao = dao
    this.service = service;
    this.view = view;
    }
    
    private int getMenuSelection() {
    return view.printMenuAndGetSelection();
}
    
    private void exitMessage() {
    view.displayExitBanner();
}
    
    private void unknownCommand() {
    view.displayUnknownCommandBanner();
}
    
    
    
    private BigDecimal AddMoney()throws ClassDAOPersistenceException, DataValidationException{
     
        String moneyIn = "";
        String moneyok = "";
        boolean ValidInput = false;
        do{
            try{
             moneyIn  =  view.getUserMoney();
            ValidInput = service.validatemoney(moneyIn);               
            }catch(DataValidationException e ){
                ValidInput = false;
                view.displayErrorMessage(e.getMessage());
            }
        }while(!ValidInput);
        BigDecimal a = new BigDecimal(moneyIn);
        return a.setScale(2, RoundingMode.HALF_UP);
    }
    
    
    private void purchase()throws ClassDAOPersistenceException, DataValidationException, InsufficientFundsException{
        BigDecimal moneyIN = AddMoney();
        String changeBigD;
        List<Product> ProductList = service.getAllProducts();
        int ProductCode = view.getUserSelection(ProductList);
        try{
           if(service.isEnoughtMoney( moneyIN ,ProductCode )){
            view.displayTransactionBanner();
            changeBigD = service.getchange(moneyIN, ProductCode);
            view.displayChangeMessage(changeBigD);
            service.modifyInventory(ProductCode , moneyIN , changeBigD );
        }else{
            view.displayErrorMessage(" Transaction cancelled ");
        } 
        }catch(InsufficientFundsException e){
            view.displayErrorMessage(e.getMessage() );
            
        }
        
        
    }
    
    private void DisplayProducts()throws ClassDAOPersistenceException {
        List<Product> ProductList = service.getAllProducts();
        view.displayProductList(ProductList);
    }

public void run() throws DataValidationException, InsufficientFundsException {
    view.displayVendingMachineBanner();
    boolean keepGoing = true;
    int menuSelection = 0;
    try {
        DisplayProducts();
        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    purchase();
                    break;
                case 2:
                    keepGoing = false;
                    break;
                default:
                    unknownCommand();
            }

        }
        exitMessage();
    }catch (ClassDAOPersistenceException e){
        view.displayErrorMessage(e.getMessage());
    }
    
    
    
}
}
