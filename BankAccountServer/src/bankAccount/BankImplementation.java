/*
 * A implementacao do banco é o objeto cujos metodos
 * serao invocados remotamente pelos clientes
 */
package bankAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Allan
 */
public class BankImplementation extends UnicastRemoteObject implements BankInterface {
    
    private ArrayList<Account> accountsList;
    
   public BankImplementation() throws RemoteException {
   
       this.accountsList = new ArrayList<>();
   }
   
   public void createAccount(String cpf, String password, boolean isSavingsAccount) throws RemoteException {
       
       Random r = new Random();
       String accNoumber = String.format("%05d", r.nextInt(10000));
       Account account = new Account(cpf, password, accNoumber, isSavingsAccount);
       accountsList.add(account);
   }
}
