package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.ActionListener;
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
        addExitKeyListener();
        addNewUserWindowsListener();

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
    public void addExitKeyListener(){
        CloseProgramJButtonListener closeListener = new CloseProgramJButtonListener();
        ExitKeyListener keyListener = new ExitKeyListener();
        view.setExitButtonListener(closeListener,keyListener);
        LOG.info("ExitKeyListener added.");
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
    public void addNewUserWindowsListener(){
        NewUserWindowsListener windowsListener = new NewUserWindowsListener(model,view);
        view.addNewUserWindowsListener(windowsListener);
    }
}
