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

        LOG.trace("Send message Jbutton pressed.");

        try {
            LOG.debug("Try to send message.");

            String message = view.getMessage();

            if (message.length() != 0) {

                model.sendMessageToServer(model.createMessage(message, model.getThisUserName()), model.getThisUserName(), view);
                LOG.debug("Successfully.");
            }
            view.cleanField();
        } catch (IOException e1) {
            LOG.error("IOException: Can't send message ", e1);
        }
    }
}