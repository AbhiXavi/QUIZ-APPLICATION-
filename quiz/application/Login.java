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
import java.sql.ResultSet;
public class Login extends JFrame implements ActionListener {
    JButton rules, back, register;
    JTextField tfname;
    JPasswordField pfpassword;

    Login() {
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
        heading.setBounds(800, 60, 300, 45);
        heading.setFont(new Font("Snap ITC", Font.BOLD, 35));
        heading.setForeground(Color.black);
        add(heading);

        JLabel name = new JLabel("Enter your name");
        name.setBounds(700, 150, 300, 30);
        name.setFont(new Font("Mongolian Baiti", Font.BOLD, 25));
        name.setForeground(Color.black);
        add(name);

        tfname = new JTextField();
        tfname.setBounds(700, 200, 425, 30);
        tfname.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(tfname);

        JLabel password = new JLabel("Enter your password");
        password.setBounds(700, 250, 300, 30);
        password.setFont(new Font("Mongolian Baiti", Font.BOLD, 25));
        password.setForeground(Color.black);
        add(password);

        pfpassword = new JPasswordField();
        pfpassword.setBounds(700, 300, 425, 30);
        pfpassword.setFont(new Font("Times New Roman", Font.BOLD, 20));
        add(pfpassword);

        rules = new JButton("Next");
        rules.setBounds(745, 370, 120, 35);
        rules.setBackground(new Color(30, 144, 254));
        rules.setForeground(Color.white);
        rules.addActionListener(this);
        rules.setEnabled(false); 
        rules.setFocusable(false); 
        add(rules);

        back = new JButton("Close");
        back.setBounds(935, 370, 120, 35);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.white);
        back.addActionListener(this);
        add(back);
        
        register = new JButton("Register");
        register.setBounds(745, 470, 320, 35);
        register.setBackground(new Color(30, 144, 254));
        register.setForeground(Color.white);
        register.addActionListener(this);
        add(register);

        ImageIcon i2 = new ImageIcon(ClassLoader.getSystemResource("icons/questionmark.jpg"));
        JLabel image1 = new JLabel(i2);
        image1.setBounds(600, 0, 600, 700);
        add(image1);
        setResizable(false); 
        setSize(1200, 650);
        setVisible(true);

        // Add listener to text fields for enabling/disabling the "Next" button
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
                if (tfname.getText().trim().isEmpty() || pfpassword.getPassword().length == 0) {
                    rules.setEnabled(false);
                } else {
                    rules.setEnabled(true);
                }
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
                if (tfname.getText().trim().isEmpty() || pfpassword.getPassword().length == 0) {
                    rules.setEnabled(false);
                } else {
                    rules.setEnabled(true);
                }
            }
        });
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == rules) {
            String name = tfname.getText();
            String password = String.valueOf(pfpassword.getPassword());

            // Check if the user exists in the database
            if (checkUserExists(name, password)) {
                setVisible(false);
                new Rules(name); // Assuming Rules.java is the next JFrame
            } else {
                JOptionPane.showMessageDialog(this, "Username or password is incorrect.");
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
        }
        else if(ae.getSource() == register) {
            new Register();
            setVisible(false);
        }
    }

    private boolean checkUserExists(String name, String password) {
        String url = "jdbc:mysql://localhost:3306/quiz";
        String username = "root";
        String dbPassword = "frgrabsa@1407";

        try (Connection conn = DriverManager.getConnection(url, username, dbPassword);
             Statement stmt = conn.createStatement()) {

            String checkUserSQL = "SELECT * FROM user WHERE name='" + name + "' AND password='" + password + "'";
            ResultSet rs = stmt.executeQuery(checkUserSQL);

            return rs.next(); 

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {
        new Login();
    }
}
