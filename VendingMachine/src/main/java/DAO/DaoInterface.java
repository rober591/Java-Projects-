/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import java.util.List;
import robertoandrade.Dto.Product;


/**
 *
 * @author elizabeth
 */
public interface DaoInterface {
    
    List<Product> getAllProducts()
     throws ClassDAOPersistenceException;
    
   void ChangeInventory(int index )throws ClassDAOPersistenceException ;
   public Product getProduct(int ID) throws ClassDAOPersistenceException;
    
}
