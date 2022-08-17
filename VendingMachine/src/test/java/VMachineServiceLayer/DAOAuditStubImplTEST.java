/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VMachineServiceLayer;

import DAO.ClassDAOPersistenceException;
import DAO.DAOAuditInterface;
import java.math.BigDecimal;

/**
 *
 * @author elizabeth
 */
public class DAOAuditStubImplTEST implements DAOAuditInterface{
    
    @Override
    public void writeAuditEntry(int id,String name , String price  ,BigDecimal moenyIn ,String change  ) throws ClassDAOPersistenceException {
        //do nothing . . .
    }
    
}
