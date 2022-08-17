/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package VMachineServiceLayer;

import DAO.DAOAuditInterface;
import DAO.DAOFileImpl;
import DAO.DaoInterface;
import static VMachineServiceLayer.ServiceInterfaceLayerImpl.CoindDenomination.QUARTERS;
import java.math.BigDecimal;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import robertoandrade.Dto.Product;

/**
 *
 * @author elizabeth
 */
public class ServiceInterfaceLayerImplTest {
     private ServiceInterface service;

    
    public ServiceInterfaceLayerImplTest() {
         /*
        DaoInterface dao = new DAOFileImpl();
        DAOAuditInterface audit = new DAOAuditStubImplTEST();
        service = new ServiceInterfaceLayerImpl(dao ,audit );
        */
          ApplicationContext ctx = 
        new ClassPathXmlApplicationContext("applicationContext.xml");
    service = 
        ctx.getBean("serviceLayer", ServiceInterface.class);
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
        List<Product> result = service.getAllProducts();
        assertEquals(20, result.size());
    }

    @Test
    public void testCalculateRestMoney() throws Exception {
        System.out.println("CalculateRestMoney");
        BigDecimal biggest = new BigDecimal("5.00");
        BigDecimal smallest = new BigDecimal("3.00");
        ServiceInterfaceLayerImpl Service2 = null;
        DaoInterface dao = new DAOFileImpl();
        DAOAuditInterface audit = new DAOAuditStubImplTEST();
        Service2 = new ServiceInterfaceLayerImpl(dao ,audit );
        String expResult = "2.00";
        String result = Service2.CalculateRestMoney(biggest, smallest);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testIsEnoughtMoney() throws Exception {
        System.out.println("isEnoughtMoney");
        BigDecimal moneyIN = new BigDecimal("5.00");
        int ItemCode = 1; // coke $2.50
        //ServiceInterfaceLayerImpl instance = null;
        boolean expResult = true;
        boolean result = service.isEnoughtMoney(moneyIN, ItemCode);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @Test
    public void testGetCoins() {
        System.out.println("getCoins");
        ServiceInterfaceLayerImpl.CoindDenomination operator = null;
        int change = 0;
        ServiceInterfaceLayerImpl Service2 = null;
        DaoInterface dao = new DAOFileImpl();
        DAOAuditInterface audit = new DAOAuditStubImplTEST();
        Service2 = new ServiceInterfaceLayerImpl(dao ,audit );
        String expResult = "Dollar Coins:1  | Pennies:5 | nickels:1 | quarters:3";
        String result = "Dollar Coins:1 "+Service2.getCoins(QUARTERS,85);
        assertEquals(expResult, result);
    }

    @Test
    public void testModifyInventory() throws Exception {
        int ProductCode = 2;
        BigDecimal moneyIN = new BigDecimal("2.60");
        String changeBigD = "0.10";
        List<Product> result = service.getAllProducts();
        int initialQ = 0  , finalQ = 0;
        for (Product item : result) {
            if (item.getID() == ProductCode ) {
                initialQ = item.getQuanity();
                service.modifyInventory(ProductCode, moneyIN, changeBigD);
                break;
            }  
        }
        List<Product> result2 = service.getAllProducts();
        for (Product item : result2) {
            if (item.getID() == ProductCode ) {
                finalQ = item.getQuanity();
                break;
            }  
        }
        
        System.out.println("modifyInventory");
       
        assertEquals(initialQ - 1, finalQ); 
    }

    @Test
    public void testGetchange() throws Exception {
        System.out.println("getchange");
        BigDecimal moneyIN = new BigDecimal("5.00");
        int ItemCode = 1; // coke $2.50
        ServiceInterfaceLayerImpl instance = null;
        String expResult = "Dollar Coins:2  | quarters:2";
        String result = service.getchange(moneyIN, ItemCode);
        assertEquals(expResult, result);        
    }

    @Test
    public void testValidatemoney() throws Exception {
        System.out.println("validatemoney");
        String MoneyIn = "3.60";
        boolean expResult = true;
        boolean result = service.validatemoney(MoneyIn);
        assertEquals(expResult, result);
    }
    
}
