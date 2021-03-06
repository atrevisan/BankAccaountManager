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
    private HashMap<Account, ClientInterface> clientsToNotify;
    
   public BankImplementation() throws RemoteException {
   
       this.accountMap1 = new HashMap<>();
       this.accountMap2 = new HashMap<>();
       this.clientsToNotify = new HashMap<>();
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
           if (acc.receiveNotifications())
               clientsToNotify.get(acc).showNotification("received deposit: " + depositAmount);
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
   
   public String transferMoney(String originCPF, String originPassword, String beneficiaryAccountNumber, String transferAmount) throws RemoteException {
   
       Account accOrigin = (Account) accountMap2.get(originCPF);
       Account accDestination = (Account) accountMap1.get(beneficiaryAccountNumber);
       if (accOrigin != null && accDestination != null) {
           
           if (accOrigin.getPassword().equals(originPassword)) {
           
               if (accDestination.receiveNotifications())
                    clientsToNotify.get(accDestination).showNotification("transfer received: " + transferAmount);
               
               accOrigin.makeWithdraw(Float.parseFloat(transferAmount));
               accDestination.makeDeposit(Float.parseFloat(transferAmount));
               return "Success!!";
           }
           else
               return "wrong password";
       }else
           return "not found";
   }
   
   public String registerInterest(ClientInterface cli, String accNumber) throws RemoteException {
   
       Account acc = (Account) accountMap1.get(accNumber);
       if (acc != null) {
           
           acc.registerInterestInReceiveNotifications();
           clientsToNotify.put(acc, cli);
           return "Success!!";
           
       }else
           return "not found";
   }
}
