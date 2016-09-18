package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class SendButtonListener implements ActionListener {

    private static final Logger LOG = Logger.getLogger(SendButtonListener.class);

    Model model;
    View view;

    public SendButtonListener(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        LOG.info("Send message Jbutton pressed.");

        try {
            LOG.info("Try to send message.");

            String message = view.getMessage();

            if (message.length() == 0) {

            } else {

                model.sendMessageToServer(model.createMessage(message, model.getThisUserName()), view);
                LOG.info("Successfully.");
            }
            view.cleanField();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
}