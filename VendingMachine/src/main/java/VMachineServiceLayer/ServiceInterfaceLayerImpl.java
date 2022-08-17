/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VMachineServiceLayer;

import DAO.ClassDAOPersistenceException;
import DAO.DAOAuditInterface;
import DAO.DaoInterface;
import static VMachineServiceLayer.ServiceInterfaceLayerImpl.CoindDenomination.DIMES;
import static VMachineServiceLayer.ServiceInterfaceLayerImpl.CoindDenomination.NICKELS;
import static VMachineServiceLayer.ServiceInterfaceLayerImpl.CoindDenomination.PENNIES;
import static VMachineServiceLayer.ServiceInterfaceLayerImpl.CoindDenomination.QUARTERS;
import static java.lang.String.valueOf;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.List;
import robertoandrade.Dto.Product;

/**
 *
 * @author elizabeth
 */
public class ServiceInterfaceLayerImpl implements ServiceInterface{
    
    private DaoInterface dao;
    private DAOAuditInterface auditDao;

   
    public ServiceInterfaceLayerImpl(DaoInterface dao , DAOAuditInterface auditDao) {
    this.dao = dao;
    this.auditDao = auditDao;

    }  
    
    
    
    
    @Override
    public List<Product> getAllProducts() throws ClassDAOPersistenceException {
        return dao.getAllProducts();

    }
    
     
    public String CalculateRestMoney(BigDecimal biggest ,BigDecimal smallest )throws ClassDAOPersistenceException{
        //MathContext mc = new MathContext(4);
        BigDecimal diff ;
        diff = biggest.subtract(smallest );
        diff.setScale(2, BigDecimal.ROUND_HALF_UP); 
        diff = diff.setScale(2, BigDecimal.ROUND_HALF_UP);
        return diff.toString();   
    }
    
    
    @Override
    public boolean isEnoughtMoney(BigDecimal moneyIN , int ItemCode)throws ClassDAOPersistenceException,InsufficientFundsException {
        boolean sold = false;
        List<Product> ProductList = getAllProducts();
        for (Product item : ProductList) {
        if (item.getID() == ItemCode ) {
            String proPrice = item.getPrice();
            //System.out.println(" price = "+proPrice);
            BigDecimal bigDecimal = new BigDecimal(Double.valueOf(proPrice));
            int result = moneyIN.compareTo(bigDecimal);         
            if(result == -1){
                
                throw new InsufficientFundsException("Insufficient Funds for this Product \n " + 
                      "you need $"+CalculateRestMoney(bigDecimal , moneyIN) + " more!!  \n"+
                        "Please take your money back and try a new transaction. Thank you \n");
            }else{
                sold =  true;
            }
            break;
        }
    }
    return sold;
    }
    
    public enum CoindDenomination {
    PENNIES, NICKELS, DIMES, QUARTERS
    }
    
    public String getCoins(CoindDenomination operator, int change) {
        String coinsInText;
        int pennies;
        int nickels;
        int dimes;
        int quarters;
        switch(operator) {
            case PENNIES:
                coinsInText = " | Pennies:"+valueOf(change);
                return coinsInText ; 
            case NICKELS:
                nickels = 1;
                pennies = change - 5 ;
                coinsInText = " | Pennies:"+valueOf(pennies)+" | Nickels:"+
                nickels;     
                return coinsInText;
            case DIMES:
                if(change >= 20){
                   dimes = 2;
                   pennies = change - 20 ;  
                   coinsInText = "| Pennies:"+valueOf(pennies)+" | dimes:"+
                   dimes;
                }else{
                    dimes = 1;
                    if(change - 10 >= 15 ){
                        nickels = 1;
                        pennies = change - 15 ; 
                        coinsInText = " | Pennies:"+valueOf(pennies)+" | nickels:"+
                   valueOf(nickels)+" | dimes:"+valueOf(dimes);
                    }else{
                        pennies = change - 10 ; 
                        coinsInText = " | Pennies:"+valueOf(pennies)+" | dimes:"+valueOf(dimes);
                    }
                    
                }
                
                return coinsInText;
            case QUARTERS:
                 quarters = change / 25 ;
                 if((quarters == 1 || quarters == 2 || quarters == 3) && change % 25 == 0){
                     coinsInText = " | quarters:"+valueOf(quarters);
                 }else if((quarters == 1 || quarters == 2 || quarters == 3) && change % 25 < 5){
                        pennies = change % 25 ; 
                        coinsInText = " | Pennies:"+valueOf(pennies)+" | quarters:"+valueOf(quarters);
                }else if((quarters == 1 || quarters == 2 || quarters == 3) && change % 25 > 10 && change % 25 < 25){
                    if(change % 25 >= 20){
                        dimes = 2;
                        pennies = (change % 25) - 15 ;
                         coinsInText = " | Pennies:"+valueOf(pennies)+" | dimes:"+valueOf(dimes)+" | quarters:"+valueOf(quarters);
              
                    }else if(change % 25 >= 15){
                        dimes = 1;
                        nickels = 1;
                        pennies = (change % 25) - 15 ;
                         coinsInText = " | Pennies:"+valueOf(pennies)+" | nickels:"+
                         valueOf(nickels)+" | dimes:"+valueOf(dimes)+" | quarters:"+valueOf(quarters);
                        
                    }else{
                        dimes = 1;
                        pennies = (change % 25) - 10 ;
                        coinsInText = " | Pennies:"+valueOf(pennies)+" | dimes:"+valueOf(dimes)
                        +" | quarters:"+valueOf(quarters);
                    }
                }else{ // > 5 && < 10
                   nickels = 1;
                   pennies = (change % 25) - 5 ;
                   coinsInText = " | Pennies:"+valueOf(pennies)+" | nickels:"+
                   valueOf(nickels) +" | quarters:"+valueOf(quarters);

                }
                return coinsInText;
            default:
                throw new UnsupportedOperationException();
        }
    }
    
    @Override
    public void modifyInventory(int ProductCode , BigDecimal moneyIN ,String changeBigD ) throws ClassDAOPersistenceException{
        List<Product> ProductList = getAllProducts();
        for (Product item : ProductList) {
            if (item.getID() == ProductCode ) {
                dao.ChangeInventory(ProductCode);
                auditDao.writeAuditEntry(item.getID() ,item.getName() , item.getPrice(),moneyIN, changeBigD );
                
                 // change inventory on DAO(int code)
                 // write audit entry
                break;
            }  
        }
        
        
        
    }
    
    @Override
    public String getchange(BigDecimal moneyIN , int ItemCode)throws ClassDAOPersistenceException{
        String Finalchange ;
        String proPrice = "";
        List<Product> ProductList = getAllProducts();
        for (Product item : ProductList) {
            if (item.getID() == ItemCode ) {
                proPrice = item.getPrice();
                break;
            }  
        }
        BigDecimal Price = new BigDecimal(Double.valueOf(proPrice));
        String change = CalculateRestMoney(moneyIN , Price);

        Double d = new Double(change);
        //BigDecimal b = BigDecimal.valueOf(d);
        BigDecimal b =new BigDecimal(change).round(new MathContext(4, RoundingMode.HALF_UP));
        b = b.setScale(2, RoundingMode.HALF_UP);
  
        //System.out.println("change = "+b.toString());

        String bills =  b.toString().substring(0, (b.toString().length() - 3));
        String coins =  b.toString().substring( (b.toString().length() - 2 ), b.toString().length());
        //System.out.println("coins = "+coins);
        int IntCoin = Integer.parseInt(coins); 
        //System.out.println("coinsINT = "+IntCoin);

        if(IntCoin == 0  ){
            Finalchange = "Dollar Coins:"+bills;
             return Finalchange;       
        }else if(IntCoin > 0 && IntCoin < 5){
            Finalchange = "Dollar Coins:"+bills+" "+getCoins(PENNIES , IntCoin);
               return Finalchange;     
        }else if(IntCoin >= 5 && IntCoin < 10){
            Finalchange = "Dollar Coins:"+bills+" "+getCoins(NICKELS , IntCoin);
               return Finalchange;
        }else if(IntCoin >= 10  && IntCoin < 25){
             Finalchange = "Dollar Coins:"+bills+" "+getCoins(DIMES , IntCoin);
               return Finalchange;    
        }else{
            Finalchange = "Dollar Coins:"+bills+" "+getCoins(QUARTERS , IntCoin);
            return Finalchange;      
         }
    }
    
    @Override
    public boolean validatemoney (String MoneyIn)throws DataValidationException{
        boolean pass = false;
        if(MoneyIn.length() > 7 || MoneyIn.length() < 4){
           throw new DataValidationException(" ERROR: Max Amount to Enter is $1000.00  and Min id $1.00  "); 
        }
        int dotIndex = MoneyIn.length() - 3;
        if(MoneyIn.charAt(dotIndex) != '.' ){
           throw new DataValidationException(" ERROR: INCORRECT FORMAT - Please enter money as $ XX.XX  ");  
        }else{
            String dollars = MoneyIn.substring(0, dotIndex);
            String cents = MoneyIn.substring(dotIndex+1, MoneyIn.length());
            try 
		{ 
			int MoneyPart = Integer.parseInt(dollars); 
                        if(MoneyPart <= 0 ){
                            throw new DataValidationException("*** ERROR: CAN NOT INPUT NEGATIVE NUMBERS  ***");  
                        }
                        Integer.parseInt(cents); 
                        pass = true;
		}  
		catch (NumberFormatException e)  
		{ 
                    throw new DataValidationException("Input ERROR , Please enter integers as values ");
		}    
        }
        if(pass){
           return true; 
        }else{
            return false;
        }
        }
            
}
