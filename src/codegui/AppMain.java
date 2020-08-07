package codegui;

/**
 * This is the class that contains the main method of this application, this program is a contact address book, which is
 * used to store contact's information like name, phone number, e-mail and address. Even though the functionality is
 * pretty basic, the real goal behind this was to get familiar with Java Swing and mainly with the creation and manipulation
 * of tables.
 */
public class AppMain {

    /**
     * The entry point of the application. This method is executed right after the application starts. Since this
     * program was written following the MVC paradigm this section initializes the model, the view and passes those as
     * arguments to a new instantiated controller object which in the end is the one having the control of the flow
     * within this app.
     *
     * @param args default value
     */
    public static void main(String[] args) {

        // Assemble all the pieces of the MVC
        ModelClass modelObj = new ModelClass();
        ViewClass viewObj = new ViewClass();
        ControllerClass controllerObj = new ControllerClass(modelObj, viewObj);

        controllerObj.initView();
        controllerObj.initController();
    }
}