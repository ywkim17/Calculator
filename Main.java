import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main {
    private static double num1 = 0;
    private static double num2 = 0;
    private static double result = 0;
    static enum Mode {
        FIRST,
        OPERATOR,
        SECOND
    }
    static enum Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        NONE
    }
    static Mode mode = Mode.FIRST;
    static Operator operator = Operator.NONE;

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
        JLabel label = new JLabel("0", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(280, 40));

        // Add components to the frame
        frame.getContentPane().add(label);
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

        // Show number on output log when the first number is clicked
        for(int i = 0; i < 10; i++) {
            final int n = i;
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(mode == Mode.FIRST) {
                        num1 = num1 * 10 + n;
                        label.setText(("" + num1));
                    } else if (mode == Mode.OPERATOR) {
                        num2 = n;
                        mode = Mode.SECOND;
                        label.setText(("" + num2));
                    } else {
                        //second
                        num2 = num2 * 10 + n;
                        label.setText(("" + num2));
                    }
                }
            });
        }
        
        // Show addition sign on output log when the button is clicked
        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    label.setText("+");
                    mode = Mode.OPERATOR;
                    operator = Operator.ADD;
                }
            }
        });

        // Show subtraction sign on output log when the button is clicked
        subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    label.setText("-");
                    mode = Mode.OPERATOR;
                    operator = Operator.SUBTRACT;
                }
            }
        });

        // Show multiplication sign on output log when the button is clicked
        multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    label.setText("x");
                    mode = Mode.OPERATOR;
                    operator = Operator.MULTIPLY;
                }
            }
        });

        // Show division sign on output log when the button is clicked
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    label.setText("÷");
                    mode = Mode.OPERATOR;
                    operator = Operator.DIVIDE;
                }
            }
        });
        
        // Calculates the sum of the two numbers
        equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode != Mode.SECOND || operator == Operator.NONE) {
                    label.setText("Invalid shit");
                    return;
                }
                if(operator == Operator.ADD) {
                    result = (num1 + num2);
                } else if(operator == Operator.SUBTRACT) {
                    result = (num1 - num2);
                } else if(operator == Operator.MULTIPLY) {
                    result = (num1 * num2);
                } else if(operator == Operator.DIVIDE) {
                    result = (num1 / num2);
                }
                label.setText(result+"");
                mode = Mode.FIRST;
                num1 = result;
                num2 = 0;
            }
        });

        // Show the frame and freeze size
        frame.setResizable(false);
        frame.setVisible(true);

        // need a clear button
    }
}
