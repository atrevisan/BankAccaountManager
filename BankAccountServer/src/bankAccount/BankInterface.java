/*
 * Interface com as operacoes realizadas pelo banco
 */
package bankAccount;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author Allan
 */
public interface BankInterface extends Remote {
    
    public String createAccount(String cpf, String password, boolean isSavingsAccount) throws RemoteException;
    public String checkBalance(String cpf, String password) throws RemoteException;
}
