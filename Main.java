import javax.swing.*;
import java.awt.*;

public class Main {
    private static double num1 = 0;
    private static double num2 = 0;
    private static String operator = "";
    private static boolean isOperatorPressed = false;

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(250, 300);

        // Initialize number buttons
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton((i + ""));
            numberButtons[i].setPreferredSize(new Dimension(50, 50));
        }

        // Constructors of symbol buttons
        JButton addition = new JButton("+");
        JButton subtraction = new JButton("-");
        JButton multiplication = new JButton("x");
        JButton division = new JButton("÷");
        JButton decimal = new JButton(".");
        JButton equals = new JButton("=");

        // Set sizes of symbol buttons
        addition.setPreferredSize(new Dimension(50, 50));
        subtraction.setPreferredSize(new Dimension(50, 50));
        multiplication.setPreferredSize(new Dimension(50, 50));
        division.setPreferredSize(new Dimension(50, 50));
        decimal.setPreferredSize(new Dimension(50, 50));
        equals.setPreferredSize(new Dimension(50, 50));

        // Initialize output log
        JLabel l = new JLabel("0", SwingConstants.CENTER);
        l.setPreferredSize(new Dimension(280, 40));

        // Add number buttons action listeners
        for (int i = 0; i < 10; i++) {
            int finalI = i; 
            numberButtons[i].addActionListener(e -> {
                if (isOperatorPressed) {
                    l.setText(String.valueOf(finalI));
                    isOperatorPressed = false;
                } else {
                    l.setText(l.getText().equals("0") ? String.valueOf(finalI) : l.getText() + finalI);
                }
            });
        }

        // Add action listener for decimal button
        decimal.addActionListener(e -> {
            if (!l.getText().contains(".")) {
                l.setText(l.getText() + ".");
            }
        });

        // Add action listener for operator buttons
        addition.addActionListener(e -> setOperator("+", l));
        subtraction.addActionListener(e -> setOperator("-", l));
        multiplication.addActionListener(e -> setOperator("x", l));
        division.addActionListener(e -> setOperator("÷", l));

        // Add action listener for equals button
        equals.addActionListener(e -> calculate(l));

        // Add components to the frame
        frame.getContentPane().add(l);
        frame.getContentPane().add(numberButtons[7]);
        frame.getContentPane().add(numberButtons[8]);
        frame.getContentPane().add(numberButtons[9]);
        frame.getContentPane().add(division);
        frame.getContentPane().add(numberButtons[4]);
        frame.getContentPane().add(numberButtons[5]);
        frame.getContentPane().add(numberButtons[6]);
        frame.getContentPane().add(multiplication);
        frame.getContentPane().add(numberButtons[1]);
        frame.getContentPane().add(numberButtons[2]);
        frame.getContentPane().add(numberButtons[3]);
        frame.getContentPane().add(subtraction);
        frame.getContentPane().add(numberButtons[0]);
        frame.getContentPane().add(decimal);
        frame.getContentPane().add(equals);
        frame.getContentPane().add(addition);

        // Show the frame and freeze size
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Method to set operator and store the first number
    private static void setOperator(String op, JLabel label) {
        num1 = Double.parseDouble(label.getText());
        operator = op;
        isOperatorPressed = true;
    }

    // Method to perform the calculation when equals button is pressed
    private static void calculate(JLabel label) {
        num2 = Double.parseDouble(label.getText());
        double result = 0;

        switch (operator) {
            case "+" -> result = num1 + num2;
            case "-" -> result = num1 - num2;
            case "x" -> result = num1 * num2;
            case "÷" -> result = num1 / num2;
        }

        label.setText(String.valueOf(result));
    }
}
