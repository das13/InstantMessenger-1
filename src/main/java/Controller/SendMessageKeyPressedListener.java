package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class SendMessageKeyPressedListener implements KeyListener {

    private static final Logger LOG = Logger.getLogger(SendMessageKeyPressedListener.class);

    Model model;
    View view;

    public SendMessageKeyPressedListener(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == 10 ){

            String message = view.getMessage();

            if (message.length() == 0) {

            } else {

                try {
                    model.sendMessageToServer(model.createMessage(message, model.getThisUserName()), view);
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                LOG.info("Successfully.");
            }
            view.cleanField();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
