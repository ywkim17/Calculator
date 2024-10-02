import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    private static double num1 = 0;
    private static double num2 = 0;
    private static double result = 0;
    private static String currentExpression = "";

    public static enum Mode {
        FIRST,
        OPERATOR,
        SECOND
    }

    public static enum Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        NONE
    }

    static Mode mode = Mode.FIRST;
    static Operator operator = Operator.NONE;

    static JFrame frame = new JFrame();
    static JLabel label = new JLabel("0", SwingConstants.CENTER);

    public static void main(String[] args) {
        // Create frame
        frame.setLayout(new FlowLayout());
        frame.setSize(250, 350);

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
        JButton clear = new JButton("C");
        JButton ce = new JButton("CE");
        JButton squareRoot = new JButton("√");
        JButton square = new JButton("x²");

        // Set sizes of symbol buttons
        addition.setPreferredSize(new Dimension(50, 50));
        subtraction.setPreferredSize(new Dimension(50, 50));
        multiplication.setPreferredSize(new Dimension(50, 50));
        division.setPreferredSize(new Dimension(50, 50));
        decimal.setPreferredSize(new Dimension(50, 50));
        equals.setPreferredSize(new Dimension(50, 50));
        clear.setPreferredSize(new Dimension(50, 50));
        ce .setPreferredSize(new Dimension(50,50));
        squareRoot.setPreferredSize(new Dimension(50,50));
        square.setPreferredSize(new Dimension(50, 50));

        // Initialize output log
        label.setPreferredSize(new Dimension(280, 40));

        // Add components to the frame
        frame.getContentPane().add(label);
        frame.getContentPane().add(clear);
        frame.getContentPane().add(ce);
        frame.getContentPane().add(squareRoot);
        frame.getContentPane().add(square);
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

        // Show number on output log when the number is clicked
        for(int i = 0; i < 10; i++) {
            final int n = i;
            numberButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if(mode == Mode.FIRST) {
                        num1 = num1 * 10 + n;
                    } else if (mode == Mode.OPERATOR) {
                        num2 = n;
                        mode = Mode.SECOND;
                    } else if (mode == Mode.SECOND) {
                        //second
                        num2 = num2 * 10 + n;
                    }
                    updateDisplay();
                }
            });
        }

        // Show addition sign on output log when the button is clicked
        addition.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    mode = Mode.OPERATOR;
                    operator = Operator.ADD;
                    updateDisplay();
                } else if(mode == Mode.OPERATOR) {
                    label.setText("Error");
                }
            }
        });

        // Show subtraction sign on output log when the button is clicked
        subtraction.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    mode = Mode.OPERATOR;
                    operator = Operator.SUBTRACT;
                    updateDisplay();
                } else if(mode == Mode.OPERATOR) {
                    label.setText("Error");
                }
            }
        });

        // Show multiplication sign on output log when the button is clicked
        multiplication.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    mode = Mode.OPERATOR;
                    operator = Operator.MULTIPLY;
                    updateDisplay();
                } else if(mode == Mode.OPERATOR) {
                    label.setText("Error");
                }
            }
        });

        // Show division sign on output log when the button is clicked
        division.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    mode = Mode.OPERATOR;
                    operator = Operator.DIVIDE;
                    updateDisplay();
                } else if(mode == Mode.OPERATOR) {
                    label.setText("Error");
                }
            }
        });
        
        // Calculates the operation of the two numbers
        equals.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode != Mode.SECOND || operator == Operator.NONE) {
                    label.setText("Error");
                    return;
                }
                if(operator == Operator.DIVIDE && num2 == 0) {
                    label.setText("Error");
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
                mode = Mode.FIRST;
                operator = Operator.NONE;
                num1 = result;
                num2 = 0;
                updateDisplay();
            }
        });

        // Clear button function -- clears the entire entry
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    mode = Mode.FIRST;
                    operator = Operator.NONE;
                    num1 = 0;
                    num2 = 0;
                    result = 0;
                    updateDisplay();
            }
        });

        // CE button function -- clears the most recent entry
        ce.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    num1 = 0;
                } else if(mode == Mode.SECOND) {
                    num2 = 0;
                    mode = Mode.FIRST;
                }
                updateDisplay();
            }
        });

        // Show the frame and freeze size
        frame.setResizable(false);
        frame.setVisible(true);
    }

    // Update function
    public static void updateDisplay() {
        String op = "";    
        if(operator == Operator.ADD ) {
            op = " + ";
        } else if(operator == Operator.SUBTRACT) {
            op = " - ";
        } else if(operator == Operator.MULTIPLY) {
            op = " x ";
        } else if(operator == Operator.DIVIDE) {
            op = " ÷ ";
        }

        String number1 = num1 + "";
        String number2 = num2 + "";
        number1 = number1.replace(".0", "");
        number2 = number2.replace(".0", "");
        if(mode == Mode.FIRST) {
            currentExpression = number1;
        } else if(mode == Mode.SECOND) {
            currentExpression = number1 + op + number2;
        } else if(mode == Mode.OPERATOR) {
            currentExpression = number1 + op;
        }
        label.setText(currentExpression);
   }
}
