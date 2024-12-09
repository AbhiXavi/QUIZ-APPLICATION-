package quiz.application;

import java.awt.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
public class Register extends JFrame implements ActionListener {
    JButton register, back;
    JTextField tfname, tfemail;
    JPasswordField pfpassword, pfconfirmPassword;
    JLabel errorLabel;

    Register() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int x = (screenSize.width - 1200) / 2;
        int y = (screenSize.height - 650) / 2;
        setLocation(x, y);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.white);
        setLayout(null);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/quizpic.jpg"));
        JLabel image = new JLabel(i1);
        image.setBounds(0, 0, 600, 700);
        add(image);

        JLabel heading = new JLabel("Quiz UP!");
        heading.setBounds(800, 40, 300, 45);
        heading.setFont(new Font("Snap ITC", Font.BOLD, 35));
        heading.setForeground(Color.black);
        add(heading);

        JLabel name = new JLabel("Enter your name");
        name.setBounds(700, 120, 300, 30);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 25));
        name.setForeground(Color.black);
        add(name);

        tfname = new JTextField();
        tfname.setBounds(700, 150, 425, 30);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);

        JLabel email = new JLabel("Enter your email");
        email.setBounds(700, 220, 300, 30);
        email.setFont(new Font("Mongolian Baiti", Font.BOLD, 25));
        email.setForeground(Color.black);
        add(email);

        tfemail = new JTextField();
        tfemail.setBounds(700, 250, 425, 30);
        tfemail.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfemail);

        JLabel password = new JLabel("Enter your password");
        password.setBounds(700, 320, 300, 30);
        password.setFont(new Font("Mongolian Baiti", Font.BOLD, 25));
        password.setForeground(Color.black);
        add(password);

        pfpassword = new JPasswordField();
        pfpassword.setBounds(700, 350, 425, 30);
        pfpassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(pfpassword);

        JLabel confirmPassword = new JLabel("Confirm your password");
        confirmPassword.setBounds(700, 420, 300, 30);
        confirmPassword.setFont(new Font("Mongolian Baiti", Font.BOLD, 25));
        confirmPassword.setForeground(Color.black);
        add(confirmPassword);

        pfconfirmPassword = new JPasswordField();
        pfconfirmPassword.setBounds(700, 450, 425, 30);
        pfconfirmPassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(pfconfirmPassword);

        errorLabel = new JLabel("");
        errorLabel.setBounds(700, 480, 425, 30);
        errorLabel.setFont(new Font("Times New Roman", Font.BOLD, 16));
        errorLabel.setForeground(Color.red);
        add(errorLabel);

        register = new JButton("Register");
        register.setBounds(745, 520, 120, 35);
        register.setBackground(new Color(30, 144, 254));
        register.setForeground(Color.white);
        register.addActionListener(this);
        register.setEnabled(false); // Button initially disabled
        register.setFocusable(false); // Disable focus
        add(register);

        back = new JButton("Close");
        back.setBounds(935, 520, 120, 35);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icons/questionmark.jpg"));
        JLabel image1 = new JLabel(i2);
        image1.setBounds(600, 0, 600, 700);
        add(image1);
        setResizable(false);
        setSize(1200, 650);
        setVisible(true);

        // Add listener to text fields for enabling/disabling the "Register" button
        // and showing error message if passwords don't match
        tfname.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                checkPasswordsMatch();
            }
        });

        tfemail.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                checkPasswordsMatch();
            }
        });

        pfpassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                checkPasswordsMatch();
            }
        });

        pfconfirmPassword.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                update();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                update();
            }

            public void update() {
                checkPasswordsMatch();
            }
        });
    }

    private void checkPasswordsMatch() {
        if (!String.valueOf(pfpassword.getPassword()).equals(String.valueOf(pfconfirmPassword.getPassword()))) {
            errorLabel.setText("Passwords do not match");
            register.setEnabled(false);
        } else {
            errorLabel.setText("");
            if (!tfname.getText().trim().isEmpty() && !tfemail.getText().trim().isEmpty()
                    && pfpassword.getPassword().length > 0 && pfconfirmPassword.getPassword().length > 0) {
                register.setEnabled(true);
            }
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == register) {
            String name = tfname.getText();
            String email = tfemail.getText();
            String password = String.valueOf(pfpassword.getPassword());

            // Save the user details in the database
            saveUserDetails(name, email, password);

            // Show a popup message
            JOptionPane.showMessageDialog(this, "Registered successfully. Go to login page.");

            // Close the current window and open the login page
            new Login(); // Assuming Login.java is your login page
            setVisible(false);
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
    }

    private void saveUserDetails(String name, String email, String password) {
        // Your database connection code here
        String url = "jdbc:mysql://localhost:3306/quiz";
        String username = "root";
        String dbpassword = "frgrabsa@1407";

        try (Connection conn = DriverManager.getConnection(url, username, dbpassword);
             Statement stmt = conn.createStatement()) {

            String insertUserSQL = "INSERT INTO user (name, email, password) VALUES ('" + name + "', '" + email + "', '" + password + "')";
            stmt.executeUpdate(insertUserSQL);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        new Register();
    }
}
