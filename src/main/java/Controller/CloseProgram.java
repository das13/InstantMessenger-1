package Controller;

import org.apache.log4j.Logger;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CloseProgram implements ActionListener {

    private static final Logger LOG = Logger.getLogger(CloseProgram.class);

    @Override
    public void actionPerformed(ActionEvent e) {

        System.exit(-1);
    }
}

