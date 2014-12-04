/*
 * A implementacao do banco é o objeto cujos metodos
 * serao invocados remotamente pelos clientes
 */
package bankAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Random;

/**
 *
 * @author Allan
 */
public class BankImplementation extends UnicastRemoteObject implements BankInterface {
    
    private HashMap<String, Account> accountMap1;
    private HashMap<String, Account> accountMap2;
    
   public BankImplementation() throws RemoteException {
   
       this.accountMap1 = new HashMap<>();
       this.accountMap2 = new HashMap<>();
   }
   
   public String createAccount(String cpf, String password, boolean isSavingsAccount) throws RemoteException {
       
       if (accountMap2.containsKey(cpf))
           return "customer already registered";
       
       Random r = new Random();
       String accNumber = String.format("%05d", r.nextInt(10000));
       while (accountMap1.containsKey(accNumber)) 
           accNumber = String.format("%05d", r.nextInt(10000));
       
       Account account = new Account(cpf, password, accNumber, isSavingsAccount);
       
       accountMap1.put(accNumber, account);
       accountMap2.put(cpf, account);
       
       return "Success!!";
   }
   
   public String checkBalance(String cpf, String password) throws RemoteException {
       
       Account acc = (Account) accountMap2.get(cpf);
       if (acc != null) {
           
           if (acc.getPassword().equals(password))
               return String.valueOf(acc.getBalance());
            else
               return "wrog password";
       }else
           return "not found";
   }
}
