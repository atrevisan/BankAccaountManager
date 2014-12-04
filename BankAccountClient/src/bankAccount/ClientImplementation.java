/*
 * Implementacao do cliente, responsavel por receber chamadas remotas
 * e mostrar notificacoes
 */
package bankAccount;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import javax.swing.JOptionPane;

/**
 *
 * @author Allan
 */
public class ClientImplementation extends UnicastRemoteObject implements ClientInterface {
    
    private ClientGUI gui;
    
    public ClientImplementation (ClientGUI gui) throws RemoteException {
    
        this.gui = gui;
    }
    
    public void showNotification(String message) throws RemoteException {
    
        JOptionPane.showMessageDialog(gui, message);
    }
}
