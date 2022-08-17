/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package VMachineServiceLayer;

import DAO.ClassDAOPersistenceException;
import java.math.BigDecimal;
import java.util.List;
import robertoandrade.Dto.Product;

/**
 *
 * @author elizabeth
 */
public interface ServiceInterface {
    
    
    List<Product> getAllProducts() throws
            ClassDAOPersistenceException;
    
    boolean validatemoney(String m)throws DataValidationException;
    
    public boolean isEnoughtMoney(BigDecimal moneyIN , int ItemCode)throws ClassDAOPersistenceException,InsufficientFundsException;
        
    
public String getchange(BigDecimal moneyIN , int ItemCode)throws ClassDAOPersistenceException ; 
public void modifyInventory(int ProductCode , BigDecimal moneyIN ,String changeBigD) throws ClassDAOPersistenceException;
    
}
