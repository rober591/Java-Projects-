/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package DAO;

import java.math.BigDecimal;

/**
 *
 * @author elizabeth
 */
public interface DAOAuditInterface {
    
    
    
    
        //public void writeAuditEntry(String entry) throws ClassDAOPersistenceException;
public void writeAuditEntry(int id , String name , String price ,BigDecimal moenyIn ,String change  ) throws ClassDAOPersistenceException ;

    
    
    
}
