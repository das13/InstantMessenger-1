package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class WindowsListener implements WindowListener {

    private static final Logger LOG = Logger.getLogger(WindowsListener.class);

    Model model;
    View view;

    public WindowsListener(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    @Override
    public void windowOpened(WindowEvent e) {
        LOG.info("Window opened.");
    }

    @Override
    public void windowClosing(WindowEvent e) {
        LOG.info("Window closing.");

        try {
            model.deleteUser(model.getThisUserName(),model.getThisUserId());
           // model.closeStreams();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    @Override
    public void windowClosed(WindowEvent e) {

    }

    @Override
    public void windowIconified(WindowEvent e) {

    }

    @Override
    public void windowDeiconified(WindowEvent e) {

    }

    @Override
    public void windowActivated(WindowEvent e) {

    }

    @Override
    public void windowDeactivated(WindowEvent e) {

    }
}
