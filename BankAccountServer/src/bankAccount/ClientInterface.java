/*
 * Interface divulgando metodos remotos do cliente
 */
package bankAccount;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Allan
 */
public interface ClientInterface extends Remote {
    
    public void showNotification(String message) throws RemoteException;
}
