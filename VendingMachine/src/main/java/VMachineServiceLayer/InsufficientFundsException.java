/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package VMachineServiceLayer;

/**
 *
 * @author elizabeth
 */
public class InsufficientFundsException extends Exception {
    
    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message,
            Throwable cause) {
        super(message, cause);
    }
    
}
