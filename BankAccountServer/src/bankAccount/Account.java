/*
 * Classe responsavel por gerenciar
 * as operacoes de uma dada conta bancaria
 */
package bankAccount;


/**
 *
 * @author Allan
 */
public class Account {
    
    private final String cpf;
    private final String clientPassword;
    private final String accountNumber;
    private float balance;
    private boolean receiveNotifications;
    private final boolean isSavingsAccount;
    
    public Account(String cpf, String clientPassword, String accountNumber, boolean isSavingsAccount) {
    
        this.cpf = cpf;
        this.clientPassword = clientPassword;
        this.isSavingsAccount = isSavingsAccount;
        this.accountNumber = accountNumber;
        this.balance = 0.0f;
        this.receiveNotifications = false;
         
        System.out.println("Account created");
        System.out.println("cpf: " + cpf);
        System.out.println("Account number: " + accountNumber);
        if (isSavingsAccount)
            System.out.println("Account type: savings account");
        else
            System.out.println("Account type: checking account");
        System.out.println("---------------------------------------------");
    }
    
    public String getCPF() {return cpf;}
    public String getPassword() {return clientPassword;}
    public boolean receiveNotifications() {return receiveNotifications;}
    
    public synchronized float getBalance() {
        
        System.out.println("Account number: " + accountNumber + " balance: " + this.balance);
        System.out.println("---------------------------------------------");
        return balance;
    }
    
    public void registerInterestInReceiveNotifications() {
    
        this.receiveNotifications = true;
        System.out.println("Account number: " + accountNumber + " receive notifications");
        System.out.println("---------------------------------------------");
    }
    
    public synchronized void makeDeposit(float amount) {
    
        this.balance += amount;
        System.out.println("Account number: " + this.accountNumber + " deposit: " + amount);
        System.out.println("---------------------------------------------");
    }
    
    public synchronized void makeWithdraw(float amount) {
    
        this.balance -= amount;
        System.out.println("Account number: " + this.accountNumber + " withdraw: " + amount);
        System.out.println("---------------------------------------------");
    }
}
