package bankPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.net.URL;
import java.sql.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
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
        peselTextFieldR = new JPasswordField(20);
        nameTextFieldR = new JPasswordField(20);
        name2TextFieldR = new JPasswordField(20);
        surnameTextFieldR = new JPasswordField(20);
        birthDateTextFieldR = new JPasswordField(20);
        AddressTextFieldR = new JPasswordField(20);
        yearTextFieldR = new JPasswordField(20);
        monthTextFieldR = new JPasswordField(20);
        dayTextFieldR = new JPasswordField(20);

        GridBagConstraints constraintsRegister = new GridBagConstraints();
        constraintsRegister.anchor = GridBagConstraints.WEST;
        constraintsRegister.insets = new Insets(10, 10, 10, 10);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 0;
        registerPanel.add(emailLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        registerPanel.add(emailTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 1;
        registerPanel.add(passwordLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        registerPanel.add(passwordTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 2;
        registerPanel.add(peselLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        registerPanel.add(peselTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 0;
        constraintsRegister.gridy = 3;
        registerPanel.add(nameLabelR, constraintsRegister);
        constraintsRegister.gridx = 1;
        registerPanel.add(nameTextFieldR, constraintsRegister);

        constraintsRegister.gridx = 1;
        constraintsRegister.gridy = 4;
        constraintsRegister.gridwidth = 2;
        constraintsRegister.anchor = GridBagConstraints.CENTER;
        registerPanel.add(registerButton, constraintsRegister);

        registerButton.addActionListener(this);

        registerPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Register Panel"));

        add(registerPanel);
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

}