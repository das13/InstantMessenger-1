package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class SendUserKeyPressedListener implements KeyListener {

    private static final Logger LOG = Logger.getLogger(SendMessageKeyPressedListener.class);

    Model model;
    View view;

    public SendUserKeyPressedListener(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        LOG.trace("Enter pressed. Try to send user.");

        if (e.getKeyCode() == 10) {

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
                    LOG.error("IOException: Can't send new user "+ e1);
                }
            }
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
