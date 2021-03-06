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

        LOG.trace("Add new user Jbutton pressed.");

        String userName = view.getUserName();

        boolean keepOn = true;

        if (userName.length() == 0 ){

             keepOn = false;

            view.notificateFromNewUser("Вы не ввели имя, пожалуйста, попробуйте еще раз.");
        }

            if(userName.length() > 16){

                keepOn = false;

                view.notificateFromNewUser("Слишком длинное имя. Максимальное кол-во символов - 16");
            }

            if(keepOn){
                model.setThisUserName(userName);

                try {
                    LOG.debug("Try to send user.");
                    model.sendNewUserToServer(view.getUserName(), view);
                    view.closeJFrame();
                    LOG.debug("Successfully.");
                } catch (IOException e1) {
                    LOG.error("IOException: Can't send new user ", e1);
                }
            }
    }
}
