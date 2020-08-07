package codegui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * <b>The View</b>.<br>This class contains methods used to present data to the user.
 * <br>
 * This is the View that uses Swing elements to draw a Graphical User Interface. The View builds the GUI in its methods
 * and offers getters and setters to all its visual components so they can be modified. It's necessary to add action
 * listeners later in the Controller.
 */
public class ViewClass {

    // Elements in the frame
    private JFrame frame;
    private JPanel rootPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JLabel contactLabel;
    private JScrollPane dataScrollPane;
    private JTable dataTable;
    private JTextField searchTxtField;
    private JButton clearButton;
    private JTextPane nameText;
    private JLabel phoneLabel;
    private JLabel mailLabel;
    private JLabel addressLabel;
    private JTextPane phoneText;
    private JTextPane mailText;
    private JTextPane addressText;
    private JButton newContactBtn;
    private JButton editContactBtn;
    private JButton deleteContactBtn;

    //Elements in the menu bar
    private JMenuBar myMenuBar;
    private JMenu myFileMenu;
    private JMenuItem saveAllMenuItem;
    private JMenuItem openMenuItem;
    private JMenuItem clearAllMenuItem;
    private JMenuItem exitMenuItem;
    private JMenu myOptionsMenu;
    private JMenuItem newMenuItem;
    private JMenuItem editMenuItem;
    private JMenuItem deleteMenuItem;

    //Elements in the contact form
    private JTextField nameTxtField;
    private JTextField phoneTxtField;
    private JTextField mailTxtField;
    private JTextField addressTxtField;
    private JPanel formPanel;

    /**
     * This method draws the main window with all the necessary graphic components.
     *
     * @param title    contains the title of this program.
     * @param myModel  contains the model that is going to be assigned to the table.
     * @param mySorter contains the sorter that is going to be assigned to the table.
     */
    public void drawView(String title, DefaultTableModel myModel, TableRowSorter<TableModel> mySorter) {
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Throwable e) {
            e.printStackTrace();
        }

        Toolkit myToolKit = Toolkit.getDefaultToolkit();
        int xSize = (int) (Math.round(myToolKit.getScreenSize().getWidth() * 0.75));
        int ySize = (int) (Math.round(myToolKit.getScreenSize().getHeight() * 0.75));

        //Create Menu elements
        myMenuBar = new JMenuBar();
        myMenuBar.setLayout(new GridBagLayout());

        myFileMenu = new JMenu("File");
        saveAllMenuItem = new JMenuItem("Save All");
        openMenuItem = new JMenuItem("Open");
        clearAllMenuItem = new JMenuItem("Clear All");
        exitMenuItem = new JMenuItem("Exit");

        myOptionsMenu = new JMenu("Contact");
        newMenuItem = new JMenuItem("New");
        editMenuItem = new JMenuItem("Edit");
        deleteMenuItem = new JMenuItem("Delete");

        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.02;
        gbc.weighty = 1.0;
        myFileMenu.setBackground(new Color(158, 158, 158));
        myFileMenu.setOpaque(true);
        gbc.fill = GridBagConstraints.BOTH;
        myMenuBar.add(myFileMenu, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.02;
        myOptionsMenu.setBackground(new Color(158, 158, 158));
        myOptionsMenu.setOpaque(true);
        myMenuBar.add(myOptionsMenu, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0.96;
        JLabel blankSpace = new JLabel();
        blankSpace.setBackground(new Color(158, 158, 158));
        blankSpace.setOpaque(true);
        myMenuBar.add(blankSpace, gbc);


        myFileMenu.add(saveAllMenuItem);
        myFileMenu.add(openMenuItem);
        myFileMenu.add(clearAllMenuItem);
        myFileMenu.add(exitMenuItem);
        myOptionsMenu.add(newMenuItem);
        myOptionsMenu.add(editMenuItem);
        myOptionsMenu.add(deleteMenuItem);

        // Create UI elements
        rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        rootPanel.setPreferredSize(new Dimension(0, 0));
        //********************************************
        rootPanel.setBackground(new Color(158, 158, 158));
        //********************************************
        leftPanel = new JPanel();
        leftPanel.setLayout(new GridBagLayout());
        leftPanel.setPreferredSize(new Dimension(0, 0));
        //********************************************
        leftPanel.setBackground(new Color(158, 158, 158));
        //********************************************
//        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.7;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        rootPanel.add(leftPanel, gbc);
        leftPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black)));
        contactLabel = new JLabel();
        contactLabel.setHorizontalAlignment(0);
        contactLabel.setPreferredSize(new Dimension(0, 0));
        contactLabel.setText("All Contacts");
        contactLabel.setVerticalTextPosition(0);
        //********************************************
        contactLabel.setFont(new Font("Arial", Font.BOLD, 20));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        leftPanel.add(contactLabel, gbc);
        dataScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dataScrollPane.setPreferredSize(new Dimension(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        leftPanel.add(dataScrollPane, gbc);
        dataTable = new JTable();
        //******
        dataTable.setModel(myModel);
        dataTable.setRowSorter(mySorter);
//        TableColumn myTableColumn0 = dataTable.getColumnModel().getColumn(0);
//        dataTable.getColumnModel().removeColumn(myTableColumn0);
//        dataScrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
//        dataTable.setAutoCreateColumnsFromModel(false);
//        dataTable.putClientProperty("Table.isFileList", Boolean.FALSE);
        //******
        dataScrollPane.setViewportView(dataTable);
        searchTxtField = new JTextField();
        searchTxtField.setPreferredSize(new Dimension(0, 0));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.94;
        gbc.weighty = 0.05;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        leftPanel.add(searchTxtField, gbc);
        clearButton = new JButton();
        clearButton.setFocusable(false);
        clearButton.setPreferredSize(new Dimension(0, 0));
        clearButton.setText("x");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.06;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        leftPanel.add(clearButton, gbc);
        rightPanel = new JPanel();
        rightPanel.setLayout(new GridBagLayout());
        rightPanel.setPreferredSize(new Dimension(0, 0));
        //********************************************
        rightPanel.setBackground(new Color(158, 158, 158));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 0, 5, 5);
        rootPanel.add(rightPanel, gbc);
        rightPanel.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.black), null));
        nameText = new JTextPane();
        //********************************************
        nameText.setFont(new Font("Arial", Font.BOLD, 30));
        StyledDocument doc = nameText.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
//        nameText.setForeground(Color.BLACK);
        nameText.setBackground(new Color(158, 158, 158));
        //********************************************
        nameText.setEditable(false);
//        nameText.setEnabled(false);
        nameText.setPreferredSize(new Dimension(0, 0));
        nameText.setRequestFocusEnabled(false);
        nameText.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.weightx = 0.5;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 15);
        rightPanel.add(nameText, gbc);
        phoneLabel = new JLabel();
        phoneLabel.setPreferredSize(new Dimension(0, 0));
        phoneLabel.setText("Telephone:");
        //********************************************
        phoneLabel.setFont(new Font("Arial", Font.BOLD, 15));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 15);
        rightPanel.add(phoneLabel, gbc);
        mailLabel = new JLabel();
        mailLabel.setPreferredSize(new Dimension(0, 0));
        mailLabel.setText("E-mail:");
        //********************************************
        mailLabel.setFont(new Font("Arial", Font.BOLD, 15));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 15);
        rightPanel.add(mailLabel, gbc);
        addressLabel = new JLabel();
        addressLabel.setPreferredSize(new Dimension(0, 0));
        addressLabel.setText("Address:");
        //********************************************
        addressLabel.setFont(new Font("Arial", Font.BOLD, 15));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 15);
        rightPanel.add(addressLabel, gbc);
        phoneText = new JTextPane();
        phoneText.setEditable(false);
        phoneText.setEnabled(true);
        phoneText.setPreferredSize(new Dimension(0, 0));
        phoneText.setText("");
        //********************************************
        phoneText.setBackground(new Color(158, 158, 158));
        phoneText.setFont(new Font("Arial", Font.PLAIN, 15));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 15);
        rightPanel.add(phoneText, gbc);
        mailText = new JTextPane();
        mailText.setEditable(false);
        mailText.setEnabled(true);
        mailText.setPreferredSize(new Dimension(0, 0));
        mailText.setText("");
        //********************************************
        mailText.setBackground(new Color(158, 158, 158));
        mailText.setFont(new Font("Arial", Font.PLAIN, 15));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.1;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 15);
        rightPanel.add(mailText, gbc);
        addressText = new JTextPane();
        addressText.setEditable(false);
        addressText.setEnabled(true);
        addressText.setMargin(new Insets(0, 0, 0, 0));
        addressText.setPreferredSize(new Dimension(0, 0));
        addressText.setText("");
        //********************************************
        addressText.setBackground(new Color(158, 158, 158));
        addressText.setFont(new Font("Arial", Font.PLAIN, 15));
        //********************************************
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 15);
        rightPanel.add(addressText, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.15;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 10);
        rightPanel.add(label1, gbc);
        newContactBtn = new JButton();
        newContactBtn.setFocusable(false);
        newContactBtn.setPreferredSize(new Dimension(0, 0));
        newContactBtn.setText("New");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.weightx = 0.33;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        rightPanel.add(newContactBtn, gbc);
        editContactBtn = new JButton();
        editContactBtn.setFocusable(false);
        editContactBtn.setPreferredSize(new Dimension(0, 0));
        editContactBtn.setText("Edit");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 9;
        gbc.weightx = 0.33;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        rightPanel.add(editContactBtn, gbc);
        deleteContactBtn = new JButton();
        deleteContactBtn.setFocusable(false);
        deleteContactBtn.setPreferredSize(new Dimension(0, 0));
        deleteContactBtn.setText("Delete");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 9;
        gbc.weightx = 0.33;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);
        rightPanel.add(deleteContactBtn, gbc);
        final JLabel label2 = new JLabel();
        label2.setText("");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.05;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(0, 10, 0, 10);
        rightPanel.add(label2, gbc);


        //Create the frame and set its size
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(xSize, ySize);
        frame.setLocationRelativeTo(null);

        //Set Mnemonics & Accelerators
        myFileMenu.setMnemonic('F');
        myOptionsMenu.setMnemonic('C');
        newContactBtn.setMnemonic('N');
        editContactBtn.setMnemonic('E');
        deleteContactBtn.setMnemonic('D');
        clearButton.setMnemonic('X');
        newMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        editMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
        deleteMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
        openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        saveAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        clearAllMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_DOWN_MASK | InputEvent.ALT_DOWN_MASK));

        //Add all elements to the frame
        frame.setContentPane(rootPanel);
        frame.setJMenuBar(myMenuBar);

        //Set visible
        frame.setVisible(true);
//        dataTable.repaint();
    }

    /**
     * This method draws the components of a contact's form, so the user can add a new entry in the address book
     *
     * @param name    contains the name of the new contact.
     * @param phone   contains the telephone number of the new contact.
     * @param email   contains the e-mail of the new contact.
     * @param address contains the address of the new contact.
     */
    public void drawContactForm(String name, String phone, String email, String address) {
        nameTxtField = new JTextField(name);
        phoneTxtField = new JTextField(phone);
        mailTxtField = new JTextField(email);
        addressTxtField = new JTextField(address);
        formPanel = new JPanel(new GridLayout(0, 1));
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameTxtField);
        formPanel.add(new JLabel("Phone:"));
        formPanel.add(phoneTxtField);
        formPanel.add(new JLabel("E-mail:"));
        formPanel.add(mailTxtField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressTxtField);
    }

    /**
     * This method draws an alert message informing the user that he is about to overwrite an existing file, so he can
     * choose to proceed or not.
     *
     * @return a int that contains the election made by the user.
     */
    public int overwritePopup() {
        Object[] twoOptions = {"Overwrite", "Append", "Cancel"};

        JPanel overwritePanel = new JPanel();
        overwritePanel.add(new JLabel("<html><h3>You are about to load a list of contacts</h3>What do you want to do with the contacts already " +
                "in the table?<br>If you want to keep them click Append, otherwise select<br>Overwrite to remove them and load your list.<html>"));

        int result = JOptionPane.showOptionDialog(null, overwritePanel, "Load Contacts",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, twoOptions, null);

        return result;
    }

    /**
     * This method draws an alert message telling the user that the current form can't be empty on any fields.
     */
    public void emptyFormPopup() {
        JOptionPane.showMessageDialog(null, "You have one or more empty fields,\nplease input all the required information.");
    }

    /**
     * This method draws an alert message informing the user that he has unsaved changes, so he can't save them before
     * closing the program.
     *
     * @return a int that contains the election made by the user.
     */
    public int unsavedChanges() {
        Object[] threeOptions = {"Save", "Don't save", "Cancel"};

        JPanel overwritePanel = new JPanel();
        overwritePanel.add(new JLabel("Do you want to save the current contact list?"));

        return JOptionPane.showOptionDialog(null, overwritePanel, "Save Contacts",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, threeOptions, null);
    }


    /**
     * Gets the value of the frame variable.
     *
     * @return a j frame type that contains
     */
//Accessor and Mutator methods
    public JFrame getFrame() {
        return frame;
    }

    /**
     * Sets the value in the frame variable.
     *
     * @param frame contains the frame value.
     */
    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    /**
     * Gets the value of the root panel variable.
     *
     * @return a j panel type that contains
     */
    public JPanel getRootPanel() {
        return rootPanel;
    }

    /**
     * Sets the value in the root panel variable.
     *
     * @param rootPanel contains the root panel value.
     */
    public void setRootPanel(JPanel rootPanel) {
        this.rootPanel = rootPanel;
    }

    /**
     * Gets the value of the left panel variable.
     *
     * @return a j panel type that contains
     */
    public JPanel getLeftPanel() {
        return leftPanel;
    }

    /**
     * Sets the value in the left panel variable.
     *
     * @param leftPanel contains the left panel value.
     */
    public void setLeftPanel(JPanel leftPanel) {
        this.leftPanel = leftPanel;
    }

    /**
     * Gets the value of the right panel variable.
     *
     * @return a j panel type that contains
     */
    public JPanel getRightPanel() {
        return rightPanel;
    }

    /**
     * Sets the value in the right panel variable.
     *
     * @param rightPanel contains the right panel value.
     */
    public void setRightPanel(JPanel rightPanel) {
        this.rightPanel = rightPanel;
    }

    /**
     * Gets the value of the contact label variable.
     *
     * @return a j label type that contains
     */
    public JLabel getContactLabel() {
        return contactLabel;
    }

    /**
     * Sets the value in the contact label variable.
     *
     * @param contactLabel contains the contact label value.
     */
    public void setContactLabel(JLabel contactLabel) {
        this.contactLabel = contactLabel;
    }

    /**
     * Gets the value of the data scroll pane variable.
     *
     * @return a j scroll pane type that contains
     */
    public JScrollPane getDataScrollPane() {
        return dataScrollPane;
    }

    /**
     * Sets the value in the data scroll pane variable.
     *
     * @param dataScrollPane contains the data scroll pane value.
     */
    public void setDataScrollPane(JScrollPane dataScrollPane) {
        this.dataScrollPane = dataScrollPane;
    }

    /**
     * Gets the value of the data table variable.
     *
     * @return a j table type that contains
     */
    public JTable getDataTable() {
        return dataTable;
    }

    /**
     * Sets the value in the data table variable.
     *
     * @param dataTable contains the data table value.
     */
    public void setDataTable(JTable dataTable) {
        this.dataTable = dataTable;
    }

    /**
     * Gets the value of the search txt field variable.
     *
     * @return a j text field type that contains
     */
    public JTextField getSearchTxtField() {
        return searchTxtField;
    }

    /**
     * Sets the value in the search txt field variable.
     *
     * @param searchTxtField contains the search txt field value.
     */
    public void setSearchTxtField(JTextField searchTxtField) {
        this.searchTxtField = searchTxtField;
    }

    /**
     * Gets the value of the clear button variable.
     *
     * @return a j button type that contains
     */
    public JButton getClearButton() {
        return clearButton;
    }

    /**
     * Sets the value in the clear button variable.
     *
     * @param clearButton contains the clear button value.
     */
    public void setClearButton(JButton clearButton) {
        this.clearButton = clearButton;
    }

    /**
     * Gets the value of the name text variable.
     *
     * @return a j text pane type that contains
     */
    public JTextPane getNameText() {
        return nameText;
    }

    /**
     * Sets the value in the name text variable.
     *
     * @param nameText contains the name text value.
     */
    public void setNameText(JTextPane nameText) {
        this.nameText = nameText;
    }

    /**
     * Gets the value of the phone label variable.
     *
     * @return a j label type that contains
     */
    public JLabel getPhoneLabel() {
        return phoneLabel;
    }

    /**
     * Sets the value in the phone label variable.
     *
     * @param phoneLabel contains the phone label value.
     */
    public void setPhoneLabel(JLabel phoneLabel) {
        this.phoneLabel = phoneLabel;
    }

    /**
     * Gets the value of the mail label variable.
     *
     * @return a j label type that contains
     */
    public JLabel getMailLabel() {
        return mailLabel;
    }

    /**
     * Sets the value in the mail label variable.
     *
     * @param mailLabel contains the mail label value.
     */
    public void setMailLabel(JLabel mailLabel) {
        this.mailLabel = mailLabel;
    }

    /**
     * Gets the value of the address label variable.
     *
     * @return a j label type that contains
     */
    public JLabel getAddressLabel() {
        return addressLabel;
    }

    /**
     * Sets the value in the address label variable.
     *
     * @param addressLabel contains the address label value.
     */
    public void setAddressLabel(JLabel addressLabel) {
        this.addressLabel = addressLabel;
    }

    /**
     * Gets the value of the phone text variable.
     *
     * @return a j text pane type that contains
     */
    public JTextPane getPhoneText() {
        return phoneText;
    }

    /**
     * Sets the value in the phone text variable.
     *
     * @param phoneText contains the phone text value.
     */
    public void setPhoneText(JTextPane phoneText) {
        this.phoneText = phoneText;
    }

    /**
     * Gets the value of the mail text variable.
     *
     * @return a j text pane type that contains
     */
    public JTextPane getMailText() {
        return mailText;
    }

    /**
     * Sets the value in the mail text variable.
     *
     * @param mailText contains the mail text value.
     */
    public void setMailText(JTextPane mailText) {
        this.mailText = mailText;
    }

    /**
     * Gets the value of the address text variable.
     *
     * @return a j text pane type that contains
     */
    public JTextPane getAddressText() {
        return addressText;
    }

    /**
     * Sets the value in the address text variable.
     *
     * @param addressText contains the address text value.
     */
    public void setAddressText(JTextPane addressText) {
        this.addressText = addressText;
    }

    /**
     * Gets the value of the new contact btn variable.
     *
     * @return a j button type that contains
     */
    public JButton getNewContactBtn() {
        return newContactBtn;
    }

    /**
     * Sets the value in the new contact btn variable.
     *
     * @param newContactBtn contains the new contact btn value.
     */
    public void setNewContactBtn(JButton newContactBtn) {
        this.newContactBtn = newContactBtn;
    }

    /**
     * Gets the value of the edit contact btn variable.
     *
     * @return a j button type that contains
     */
    public JButton getEditContactBtn() {
        return editContactBtn;
    }

    /**
     * Sets the value in the edit contact btn variable.
     *
     * @param editContactBtn contains the edit contact btn value.
     */
    public void setEditContactBtn(JButton editContactBtn) {
        this.editContactBtn = editContactBtn;
    }

    /**
     * Gets the value of the delete contact btn variable.
     *
     * @return a j button type that contains
     */
    public JButton getDeleteContactBtn() {
        return deleteContactBtn;
    }

    /**
     * Sets the value in the delete contact btn variable.
     *
     * @param deleteContactBtn contains the delete contact btn value.
     */
    public void setDeleteContactBtn(JButton deleteContactBtn) {
        this.deleteContactBtn = deleteContactBtn;
    }

    /**
     * Gets the value of the my menu bar variable.
     *
     * @return a j menu bar type that contains
     */
    public JMenuBar getMyMenuBar() {
        return myMenuBar;
    }

    /**
     * Sets the value in the my menu bar variable.
     *
     * @param myMenuBar contains the my menu bar value.
     */
    public void setMyMenuBar(JMenuBar myMenuBar) {
        this.myMenuBar = myMenuBar;
    }

    /**
     * Gets the value of the my file menu variable.
     *
     * @return a j menu type that contains
     */
    public JMenu getMyFileMenu() {
        return myFileMenu;
    }

    /**
     * Sets the value in the my file menu variable.
     *
     * @param myFileMenu contains the my file menu value.
     */
    public void setMyFileMenu(JMenu myFileMenu) {
        this.myFileMenu = myFileMenu;
    }

    /**
     * Gets the value of the save all menu item variable.
     *
     * @return a j menu item type that contains
     */
    public JMenuItem getSaveAllMenuItem() {
        return saveAllMenuItem;
    }

    /**
     * Sets the value in the save all menu item variable.
     *
     * @param saveAllMenuItem contains the save all menu item value.
     */
    public void setSaveAllMenuItem(JMenuItem saveAllMenuItem) {
        this.saveAllMenuItem = saveAllMenuItem;
    }

    /**
     * Gets the value of the open menu item variable.
     *
     * @return a j menu item type that contains
     */
    public JMenuItem getOpenMenuItem() {
        return openMenuItem;
    }

    /**
     * Sets the value in the open menu item variable.
     *
     * @param openMenuItem contains the open menu item value.
     */
    public void setOpenMenuItem(JMenuItem openMenuItem) {
        this.openMenuItem = openMenuItem;
    }

    /**
     * Gets the value of the exit menu item variable.
     *
     * @return a j menu item type that contains
     */
    public JMenuItem getExitMenuItem() {
        return exitMenuItem;
    }

    /**
     * Sets the value in the exit menu item variable.
     *
     * @param exitMenuItem contains the exit menu item value.
     */
    public void setExitMenuItem(JMenuItem exitMenuItem) {
        this.exitMenuItem = exitMenuItem;
    }

    /**
     * Gets the value of the my options menu variable.
     *
     * @return a j menu type that contains
     */
    public JMenu getMyOptionsMenu() {
        return myOptionsMenu;
    }

    /**
     * Sets the value in the my options menu variable.
     *
     * @param myOptionsMenu contains the my options menu value.
     */
    public void setMyOptionsMenu(JMenu myOptionsMenu) {
        this.myOptionsMenu = myOptionsMenu;
    }

    /**
     * Gets the value of the new menu item variable.
     *
     * @return a j menu item type that contains
     */
    public JMenuItem getNewMenuItem() {
        return newMenuItem;
    }

    /**
     * Sets the value in the new menu item variable.
     *
     * @param newMenuItem contains the new menu item value.
     */
    public void setNewMenuItem(JMenuItem newMenuItem) {
        this.newMenuItem = newMenuItem;
    }

    /**
     * Gets the value of the edit menu item variable.
     *
     * @return a j menu item type that contains
     */
    public JMenuItem getEditMenuItem() {
        return editMenuItem;
    }

    /**
     * Sets the value in the edit menu item variable.
     *
     * @param editMenuItem contains the edit menu item value.
     */
    public void setEditMenuItem(JMenuItem editMenuItem) {
        this.editMenuItem = editMenuItem;
    }

    /**
     * Gets the value of the delete menu item variable.
     *
     * @return a j menu item type that contains
     */
    public JMenuItem getDeleteMenuItem() {
        return deleteMenuItem;
    }

    /**
     * Sets the value in the delete menu item variable.
     *
     * @param deleteMenuItem contains the delete menu item value.
     */
    public void setDeleteMenuItem(JMenuItem deleteMenuItem) {
        this.deleteMenuItem = deleteMenuItem;
    }

    /**
     * Gets the value of the clear all menu item variable.
     *
     * @return a j menu item type that contains
     */
    public JMenuItem getClearAllMenuItem() {
        return clearAllMenuItem;
    }

    /**
     * Sets the value in the clear all menu item variable.
     *
     * @param clearAllMenuItem contains the clear all menu item value.
     */
    public void setClearAllMenuItem(JMenuItem clearAllMenuItem) {
        this.clearAllMenuItem = clearAllMenuItem;
    }

    /**
     * Gets the value of the name txt field variable.
     *
     * @return a j text field type that contains
     */
    public JTextField getNameTxtField() {
        return nameTxtField;
    }

    /**
     * Sets the value in the name txt field variable.
     *
     * @param nameTxtField contains the name txt field value.
     */
    public void setNameTxtField(JTextField nameTxtField) {
        this.nameTxtField = nameTxtField;
    }

    /**
     * Gets the value of the phone txt field variable.
     *
     * @return a j text field type that contains
     */
    public JTextField getPhoneTxtField() {
        return phoneTxtField;
    }

    /**
     * Sets the value in the phone txt field variable.
     *
     * @param phoneTxtField contains the phone txt field value.
     */
    public void setPhoneTxtField(JTextField phoneTxtField) {
        this.phoneTxtField = phoneTxtField;
    }

    /**
     * Gets the value of the mail txt field variable.
     *
     * @return a j text field type that contains
     */
    public JTextField getMailTxtField() {
        return mailTxtField;
    }

    /**
     * Sets the value in the mail txt field variable.
     *
     * @param mailTxtField contains the mail txt field value.
     */
    public void setMailTxtField(JTextField mailTxtField) {
        this.mailTxtField = mailTxtField;
    }

    /**
     * Gets the value of the address txt field variable.
     *
     * @return a j text field type that contains
     */
    public JTextField getAddressTxtField() {
        return addressTxtField;
    }

    /**
     * Sets the value in the address txt field variable.
     *
     * @param addressTxtField contains the address txt field value.
     */
    public void setAddressTxtField(JTextField addressTxtField) {
        this.addressTxtField = addressTxtField;
    }

    /**
     * Gets the value of the form panel variable.
     *
     * @return a j panel type that contains
     */
    public JPanel getFormPanel() {
        return formPanel;
    }

    /**
     * Sets the value in the form panel variable.
     *
     * @param formPanel contains the form panel value.
     */
    public void setFormPanel(JPanel formPanel) {
        this.formPanel = formPanel;
    }
}
