package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Rules extends JFrame implements ActionListener, AdjustmentListener {
    String name;
    JButton start, back;
    JCheckBox termsCheckBox;
    JScrollPane scrollPane;
    JScrollBar scrollBar;

    // Custom JPanel to hold components and draw background image
    class ImagePanel extends JPanel {
        private BufferedImage image;

        public ImagePanel() {
            try {
                image = ImageIO.read(getClass().getResourceAsStream("background1.jpg"));
                System.out.println("Image loaded successfully!");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Failed to load image!");
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public Rules(String name) {
        getContentPane().setBackground(Color.white);
        setLayout(null);

        JPanel panel = new ImagePanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 800, 650);
        add(panel);

        JLabel heading = new JLabel("Welcome " + name + " to QUIZ UP!");
        heading.setBounds(70, 20, 700, 30);
        heading.setFont(new Font("Snap ITC", Font.BOLD, 35));
        heading.setForeground(Color.DARK_GRAY);
        panel.add(heading);

        JLabel rules = new JLabel();
        rules.setFont(new Font("Tahoma", Font.PLAIN, 16));
        rules.setText(
                "<html>" +
                        "<div style='text-align: center;'><b><b>Instructions</b></b></div><br>" +
                        "1. Do not close the tab without attempting all the questions." + "<br><br>" +
                        "2. All the questions are compulsory." + "<br><br>" +
                        "3. All are MCQ pattern questions." + "<br><br>" +
                        "4. You can select only one option from each question." + "<br><br>" +
                        "5. Each question is of 5 marks." + "<br><br>" +
                        "6. Each question has a time limit of 60 seconds." + "<br><br>" +
                        "7. Click on Next to move to the next question." + "<br><br>" +
                        "8. Click on Submit at the end after completing the exam." + "<br><br>" +
                        "9. Make sure to answer all questions before submitting." + "<br><br>" +
                        "10. The timer will start as soon as you begin the quiz." + "<br><br>" +
                        "11. You cannot go back to a previous question once you have moved forward." + "<br><br>" +
                        "12. The final score will be displayed at the end of the quiz." + "<br><br>" +
                        "</html>"
        );
        rules.setBounds(20, 90, 700, 350);
        panel.add(rules);

        scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 90, 700, 350);
        scrollPane.setViewportView(rules);
        panel.add(scrollPane);
        scrollBar = scrollPane.getVerticalScrollBar();
        scrollBar.addAdjustmentListener(this);

        termsCheckBox = new JCheckBox("I have gone through the instructions, understood and will hereby follow the same.");
        termsCheckBox.setBounds(50, 450, 500, 30);
        termsCheckBox.setEnabled(false); // Initially disabled
        termsCheckBox.addActionListener(this);
        panel.add(termsCheckBox);

        back = new JButton("Back");
        back.setBounds(250, 500, 100, 30);
        back.setBackground(new Color(30, 144, 254));
        back.setForeground(Color.white);
        back.addActionListener(this);
        panel.add(back);

        start = new JButton("Start Quiz");
        start.setBounds(400, 500, 100, 30);
        start.setBackground(new Color(30, 144, 254));
        start.setForeground(Color.white);
        start.setEnabled(false); // Initially disabled
        start.addActionListener(this);
        panel.add(start);

        setSize(800, 650);
        setResizable(false); // Fix window size

        // Calculate the center coordinates of the screen
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int centerX = (int) ((screenSize.getWidth() - getWidth()) / 2);
        int centerY = (int) ((screenSize.getHeight() - getHeight()) / 2);

        // Set the frame's location to the center coordinates
        setLocation(centerX, centerY);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == start) {
            setVisible(false);
            new Quiz(name);
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Login();
        } else if (ae.getSource() == termsCheckBox) {
            start.setEnabled(termsCheckBox.isSelected() && scrollBar.getValue() == scrollBar.getMaximum() - scrollBar.getVisibleAmount());
        }
    }

    public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getSource() == scrollBar) {
            termsCheckBox.setEnabled(scrollBar.getValue() == scrollBar.getMaximum() - scrollBar.getVisibleAmount());
            start.setEnabled(termsCheckBox.isSelected() && scrollBar.getValue() == scrollBar.getMaximum() - scrollBar.getVisibleAmount());
        }
    }

    public static void main(String[] args) {
        new Rules("User");
    }
}
