/*
 * Servidor responsavel por iniciar o servico de nomes
 */
package bankAccount;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Allan
 */
public class Server {
    
    public static void main(String[] args) {
    
        try {
            
            BankInterface bank = new BankImplementation();
            
            Registry nameServiceRef = LocateRegistry.createRegistry(1088);
            nameServiceRef.rebind("Bank", bank);
            System.out.println("Server is ready!!");
            
        } catch (RemoteException ex) {
            Logger.getLogger(Server.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
