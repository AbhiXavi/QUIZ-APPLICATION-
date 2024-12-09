package quiz.application;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Score extends JFrame implements ActionListener {

    Score(String name, int score) {
        setBounds(300, 50, 950, 750);
        setLayout(null);

        // Load background image
        try {
            BufferedImage image = ImageIO.read(getClass().getResourceAsStream("background2.jpg"));
            ImagePanel background = new ImagePanel(image);
            background.setBounds(0, 0, 950, 750);
            add(background); // Add background panel to JFrame
            background.setLayout(null); // Set layout to null for absolute positioning of components

            JLabel heading = new JLabel("Thank you for attempting the Quiz");
            heading.setBounds(200, 190, 700, 50);
            heading.setForeground(Color.WHITE);
            heading.setFont(new Font("Kristen ITC", Font.PLAIN, 35));
            background.add(heading); // Add heading to background panel

            String performance = "";
            if (score == 0) {
                performance = "Your performance is below average";
            } else if (score >= 10 && score <= 30) {
                performance = "Your performance is average";
            } else if (score >= 40 && score <= 60) {
                performance = "Your performance is good";
            } else if (score >= 70 && score <= 80) {
                performance = "Your performance is very good";
            } else if (score >= 90 && score <= 100) {
                performance = "Your performance is excellent";
            }

            JLabel performanceLabel = new JLabel(performance);
            performanceLabel.setBounds(310, 270, 500, 30);
            performanceLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
            performanceLabel.setForeground(Color.WHITE);
            background.add(performanceLabel); // Add performance label to background panel

            JLabel ascore = new JLabel("Your score is " + score);
            ascore.setBounds(360, 340, 300, 30);
            ascore.setFont(new Font("Tahoma", Font.BOLD, 30));
            ascore.setForeground(Color.WHITE);
            background.add(ascore); // Add score label to background panel

            JButton submit = new JButton("Attempt Again");
            submit.setBounds(400, 400, 120, 40);
            submit.setBackground(new Color(30, 144, 255));
            submit.setForeground(Color.WHITE);
            submit.addActionListener(this);
            background.add(submit); // Add button to background panel
            BufferedImage quizImage = null;
try {
    quizImage = ImageIO.read(getClass().getResourceAsStream("quiz.png"));
} catch (IOException e) {
    e.printStackTrace();
    System.out.println("Failed to load quiz image!");
}

if (quizImage != null) {
    JLabel quizLabel = new JLabel(new ImageIcon(quizImage));
    quizLabel.setBounds(390, 420, 550, 260); // Adjust the bounds as needed
    background.add(quizLabel);
}

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failed to load background image!");
        }

        setResizable(false); // Fix window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        
    }

    public void actionPerformed(ActionEvent ae) {
        setVisible(false);
        new Login();
    }

    public static void main(String[] args) {
        new Score("User", 0);
    }

    // Custom JPanel to hold components and draw background image
    class ImagePanel extends JPanel {
        private BufferedImage image;

        public ImagePanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}
