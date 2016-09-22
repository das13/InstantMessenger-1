package View;

import Controller.NewUserWindowsListener;
import Controller.NoConnectionWindowsListener;
import Controller.WindowsListener;
import Model.User;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ManageViewElements extends JFrame {

    private static final Logger LOG = Logger.getLogger(ManageViewElements.class);

    JFrame addNewUserJFrame = new JFrame();
    JFrame noConnectionJFrame = new JFrame();

    JPanel usersJPanel = new JPanel();
    JPanel messagesJPanel = new JPanel();
    JPanel sendBarJpanel = new JPanel();
    JPanel welcomeJpanel = new JPanel();
    JPanel addNewUserJpanel = new JPanel();

    JButton sendMessageJButton = new JButton("Отправить");
    JButton addNewUserJButton = new JButton("Ок");
    JButton exit = new JButton("Ок");

    JTextField nameOfUserJTextField = new JTextField(18);
    JTextArea textOfMessageJAreaField = new JTextArea(3,45);
    JScrollPane scrollPaneMessage = new JScrollPane(textOfMessageJAreaField);

    JTextArea listOfMasseges = new JTextArea();
    JList listOfUsers = new JList();

    JScrollPane scrollPane = new JScrollPane(listOfMasseges);
    JScrollPane scrollPaneUser = new JScrollPane(listOfUsers);

    DefaultListModel userModelOfTask = new DefaultListModel();


    ManageViewElements() {

        super();

        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        listOfUsers.setModel(userModelOfTask);

        listOfMasseges.setEditable(false);
        listOfMasseges.setLineWrap(true);
        listOfMasseges.setWrapStyleWord(true);

        sendBarJpanel.setLayout(new FlowLayout());
        usersJPanel.setLayout(new BorderLayout());
        messagesJPanel.setLayout(new BorderLayout());

        textOfMessageJAreaField.setLineWrap(true);;
        textOfMessageJAreaField.setWrapStyleWord(true);

        sendBarJpanel.add(scrollPaneMessage);
        sendBarJpanel.add(sendMessageJButton);

        usersJPanel.add(scrollPaneUser, BorderLayout.CENTER);

        messagesJPanel.add(scrollPane, BorderLayout.CENTER);
        messagesJPanel.add(sendBarJpanel, BorderLayout.PAGE_END);

        add(messagesJPanel, BorderLayout.LINE_END);
        add(usersJPanel, BorderLayout.CENTER);

        setVisible(true);
        setResizable(false);
        setLocationRelativeTo(null);

        LOG.info("View created.");
    }

    public void addNewUserWindow() {

        addNewUserJFrame.setSize(280, 90);

        welcomeJpanel = new JPanel();
        addNewUserJpanel = new JPanel();

        JLabel welcomeJlabel = new JLabel("Пожалуйста, введите свое имя:");

        addNewUserJpanel.setLayout(new FlowLayout());
        addNewUserJpanel.add(nameOfUserJTextField);
        addNewUserJpanel.add(addNewUserJButton);

        welcomeJpanel.setLayout(new FlowLayout());
        welcomeJpanel.add(welcomeJlabel);

        addNewUserJFrame.setLayout(new BorderLayout());

        addNewUserJFrame.add(welcomeJpanel, BorderLayout.PAGE_START);
        addNewUserJFrame.add(addNewUserJpanel, BorderLayout.CENTER);

        addNewUserJFrame.setVisible(true);
        addNewUserJFrame.setResizable(false);
        addNewUserJFrame.setLocationRelativeTo(null);
    }

    public void noConnectionJFrame(){

        noConnectionJFrame.setSize(325, 90);
        noConnectionJFrame.setLayout(new FlowLayout());

        NoConnectionWindowsListener windowsListener = new NoConnectionWindowsListener();
        noConnectionJFrame.addWindowListener(windowsListener);

        JLabel textMessage = new JLabel("Нет поключения,  пожалуйста,  попробуйте позже.");

        noConnectionJFrame.add(textMessage);
        noConnectionJFrame.add(exit);

        noConnectionJFrame.setVisible(true);
        noConnectionJFrame.setLocationRelativeTo(null);
        noConnectionJFrame.setResizable(false);
        addNewUserJFrame.setEnabled(false);
        setEnabled(false);
    }

    public void addMessage(String message) {
        listOfMasseges.append(message + "\n");
    }

    public String getMessage() {
        return textOfMessageJAreaField.getText();
    }

    public String getUserName() {
        return nameOfUserJTextField.getText();
    }

    public void setUserList(ArrayList<User> list) {
        this.userModelOfTask.clear();

        if (list.size() == 0) {
            return;
        }

        for (int i = 0; i < list.size() - 1; i++) {
            String tempUser = "* "+ list.get(i).getUser_Name() + ";";
            userModelOfTask.addElement(tempUser);
        }
        String tempUser = "* "+ list.get(list.size() - 1).getUser_Name() + ".";
        userModelOfTask.addElement(tempUser);
    }

    public void setSendButtonListener(ActionListener event) {
        sendMessageJButton.addActionListener(event);
    }

    public void setAddNewUserButtonListener(ActionListener event) {
        addNewUserJButton.addActionListener(event);
    }

    public void setMessageTextFieldKeyListener(KeyListener listener) {

        textOfMessageJAreaField.addKeyListener(listener);
    }

    public void setUserTextFieldKeyListener(KeyListener listener) {

        nameOfUserJTextField.addKeyListener(listener);
    }

    public void cleanField(){
        textOfMessageJAreaField.setText("");

        try {
            Robot r = new Robot();

            r.keyPress(KeyEvent.VK_BACK_SPACE);
        } catch (AWTException e) {
            e.printStackTrace();
        }

    }

    public void closeJFrame(){
        addNewUserJFrame.setVisible(false);
    }

    public void setExitButtonListener(ActionListener listener, KeyListener keyListener){

        noConnectionJFrame.addKeyListener(keyListener);
        exit.addActionListener(listener);
    }

    public void addNewUserWindowsListener(NewUserWindowsListener listener){

        addNewUserJFrame.addWindowListener(listener);
    }
}