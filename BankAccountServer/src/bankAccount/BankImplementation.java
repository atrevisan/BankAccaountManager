/*
 * A implementacao do banco é o objeto cujos metodos
 * serao invocados remotamente pelos clientes
 */
package bankAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
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
               return "wrong password";
       }else
           return "not found";
   }
   
   public String makeDeposit(String accountNumber, String depositAmount) throws RemoteException {
   
       Account acc = (Account) accountMap1.get(accountNumber);
       if (acc != null) {
           
           acc.makeDeposit(Float.parseFloat(depositAmount));
           return "Success!!";
           
       }else
           return "not found";
   }
   
   public String makeWithdraw(String cpf, String password, String withdrawingAmount) throws RemoteException {
   
       Account acc = (Account) accountMap2.get(cpf);
       if (acc != null) {
           
           if (acc.getPassword().equals(password)) {
           
               acc.makeWithdraw(Float.parseFloat(withdrawingAmount));
               return "Success!!";
           }
           else
               return "wrong password";
       }else
           return "not found";
   }
}
