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
            model.sendMessageToServer(model.createMessage(view.getMessage(), model.getThisUserName()),view);
            LOG.info("Successfully.");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        view.cleanField();
    }
}
