package Controller;

import Model.Model;
import View.View;
import org.apache.log4j.Logger;

public class StartProgram {

    private static final Logger LOG = Logger.getLogger(StartProgram.class);

    public static void main(String[] args) {

        LOG.info("Start program.");

        Model model = new Model();
        LOG.info("Model created.");

        View view = new View();
        LOG.info("View created.");

        Controller controller = new Controller(view, model);
        LOG.info("Controller created.");
    }
}
