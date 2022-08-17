/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package robertoandrade.vendingmachine;

import DAO.DaoInterface;
import DAO.DAOFileImpl;
import DAO.DAOAuditInterface;
import DAO.DAOAuditFileImpl;

import robertoandrade.UI.UIView;
import robertoandrade.UI.UserIOImplementation;
import robertoandrade.UI.UserInterface;
import Controller.ControllerClass;
import VMachineServiceLayer.DataValidationException;
import VMachineServiceLayer.InsufficientFundsException;
import VMachineServiceLayer.ServiceInterface;
import VMachineServiceLayer.ServiceInterfaceLayerImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



/**
 *
 * @author elizabeth
 */
public class AppVendingMachine {

    public static void main(String[] args) throws DataValidationException, InsufficientFundsException {
        
    /*    
    UserInterface myIo = new UserIOImplementation();
        
    UIView myView = new UIView(myIo);
        
    DaoInterface myDao = new DAOFileImpl();
            
    DAOAuditInterface myAuditDao = new DAOAuditFileImpl();

    ServiceInterface myService = new ServiceInterfaceLayerImpl(myDao , myAuditDao);

    ControllerClass controller =   new ControllerClass(myService, myView);

    controller.run();
        
       
*/
      ApplicationContext ctx = 
           new ClassPathXmlApplicationContext("applicationContext.xml");
        ControllerClass controller = 
           ctx.getBean("controller", ControllerClass.class);
        controller.run();

    
    }
}
