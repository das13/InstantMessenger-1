package Controller;


import org.apache.log4j.Logger;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class NoConnectionWindowsListener implements WindowListener {

    private static final Logger LOG = Logger.getLogger(NewUserWindowsListener.class);

    @Override
    public void windowOpened(WindowEvent e) {

    }

    @Override
    public void windowClosing(WindowEvent e) {

        LOG.trace("No connection window closing. Close the program.");

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
