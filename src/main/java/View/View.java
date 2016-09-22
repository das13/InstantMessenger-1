package View;

import Controller.NewUserWindowsListener;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.WindowListener;
import java.util.ArrayList;

public class View {

    ManageViewElements instantMessenger = new ManageViewElements();

    public void addNewUserWindow () {
        instantMessenger.addNewUserWindow();
    }

    public String getMessage(){
        return instantMessenger.getMessage();
    }

    public void setSendButtonListener(ActionListener event){
        instantMessenger.setSendButtonListener(event);
    }

    public void setAddNewUserButtonListener(ActionListener event){
        instantMessenger.setAddNewUserButtonListener(event);
    }

    public void setWindowListener(WindowListener event){
        instantMessenger.addWindowListener(event);
    }

    public void notificateFromNewUser(String s) {
        JOptionPane.showMessageDialog(instantMessenger, s, "Уведомление!", JOptionPane.WARNING_MESSAGE);
    }

    public String getUserName(){
        return instantMessenger.getUserName();
    }

    public void setUsers(ArrayList users){
        instantMessenger.setUserList(users);
    }

    public void addMessages(String message) {
        instantMessenger.addMessage(message);
    }

    public void setMessageTextFieldKeyListener(KeyListener listener){
        instantMessenger.setMessageTextFieldKeyListener(listener);
    }

    public void setUserTextFieldKeyListener(KeyListener listener){
        instantMessenger.setUserTextFieldKeyListener(listener);
    }

    public void showMessageDialog(String s){
        instantMessenger.noConnectionJFrame();
    }

    public void closeJFrame(){
        instantMessenger.closeJFrame();
    }

    public void cleanField(){
        instantMessenger.cleanField();
    }

    public void setExitButtonListener(ActionListener listener,KeyListener keyListener){
        instantMessenger.setExitButtonListener(listener, keyListener);
    }

    public void addNewUserWindowsListener(NewUserWindowsListener listener){
        instantMessenger.addNewUserWindowsListener(listener);
    }
}
