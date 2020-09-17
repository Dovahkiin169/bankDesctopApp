package bankPackage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class loginActivity extends JFrame implements ActionListener {

    public JLabel labelUsername,labelPassword;
    public JTextField textUsername;
    public JPasswordField fieldPassword;
    public JButton buttonLogin;
    public JCheckBox chinButton;

    public loginActivity()  {
        super("BankApp");

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
        if (source == buttonLogin && chinButton.isSelected())
            System.out.println("Login Employee");
        else if(source == buttonLogin && !chinButton.isSelected())
            System.out.println("Login User");
    }
}