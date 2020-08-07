package codegui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;

/**
 * <b>The controller</b>.<br>This class contains methods used to accept input from the user and convert it to commands
 * for the Model or View.
 */
public class ControllerClass {
    private final ModelClass modelObj;
    private final ViewClass viewObj;

    /**
     * The content of this constructor is executed when the object of type ControllerClass is instantiated.
     *
     * @param m contains the model already instantiated by the main class.
     * @param v contains the view already instantiated by the main class.
     */
    public ControllerClass(ModelClass m, ViewClass v) {
        modelObj = m;
        viewObj = v;
    }

    /**
     * This method calls the view, which in turn is going to draw all the graphic elements of the main window.
     */
    public void initView() {
        modelObj.createTableModel();
        viewObj.drawView(modelObj.getFrameTitle(), modelObj.getMyModel(), modelObj.getMySorter());
    }

    /**
     * This method contains the Listeners for every button, the handlers are the methods below.
     */
    public void initController() {
        viewObj.getSaveAllMenuItem().addActionListener(e -> saveData());
        viewObj.getOpenMenuItem().addActionListener(e -> loadData());
        viewObj.getClearButton().addActionListener(e -> clearSearch());
        viewObj.getExitMenuItem().addActionListener(e -> closeApp());

        viewObj.getClearAllMenuItem().addActionListener(e -> clearContacts());

        viewObj.getNewMenuItem().addActionListener(e -> newContact());
        viewObj.getEditMenuItem().addActionListener(e -> editContact());
        viewObj.getDeleteMenuItem().addActionListener(e -> deleteContact());

        viewObj.getNewContactBtn().addActionListener(e -> newContact());
        viewObj.getEditContactBtn().addActionListener(e -> editContact());
        viewObj.getDeleteContactBtn().addActionListener(e -> deleteContact());

        viewObj.getDataTable().getSelectionModel().addListSelectionListener(e -> selectContact());
        viewObj.getSearchTxtField().getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = viewObj.getSearchTxtField().getText();

                if (text.trim().length() == 0) {
                    ((TableRowSorter<TableModel>) viewObj.getDataTable().getRowSorter()).setRowFilter(null);
                } else {
                    ((TableRowSorter<TableModel>) viewObj.getDataTable().getRowSorter()).setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = viewObj.getSearchTxtField().getText();

                if (text.trim().length() == 0) {
                    ((TableRowSorter<TableModel>) viewObj.getDataTable().getRowSorter()).setRowFilter(null);
                } else {
                    ((TableRowSorter<TableModel>) viewObj.getDataTable().getRowSorter()).setRowFilter(RowFilter.regexFilter("(?i)" + text));
                }
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
        viewObj.getFrame().addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                closeApp();
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
        });
    }

    /**
     * This method is called by a listener, calls the corresponding methods to receive the information of a new contact
     * and save it in the table.
     */
    public void newContact() {
        viewObj.drawContactForm("", "", "", "");

        Object[] options = {"Save", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, viewObj.getFormPanel(), "New Contact", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, "");

        if (result == JOptionPane.OK_OPTION) {

            boolean emptyFields = isFormEmpty(viewObj.getNameTxtField().getText(), viewObj.getPhoneTxtField().getText(),
                    viewObj.getMailTxtField().getText(), viewObj.getAddressTxtField().getText());
            if (emptyFields) {
                viewObj.emptyFormPopup();
                newContact();
                return;
            }

            modelObj.addNewContact(viewObj.getNameTxtField().getText(), viewObj.getPhoneTxtField().getText(),
                    viewObj.getMailTxtField().getText(), viewObj.getAddressTxtField().getText());

            int position = modelObj.getMyArrayList().size();
            AddressBookData tempArrayL = modelObj.getMyArrayList().get(position - 1);

            Object[] contactData = {tempArrayL.getContactName(), tempArrayL.getContactPhone(),
                    tempArrayL.getContactEmail(), tempArrayL.getContactAddress()};

            ((DefaultTableModel) viewObj.getDataTable().getModel()).addRow(contactData);
        }

        resizeColumnWidth(viewObj.getDataTable());
    }

    /**
     * This method is called by a listener, after that, calls the corresponding functionality to edit an existing
     * contact and update those changes.
     */
    public void editContact() {
        int currentRow = viewObj.getDataTable().getSelectedRow();

        //row == -1 means no selected contact
        if (currentRow == -1) {
            viewObj.getNameText().setText(null);
            viewObj.getPhoneText().setText(null);
            viewObj.getMailText().setText(null);
            viewObj.getAddressText().setText(null);
            return;
        }

        int clickedRow = viewObj.getDataTable().convertRowIndexToModel(currentRow);

        viewObj.drawContactForm(viewObj.getDataTable().getModel().getValueAt(clickedRow, 0).toString(),
                viewObj.getDataTable().getModel().getValueAt(clickedRow, 1).toString(),
                viewObj.getDataTable().getModel().getValueAt(clickedRow, 2).toString(),
                viewObj.getDataTable().getModel().getValueAt(clickedRow, 3).toString());

        Object[] options = {"Save", "Cancel"};
        int result = JOptionPane.showOptionDialog(null, viewObj.getFormPanel(), "Edit Contact", JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE, null, options, "");

        if (result == JOptionPane.OK_OPTION) {
            AddressBookData tempAdBookD = new AddressBookData(viewObj.getNameTxtField().getText(), viewObj.getPhoneTxtField().getText(),
                    viewObj.getMailTxtField().getText(), viewObj.getAddressTxtField().getText());

            viewObj.getDataTable().getModel().setValueAt(tempAdBookD.getContactName(), clickedRow, 0);
            viewObj.getDataTable().getModel().setValueAt(tempAdBookD.getContactPhone(), clickedRow, 1);
            viewObj.getDataTable().getModel().setValueAt(tempAdBookD.getContactEmail(), clickedRow, 2);
            viewObj.getDataTable().getModel().setValueAt(tempAdBookD.getContactAddress(), clickedRow, 3);
        }

        resizeColumnWidth(viewObj.getDataTable());
    }

    /**
     * This method contains the functionality to check that no field has been leaved blank in the "new contact" form.
     *
     * @param name    contains the name of the new contact.
     * @param phone   contains the telephone number of the new contact.
     * @param email   contains the e-mail of the new contact.
     * @param address contains the address of the new contact.
     * @return a boolean that contains a value indicating if the form has been properly filled.
     */
    public boolean isFormEmpty(String name, String phone, String email, String address) {
        return !(name).matches("(.*\\w.*)") || !(phone).matches("(.*\\w.*)") ||
                !(email).matches("(.*\\w.*)") || !(address).matches("(.*\\w.*)");
    }

    /**
     * This method is called by a listener, after that, calls the corresponding functionality to delete one or more
     * contacts in the current table.
     */
    public void deleteContact() {
        clearSearch();

        int[] currentRow = viewObj.getDataTable().getSelectedRows();

        if (currentRow.length == 0) {
            return;
        }

        for (int i = currentRow.length - 1; i >= 0; i--) {
            int viewIndex = currentRow[i];
            int modelIndex = viewObj.getDataTable().convertRowIndexToModel(viewIndex);
            ((DefaultTableModel) viewObj.getDataTable().getModel()).removeRow(modelIndex);

            modelObj.getMyArrayList().remove(modelIndex);
        }

        resizeColumnWidth(viewObj.getDataTable());
    }

    /**
     * This method is called by a listener, contains the functionality to get the data in the selected row, the data in
     * every column is showed in the right panel to facilitate its view by the user.
     */
    public void selectContact() {
        int row = viewObj.getDataTable().getSelectedRow();

        //row == -1 means no selected contact
        if (row == -1) {
            viewObj.getNameText().setText(null);
            viewObj.getPhoneText().setText(null);
            viewObj.getMailText().setText(null);
            viewObj.getAddressText().setText(null);
            return;
        }

        int clickedRow = viewObj.getDataTable().convertRowIndexToModel(row);

        viewObj.getNameText().setText(viewObj.getDataTable().getModel().getValueAt(clickedRow, 0).toString());
        viewObj.getPhoneText().setText(viewObj.getDataTable().getModel().getValueAt(clickedRow, 1).toString());
        viewObj.getMailText().setText(viewObj.getDataTable().getModel().getValueAt(clickedRow, 2).toString());
        viewObj.getAddressText().setText(viewObj.getDataTable().getModel().getValueAt(clickedRow, 3).toString());
    }

    /**
     * This method is called by a listener, and it has the functionality to remove all the contacts in the current
     * table.
     */
    public void clearContacts() {
        clearSearch();

        modelObj.getMyArrayList().clear();
        ((DefaultTableModel) viewObj.getDataTable().getModel()).setRowCount(0);

        resizeColumnWidth(viewObj.getDataTable());
    }

    /**
     * This method is called by a listener, and it has the functionality to save the current data in the table to a file
     * that can be loaded in another session.
     */
    public void saveData() {
        if (modelObj.getMyArrayList().isEmpty()) {
            return;
        }

        JFrame parentFrame = new JFrame();
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Specify a file to save");
        String userDir = System.getProperty("user.home");
        chooser.setCurrentDirectory(new File(userDir + "/Desktop"));
        int actionDialog = chooser.showSaveDialog(parentFrame);

        if (actionDialog == JFileChooser.APPROVE_OPTION) {
            File fileName = new File(chooser.getSelectedFile() + ".txt");

            if (fileName == null) {
                return;
            }

            if (fileName.exists()) {
                actionDialog = JOptionPane.showConfirmDialog(parentFrame, "Replace existing file?");

                modelObj.setSaveAndReplace(actionDialog);

                if (actionDialog == JOptionPane.YES_NO_CANCEL_OPTION) {
                    saveData();
                    return;
                } else if (actionDialog != JOptionPane.OK_OPTION) {
                    return;
                }
            }

            BufferedWriter outFile;
            try {
                outFile = new BufferedWriter(new FileWriter(fileName));
                modelObj.getMyArrayList().sort(Comparator.comparing(AddressBookData::getContactName));

                for (AddressBookData currentLine : modelObj.getMyArrayList()) {
                    outFile.write(currentLine.getContactName() + "|" + currentLine.getContactPhone() + "|" + currentLine.getContactEmail() + "|" + currentLine.getContactAddress() + "\n");

                }
                outFile.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            return;
        }
    }

    /**
     * This method is called by a listener, and it has the functionality to open a file chooser window so the user can
     * find and select a valid file containing one or multiple contacts to add to the table.
     */
    public void loadData() {
        clearSearch();

        File file;
//        String userDir = System.getProperty("user.home");
//        JFileChooser fileDialog = new JFileChooser(userDir + "/Desktop");
        JFileChooser fileDialog = new JFileChooser("./contacts");
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Contact database", "txt");
        fileDialog.setDialogTitle("Contact database to open");
        fileDialog.removeChoosableFileFilter(fileDialog.getAcceptAllFileFilter());
        fileDialog.setFileFilter(filter);

        if (fileDialog.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            file = fileDialog.getSelectedFile();
        } else {
            return;
        }

        if (viewObj.getDataTable().getRowCount() > 0) {
            int selectedButton = viewObj.overwritePopup();

            if (selectedButton == JOptionPane.YES_OPTION) {
                modelObj.getMyArrayList().clear();
                ((DefaultTableModel) viewObj.getDataTable().getModel()).setRowCount(0);

            } else if (selectedButton != JOptionPane.YES_NO_CANCEL_OPTION) {
                return;
            }
        }

        try {
            modelObj.readFile(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        modelObj.fillTableModel(((DefaultTableModel) viewObj.getDataTable().getModel()));

        resizeColumnWidth(viewObj.getDataTable());
    }

    /**
     * This method is called by a listener, it has the functionality to clear the search text field, resetting with this
     * the view of the table.
     */
    public void clearSearch() {
        viewObj.getSearchTxtField().setText(null);
        resizeColumnWidth(viewObj.getDataTable());
    }

    /**
     * This method has the functionality to update the width of the columns so it fits the current content. This method
     * is called after a change has been made in the table.
     *
     * @param table contains the table so we can modify the width property in this method.
     */
    public void resizeColumnWidth(JTable table) {
        if (viewObj.getDataTable().getRowCount() == 0) {
            viewObj.getDataTable().getRowSorter().setSortKeys(null);
            viewObj.getDataTable().setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
            return;
        } else {
            viewObj.getDataTable().getRowSorter().setSortKeys(null);
            viewObj.getDataTable().getRowSorter().toggleSortOrder(0);
            viewObj.getDataTable().setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }


        TableColumnModel columnModel = table.getColumnModel();
        for (int column = 0; column < table.getColumnCount(); column++) {
            int width = 50; // Minimum width
            for (int row = 0; row < table.getRowCount(); row++) {
                TableCellRenderer renderer = table.getCellRenderer(row, column);
                Component comp = table.prepareRenderer(renderer, row, column);
                width = Math.max(comp.getPreferredSize().width + 1, width);
            }
            if (width > 300) {
                width = 300;
            }
            columnModel.getColumn(column).setPreferredWidth(width + 10);
        }
    }

    /**
     * This method is called by a listener when the user wants to close the program by any method, either by pressing
     * the close button on the upper right (this in Windows, in Mac and Linux is on the left) or by selecting the menu
     * item "Exit" in the file menu.
     */
    public void closeApp() {
        if (modelObj.getMyArrayList().isEmpty()) {
            System.exit(0);
        } else {
            int result = viewObj.unsavedChanges();
            confirmExit(result);
        }
    }

    /**
     * This method is called when the user wants to close the program and there are unsaved changes, this method
     * receives a value corresponding to a prompt asking the user if he really wants to leave and loose any information
     * or if he wants to save.
     *
     * @param result contains the value of the election made in the alert message.
     */
    public void confirmExit(int result) {
        if (result == JOptionPane.YES_OPTION) {
            saveData();

            //If the save panel is closed the program shouldn't be closed but the file chooser should be.
            if (modelObj.getSaveAndReplace() == JOptionPane.YES_NO_CANCEL_OPTION) {
                confirmExit(result);
                return;
            } else if (modelObj.getSaveAndReplace() != JOptionPane.YES_OPTION) {
                return;
            }
            System.exit(0);

        } else if (result == JOptionPane.YES_NO_CANCEL_OPTION) {
            System.exit(0);
        }
    }
}
