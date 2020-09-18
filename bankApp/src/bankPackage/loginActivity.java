package bankPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.List;


public class loginActivity extends JFrame implements ActionListener {

    JPanel loginPanel;
    public JLabel emailLabel, passwordLabel;
    public JTextField emailTextField;
    public JPasswordField fieldPassword;
    public JButton loginButton;
    public JCheckBox checkBoxEmployee;

    JPanel registerPanel;
    public JLabel emailLabelR, passwordLabelR, peselLabelR, nameLabelR, name2LabelR,surnameLabelR, sexLabelR, birthDateLabelR,AddressLabelR, yearLabelR, monthLabelR, dayLabelR;
    public JTextField emailTextFieldR, passwordTextFieldR, peselTextFieldR, nameTextFieldR, name2TextFieldR,surnameTextFieldR, birthDateTextFieldR,AddressTextFieldR, yearTextFieldR, monthTextFieldR, dayTextFieldR;
    String[] chooseSex = {"m","w"};
    public JComboBox sex;
    public JButton registerButton;


    emailValidator EV = new emailValidator();

    public loginActivity()  {
        super("BankApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /* Login Panel */
        /******************
        loginPanel = new JPanel(new GridBagLayout());
        emailLabel = new JLabel("Enter email: ");
        passwordLabel = new JLabel("Enter password: ");
        emailTextField = new JTextField(20);
        fieldPassword = new JPasswordField(20);
        loginButton = new JButton("Login");
        checkBoxEmployee = new JCheckBox("as Employee");

        checkBoxEmployee.setMnemonic(KeyEvent.VK_C);
        checkBoxEmployee.setSelected(false);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        loginPanel.add(emailLabel, constraints);

        constraints.gridx = 1;
        loginPanel.add(emailTextField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        loginPanel.add(passwordLabel, constraints);

        constraints.gridx = 1;
        loginPanel.add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        loginPanel.add(checkBoxEmployee, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        loginPanel.add(loginButton, constraints);

        loginButton.addActionListener(this);

        loginPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));

        add(loginPanel);
        /******************/
        /* Register Panel */
        /******************/
        registerPanel = new JPanel(new GridBagLayout());
        emailLabelR = new JLabel("Email: ");
        passwordLabelR = new JLabel("Password: ");
        peselLabelR = new JLabel("PESEL: ");
        nameLabelR = new JLabel("Name: ");
        name2LabelR = new JLabel("Second Name: ");
        surnameLabelR = new JLabel("Surname: ");
        sexLabelR = new JLabel("Sex: ");
        birthDateLabelR = new JLabel("Birth Date: ");
        AddressLabelR = new JLabel("Address: ");
        yearLabelR = new JLabel("Year: ");
        monthLabelR = new JLabel("Month: ");
        dayLabelR = new JLabel("Day: ");
        registerButton = new JButton("Register");
        sex = new JComboBox(chooseSex);

        emailTextFieldR = new JTextField(20);
        passwordTextFieldR = new JPasswordField(20);
        peselTextFieldR = new JTextField(20);
        peselTextFieldR.setDocument(new TextFieldSizeLimit(11));
        nameTextFieldR = new JTextField(20);
        name2TextFieldR = new JTextField(20);
        surnameTextFieldR = new JTextField(20);
        AddressTextFieldR = new JTextField(20);
        yearTextFieldR = new JTextField(4);
        monthTextFieldR = new JTextField(2);
        dayTextFieldR = new JTextField(2);

        GridBagConstraints constraintsRegister = new GridBagConstraints();
        constraintsRegister.anchor = GridBagConstraints.WEST;
        constraintsRegister.insets = new Insets(10, 10, 10, 10);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 0;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(emailLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(emailTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 1;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(passwordLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(passwordTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 2;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(peselLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(peselTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 3;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(nameLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(nameTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 4;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(name2LabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(name2TextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 5;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(surnameLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(surnameTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 6;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(sexLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(sex, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 7;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(birthDateLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(yearLabelR, constraintsRegister);
        constraintsRegister.insets = new Insets(0,0,0,0);
        constraintsRegister.gridx = 2;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(yearTextFieldR, constraintsRegister);
        constraintsRegister.insets = new Insets(0,0,0,0);
        constraintsRegister.gridx = 3;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(monthLabelR, constraintsRegister);
        constraintsRegister.insets = new Insets(0,0,0,0);
        constraintsRegister.gridx = 4;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(monthTextFieldR, constraintsRegister);
        constraintsRegister.insets = new Insets(0,0,0,0);
        constraintsRegister.gridx = 5;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(dayLabelR, constraintsRegister);
        constraintsRegister.insets = new Insets(0,0,0,0);
        constraintsRegister.gridx = 6;
        constraintsRegister.gridwidth = 1;
        registerPanel.add(dayTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 8;
        constraintsRegister.gridwidth = 1;
        constraintsRegister.insets = new Insets(10,10,0,0);
        registerPanel.add(AddressLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        constraintsRegister.gridwidth = 6;
        registerPanel.add(AddressTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 1;
        constraintsRegister.gridy = 9;
        constraintsRegister.gridwidth = 7;
        constraintsRegister.anchor = GridBagConstraints.CENTER;
        registerPanel.add(registerButton, constraintsRegister);

        registerButton.addActionListener(this);

        registerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Register Panel"));

        add(registerPanel);
     //   registerPanel.setVisible(false);
        /******************/
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) { ex.printStackTrace(); }

        SwingUtilities.invokeLater(() -> new loginActivity().setVisible(true));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();

        if (source == loginButton && checkBoxEmployee.isSelected() && canLogin()) {
            LoginOperation("worker");
            System.out.println("Login Employee");
        }
        else if(source == loginButton && !checkBoxEmployee.isSelected() && canLogin()) {
            LoginOperation("user");
            System.out.println("Login User");
            loginPanel.setVisible(false);
        }
        else if(source == registerButton && canRegister()) {
            RegisterOperation();
            System.out.println("register user");
        }
    }

    public boolean canLogin() {
        if (EV.validate(emailTextField.getText())) {
             if(fieldPassword.getPassword().length != 0)
                 return true;
             else {
                 JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                         "Password cannot be empty",
                         "Warning",
                         JOptionPane.WARNING_MESSAGE);
                 return false;
             }
        }
        else {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Please enter correct email address",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public boolean canRegister() {
        if (!EV.validate(emailTextFieldR.getText())) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Please enter correct email address",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(passwordTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Password cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(peselTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "PESEL cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(peselTextFieldR.getText().length() != 11) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "PESEL must be 11 numbers length",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(nameTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Name cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(surnameTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Surname cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(yearTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Year cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(monthTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Month cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(dayTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Day cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        int currentYear = Calendar.getInstance().get(Calendar.YEAR) - 16;
        if(Integer.parseInt(yearTextFieldR.getText()) > currentYear) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "You must be at least 16 to register bank account",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(Integer.parseInt(yearTextFieldR.getText()) < 1898) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "You cant be that old :)",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(Integer.parseInt(monthTextFieldR.getText()) > 12) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Incorrect value, in year only 12 month",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        YearMonth yearMonthObject = YearMonth.of(Integer.parseInt(yearTextFieldR.getText()), Integer.parseInt(monthTextFieldR.getText()));
        int daysInMonth = yearMonthObject.lengthOfMonth();
        if(Integer.parseInt(dayTextFieldR.getText()) > daysInMonth) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "In that month was only "+daysInMonth+" days",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }
        if(AddressTextFieldR.getText().equals("")) {
            JOptionPane.showMessageDialog(SwingUtilities.getWindowAncestor(this),
                    "Address cannot be empty",
                    "Warning",
                    JOptionPane.WARNING_MESSAGE);
            return false;
        }

        return true;
    }

    public void LoginOperation(String user_status) {
        try{
            String table;
            String login;
            String email_address;
            String password;
            if(user_status.equals("user")) {
                login = "login_id";
                table = "users";
                email_address = "email_address";
                password = "password";
            }
            else{// worker
                login = "login_id_e";
                table = "employes";
                email_address = "email_address_e";
                password = "password_e";
            }

            String fileName = "database.properties";
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (resource == null)
                throw new IllegalArgumentException("file not found! " + fileName);
            File file = new File(resource.toURI());
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

            Class.forName(lines.get(3));
            Connection con=DriverManager.getConnection(
                    lines.get(0),lines.get(1),lines.get(2));
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select "+login+" from "+table+" where "+email_address+" = '"+ emailTextField.getText()+"'AND "+password+" = MD5('"+ String.valueOf(fieldPassword.getPassword()) +"');");
            while(rs.next())
                System.out.println(rs.getInt(1));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
    public void RegisterOperation() {
        try{
            String fileName = "database.properties";
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(fileName);
            if (resource == null)
                throw new IllegalArgumentException("file not found! " + fileName);
            File file = new File(resource.toURI());
            List<String> lines = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);

            Class.forName(lines.get(3));
            Connection con=DriverManager.getConnection(
                    lines.get(0),lines.get(1),lines.get(2));
            Statement stmt=con.createStatement();
            passwordTextFieldR.getText();
            ResultSet rs=stmt.executeQuery("select bank.register('"+passwordTextFieldR.getText()+"','"+emailTextFieldR.getText()+"','"+peselTextFieldR.getText()+"','"+nameTextFieldR.getText()+"','"+name2TextFieldR.getText()+"','"+surnameTextFieldR.getText()+"','"+AddressTextFieldR.getText()+"','"+sex.getSelectedItem()+"','"+yearTextFieldR.getText()+"-"+monthTextFieldR.getText()+"-"+dayTextFieldR.getText()+"');");
            while(rs.next())
                System.out.println(rs.getDate(1));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

}