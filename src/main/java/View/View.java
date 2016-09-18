package View;

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

    public void notificateFromNewUser() {
        instantMessenger.notificateFromNewUser();
    }

    public String getUserName(){
        return instantMessenger.getUserName();
    }

    public void setUsers(ArrayList users){
        instantMessenger.setUserList(users);
    }

    public void setMessages(ArrayList list) {
        instantMessenger.setMessageList(list);
    }

    public void setMessageTextFieldKeyListener(KeyListener listener){
        instantMessenger.setMessageTextFieldKeyListener(listener);
    }
    public void setUserTextFieldKeyListener(KeyListener listener){
        instantMessenger.setUserTextFieldKeyListener(listener);
    }

    public void closeJFrame(){
        instantMessenger.closeJFrame();
    }

    public void cleanField(){
        instantMessenger.cleanField();
    }
}
