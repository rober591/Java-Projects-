/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package DAO;

import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import robertoandrade.Dto.Product;

/**
 *
 * @author elizabeth
 */
public class DAOFileImplTest {
    DaoInterface testDao;

    
    public DAOFileImplTest() {
        testDao = new DAOFileImpl(); 
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testGetAllProducts() throws Exception {
        System.out.println("getAllProducts");
       List<Product> result = testDao.getAllProducts();
        assertEquals(20, result.size());
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetProduct() throws Exception {
        System.out.println("getProduct");
        int ID = 1;
        assertEquals(testDao.getProduct(ID).getName(),"Coke ",
       "Checking Product Name.");
                
        assertEquals(testDao.getProduct(ID).getPrice()," 2.50",
        "Checking Product price.");
        //fail("The test case is a prototype.");
    }

    @Test
    public void testChangeInventory() throws Exception {
        System.out.println("ChangeInventory");
       
        
        
        int ProductCode = 4;
        BigDecimal moneyIN = new BigDecimal("3.00");
        String changeBigD = "0.25";
        List<Product> result = testDao.getAllProducts();
        int initialQ = 0  , finalQ = 0;
        for (Product item : result) {
            if (item.getID() == ProductCode ) {
                initialQ = item.getQuanity();
                testDao.ChangeInventory(ProductCode);
                break;
            }  
        }
        List<Product> result2 = testDao.getAllProducts();
        for (Product item : result2) {
            if (item.getID() == ProductCode ) {
                finalQ = item.getQuanity();
                break;
            }  
        }
        
        System.out.println("modifyInventory");
       
        assertEquals(initialQ - 1, finalQ); 
    }
    
}
