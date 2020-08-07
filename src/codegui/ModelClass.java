package codegui;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * <b>The Model</b>.<br>This class contains methods used to process but mainly to store data, which in time is going to
 * be used by the controller.
 */
public class ModelClass {
    private ArrayList<AddressBookData> myArrayList = new ArrayList<>();
    private DefaultTableModel myModel;
    private int saveAndReplace;
    private TableRowSorter<TableModel> mySorter;
    private String frameTitle = "Address Book";

    /**
     * This method as its name suggests creates the model that is going to be used by the table in the program, here the
     * column identifiers are defined and a new sorter is applied so the existing data can be easily sorted by any of
     * the four column's values.
     */
    public void createTableModel() {
        String[] columnTitle = {"Name", "Phone", "Email", "Address"};
        setMyModel(new DefaultTableModel(columnTitle, 0) {
            @Override
            public Class getColumnClass(int column) {
                return String.class;
            }

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        setMySorter(new TableRowSorter<>(myModel));
        getMySorter().setSortsOnUpdates(true);
    }

    /**
     * This method receives a file loaded by the user which contains the contacts' data that is going to be used to fill
     * every row, when the data is loaded and read line by line there is a part that is in charge of split those values
     * by a delimiter found after every value, so we have a set of four values that identify the contact's information,
     * this helps to store easily the data in the corresponding column later on.
     * <br>
     * After being split by the delimiter the information is stored in an object that then is added to an array list.
     *
     * @param file contains the contacts' data loaded by the user in a format of one user per line with a delimiter
     *             after every category (e.g., name|phone|e-mail|address ).
     * @throws IOException this exception is thrown when the selected file is invalid.
     */
    public void readFile(File file) throws IOException {
        String currentLine;
        FileReader myFileReader;
        BufferedReader myBuffReader;

        myFileReader = new FileReader(file);
        myBuffReader = new BufferedReader(myFileReader);

        while ((currentLine = myBuffReader.readLine()) != null) {

            String[] splitLine = currentLine.split("\\|");

            myArrayList.add(new AddressBookData(splitLine[0], splitLine[1], splitLine[2], splitLine[3]));
        }
    }

    /**
     * This method contains the functionality to load the previously filled array list (the one that contains the
     * contacts' data) to our table, it does this one contact by row.
     *
     * @param model contains the model previously assigned to the table.
     */
    public void fillTableModel(DefaultTableModel model) {
        model.setRowCount(0);
        for (AddressBookData tempDataArray : myArrayList) {
            model.addRow(new Object[]{tempDataArray.getContactName(), tempDataArray.getContactPhone(), tempDataArray.getContactEmail(), tempDataArray.getContactAddress()});
        }
    }

    /**
     * This method receives the information of a new contact and adds that data in the already created array list (the
     * one containing all the contacts' data). The data has been previously validated so the user can't add a blank
     * contact or leave missing information.
     *
     * @param name    contains the name of the new contact.
     * @param phone   contains the telephone number of the new contact.
     * @param mail    contains the e-mail of the new contact.
     * @param address contains the address of the new contact.
     */
    public void addNewContact(String name, String phone, String mail, String address) {
        if (myArrayList == null) {
            myArrayList = new ArrayList<>();
        }

        myArrayList.add(new AddressBookData(name, phone, mail, address));
    }

//Accessors and Mutators methods

    /**
     * Gets the value of the my array list variable.
     *
     * @return an array list type that contains all the contacts' data.
     */
    public ArrayList<AddressBookData> getMyArrayList() {
        return myArrayList;
    }

    /**
     * Sets the value in the my array list variable.
     *
     * @param myArrayList contains the information of the contacts to be added to the list.
     */
    public void setMyArrayList(ArrayList<AddressBookData> myArrayList) {
        this.myArrayList = myArrayList;
    }

    /**
     * Gets the value of the my model variable.
     *
     * @return a default table model type that is in charge of hold the data visualized in the table.
     */
    public DefaultTableModel getMyModel() {
        return myModel;
    }

    /**
     * Sets the value in the my model variable.
     *
     * @param myModel contains the my model value. If the model gets modified this is the method to set its new value.
     */
    public void setMyModel(DefaultTableModel myModel) {
        this.myModel = myModel;
    }

    /**
     * Gets the value of the frame title variable.
     *
     * @return a string type that contains just the title displayed on this app's frame.
     */
    public String getFrameTitle() {
        return frameTitle;
    }

    /**
     * Sets the value in the frame title variable.
     *
     * @param frameTitle contains the frame title value that is going to be displayed in the main window of the
     *                   program.
     */
    public void setFrameTitle(String frameTitle) {
        this.frameTitle = frameTitle;
    }

    /**
     * Gets the value of the my sorter variable.
     *
     * @return a table row sorter type that contains the functionality to allow sort by column.
     */
    public TableRowSorter<TableModel> getMySorter() {
        return mySorter;
    }

    /**
     * Sets the value in the my sorter variable.
     *
     * @param mySorter contains the my sorter value, if the sorter gets modified this is the method to set the new one.
     */
    public void setMySorter(TableRowSorter<TableModel> mySorter) {
        this.mySorter = mySorter;
    }

    /**
     * Gets the value of the save and replace variable, this value is not used in this class but saves the state of the
     * decision taken by the user when he is asked if he wants to save and replace an existing file with the same name
     * as the one he input.
     *
     * @return an int type that contains the decision taken by the user according to which button he choose.
     */
    public int getSaveAndReplace() {
        return saveAndReplace;
    }

    /**
     * Sets the value in the save and replace variable.
     *
     * @param saveAndReplace contains the save and replace value which is the election taken by the user about if he
     *                       wants to save and replace an existing file.
     */
    public void setSaveAndReplace(int saveAndReplace) {
        this.saveAndReplace = saveAndReplace;
    }
}
