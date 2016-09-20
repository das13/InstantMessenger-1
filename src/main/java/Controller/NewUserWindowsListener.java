package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

public class NewUserWindowsListener implements WindowListener {

    private static final Logger LOG = Logger.getLogger(NewUserWindowsListener.class);

    Model model;
    View view;

    public NewUserWindowsListener(Model model, View view) {
       this.model = model;
       this.view = view;
    }

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {
       LOG.trace("New user window closing.");
       try {
          model.closeStreams();
       } catch (IOException e1) {
           LOG.error("IOException: Can't close streams "+ e1);
       }
       System.exit(-1);
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
