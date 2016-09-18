package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyListener;
import java.io.IOException;

public class Controller {

    private static final Logger LOG = Logger.getLogger(Controller.class);

    Model model;
    View view;

    public Controller(View view, Model model)  {
        this.view = view;
        this.model = model;

        view.addNewUserWindow();

        addAddNewUserButtonListener();
        addSendButtonListener();
        addWindowsListener();
        addSendMessageKeyListener();
        addSendUserKeyListener();
        addExitButtonListener();

        try {
            LOG.info("Try to connect with server.");
            model.connectToServer();
            LOG.info("Successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        LOG.info("Controller created.");
    }

    public void addAddNewUserButtonListener(){
        ActionListener addNewUserButtonListener = new AddNewUserButtonListener(model,view);
        view.setAddNewUserButtonListener(addNewUserButtonListener);
        LOG.info("NewUserButtonListener added.");
    }

    public void addSendButtonListener(){
        ActionListener sendButtonListener = new SendButtonListener(model,view);
        view.setSendButtonListener(sendButtonListener);
        LOG.info("SendButtonListener added.");
    }
    public void addExitButtonListener(){
        CloseProgram closeListener = new CloseProgram();
        NewUserWindowsListener windowsListener = new NewUserWindowsListener();
        ExitButtonListener exitButtonListener = new ExitButtonListener();
        view.setExitButtonListener(closeListener,windowsListener,exitButtonListener);
        LOG.info("ExitButtonListener added.");
    }

    public void addWindowsListener(){
        WindowsListener windowsListener = new WindowsListener(model, view);
        view.setWindowListener(windowsListener);
        LOG.info("WindowsListener added.");
    }
    public void addSendMessageKeyListener(){
        SendMessageKeyPressedListener keyListener = new SendMessageKeyPressedListener(model,view);
        view.setMessageTextFieldKeyListener(keyListener);
        LOG.info("SendMessageKeyListener added");
    }
    public void addSendUserKeyListener(){
        SendUserKeyPressedListener keyListener = new SendUserKeyPressedListener(model,view);
        view.setUserTextFieldKeyListener(keyListener);
        LOG.info("SendUserKeyListener added");
    }
}
