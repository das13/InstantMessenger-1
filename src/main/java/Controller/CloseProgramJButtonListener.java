package Controller;

import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseProgramJButtonListener implements ActionListener {

    private static final Logger LOG = Logger.getLogger(CloseProgramJButtonListener.class);

    @Override
    public void actionPerformed(ActionEvent e) {

        LOG.trace("Ok button pressed. Close the program.");

        System.exit(-1);
    }
}

