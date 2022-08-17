/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 *
 * @author elizabeth
 */
public class DAOAuditFileImpl implements DAOAuditInterface{
        public static final String AUDIT_FILE = "audit.txt";

    
    public void writeAuditEntry(int id , String name , String price ,BigDecimal moenyIn ,String change  ) throws ClassDAOPersistenceException {
        PrintWriter out;
       String entry = "Sale"+ ":" + id + ":" + name + ":" + price + ":" + moenyIn.toString() + ":" + change ;
        try {
            out = new PrintWriter(new FileWriter(AUDIT_FILE, true));
        } catch (IOException e) {
            throw new ClassDAOPersistenceException("Could not persist audit information.", e);
        }
 
        LocalDateTime timestamp = LocalDateTime.now();
        out.println(timestamp.toString() + " : " + entry);
        out.flush();
    }
}
