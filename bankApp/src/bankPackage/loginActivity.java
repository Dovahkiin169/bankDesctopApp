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

    public JLabel labelUsername,labelPassword;
    public JTextField textUsername;
    public JPasswordField fieldPassword;
    public JButton buttonLogin;
    public JCheckBox chinButton;
    emailValidator EV = new emailValidator();

    public loginActivity()  {
        super("BankApp");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        labelUsername = new JLabel("Enter email: ");
        labelPassword = new JLabel("Enter password: ");
        textUsername = new JTextField(20);
        fieldPassword = new JPasswordField(20);
        buttonLogin = new JButton("Login");
        chinButton = new JCheckBox("as Employee");

        chinButton.setMnemonic(KeyEvent.VK_C);
        chinButton.setSelected(true);

        JPanel newPanel = new JPanel(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.anchor = GridBagConstraints.WEST;
        constraints.insets = new Insets(10, 10, 10, 10);

        constraints.gridx = 0;
        constraints.gridy = 0;
        newPanel.add(labelUsername, constraints);

        constraints.gridx = 1;
        newPanel.add(textUsername, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        newPanel.add(labelPassword, constraints);

        constraints.gridx = 1;
        newPanel.add(fieldPassword, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        newPanel.add(chinButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        newPanel.add(buttonLogin, constraints);

        buttonLogin.addActionListener(this);

        newPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Login Panel"));

        add(newPanel);
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

        if (source == buttonLogin && chinButton.isSelected() && canLogin()) {
            LoginOperation("worker");
            System.out.println("Login Employee");
        }
        else if(source == buttonLogin && !chinButton.isSelected() && canLogin()) {
            LoginOperation("user");
            System.out.println("Login User");
        }
    }

    public boolean canLogin() {
        if (EV.validate(textUsername.getText())) {
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
            ResultSet rs=stmt.executeQuery("select "+login+" from "+table+" where "+email_address+" = '"+textUsername.getText()+"'AND "+password+" = MD5('"+ String.valueOf(fieldPassword.getPassword()) +"');");
            while(rs.next())
                System.out.println(rs.getInt(1));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }

}