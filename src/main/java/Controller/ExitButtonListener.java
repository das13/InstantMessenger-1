package Controller;

import org.apache.log4j.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ExitButtonListener implements KeyListener {

    private static final Logger LOG = Logger.getLogger(ExitButtonListener.class);

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        LOG.info("Close program");

        if (e.getKeyCode() == 10 ){
            System.exit(-1);
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
