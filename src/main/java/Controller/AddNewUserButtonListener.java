package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddNewUserButtonListener implements ActionListener {

    private static final Logger LOG = Logger.getLogger(AddNewUserButtonListener.class);

    Model model;
    View view;

    public AddNewUserButtonListener (Model model, View view){
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOG.info("Add new user Jbutton pressed.");

        String userName = view.getUserName();

        if (userName.length() == 0 ){

            view.showMessageDialog("Вы не ввели имя, пожалуйста, попробуйте еще раз.");
        }else {

            if(userName.length() > 16){

                view.showMessageDialog("Слишком длинное имя. Максимальное кол-во символов - 16");
            }

            model.setThisUserName(userName);

            try {
                LOG.info("Try to send user.");
                model.sendNewUserToServer(view.getUserName(), view);
                view.closeJFrame();
                LOG.info("Successfully.");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }
}
