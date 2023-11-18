import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GuessGUI extends JFrame {
    private int maxtrials = 3;
    private int attempts = 0;
    private int randomNumber;
    private JLabel instructionLabel;
    private JTextField inputField;
    private JButton submitButton;
    private JLabel resultLabel;

    public GuessGUI() {
        setTitle("Number Guessing Game");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        instructionLabel = new JLabel("Enter a number between 0 and 9:");
        inputField = new JTextField(10);b
        submitButton = new JButton("Submit");
        resultLabel = new JLabel("");

        add(instructionLabel);
        add(inputField);
        add(submitButton);
        add(resultLabel);

        randomNumber = generateRandomNumber();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int guess = Integer.parseInt(inputField.getText());
                checkGuess(guess);
            }
        });
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt(10);
    }

    private void checkGuess(int guess) {
        if (guess == randomNumber) {
            resultLabel.setText("You win!");
            submitButton.setEnabled(false);
        } else {
            attempts++;
            if (attempts < maxtrials) {
                resultLabel.setText("Incorrect guess. " + (maxtrials - attempts) + " attempts left.");
            } else {
                resultLabel.setText("Out of attempts. The correct number was " + randomNumber + ".");
                submitButton.setEnabled(false);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                GuessGUI game = new GuessGUI();
                game.setVisible(true);
            }
        });
    }
}
