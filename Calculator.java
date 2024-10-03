import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator {
    // Fields
    private static double num1 = 0;
    private static double num2 = 0;
    private static double result = 0;
    private static String currentExpression = "";

    static Mode mode = Mode.FIRST;
    static Operator operator = Operator.NONE;
    static JFrame frame = new JFrame();
    static JLabel label = new JLabel("0", SwingConstants.CENTER);

    // Calculator states
    public static enum Mode {   
        FIRST,
        OPERATOR,
        SECOND
    }

    // Operator types
    public static enum Operator {
        ADD,
        SUBTRACT,
        MULTIPLY,
        DIVIDE,
        SQUAREROOT,
        NONE
    }

    // Main method
    public static void main(String[] args) {
        // Create frame
        frame.setLayout(new FlowLayout());
        frame.setSize(260, 430);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize number buttons
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton((i + ""));
            numberButtons[i].setPreferredSize(new Dimension(60, 60));
            numberButtons[i].setOpaque(true);
            numberButtons[i].setBorderPainted(false);
            numberButtons[i].setBackground(Color.decode("#b684d1"));
        }

        // Constructors of symbol buttons
        JButton addition = new JButton("+");
        addition.setOpaque(true);
        addition.setBorderPainted(false);
        JButton subtraction = new JButton("-");
        subtraction.setOpaque(true);
        subtraction.setBorderPainted(false);
        JButton multiplication = new JButton("x");
        multiplication.setOpaque(true);
        multiplication.setBorderPainted(false);
        JButton division = new JButton("÷");
        division.setOpaque(true);
        division.setBorderPainted(false);
        JButton decimal = new JButton(".");
        decimal.setOpaque(true);
        decimal.setBorderPainted(false);
        JButton equals = new JButton("=");
        equals.setOpaque(true);
        equals.setBorderPainted(false);
        JButton clear = new JButton("C");
        clear.setOpaque(true);
        clear.setBorderPainted(false);
        JButton ce = new JButton("CE");
        ce.setOpaque(true);
        ce.setBorderPainted(false);
        JButton squareRoot = new JButton("√");
        squareRoot.setOpaque(true);
        squareRoot.setBorderPainted(false);
        JButton square = new JButton("x²");
        square.setOpaque(true);
        square.setBorderPainted(false);

        // Set sizes of symbol buttons
        addition.setPreferredSize(new Dimension(60, 60));
        subtraction.setPreferredSize(new Dimension(60, 60));
        multiplication.setPreferredSize(new Dimension(60, 60));
        division.setPreferredSize(new Dimension(60, 60));
        decimal.setPreferredSize(new Dimension(60, 60));
        equals.setPreferredSize(new Dimension(60, 60));
        clear.setPreferredSize(new Dimension(60, 60));
        ce .setPreferredSize(new Dimension(60,60));
        squareRoot.setPreferredSize(new Dimension(60,60));
        square.setPreferredSize(new Dimension(60, 60));

        // Set colors for each operation button
        addition.setBackground(Color.decode("#74b8b8"));
        subtraction.setBackground(Color.decode("#74b8b8"));
        multiplication.setBackground(Color.decode("#74b8b8"));
        division.setBackground(Color.decode("#74b8b8"));
        decimal.setBackground(Color.decode("#c5cf70"));
        equals.setBackground(Color.decode("#c5cf70"));
        clear.setBackground(Color.decode("#c5cf70"));
        ce.setBackground(Color.decode("#c5cf70"));
        squareRoot.setBackground(Color.decode("#c5cf70"));
        square.setBackground(Color.decode("#c5cf70"));

        // Initialize output log
        label.setPreferredSize(new Dimension(260, 70));
        label.setBackground(Color.GRAY);
        label.setOpaque(true);

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

        // Number buttons function
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

        // Addition function
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

        // Subtraction function
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

        // Multiplication function
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

        // Division function
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
        
        // Operation function
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
                } else if(mode == Mode.SECOND || mode == Mode.OPERATOR) {
                    num2 = 0;
                    mode = Mode.FIRST;
                }
                updateDisplay();
            }
        });

        // Square root function
        squareRoot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    mode = Mode.OPERATOR;
                    operator = Operator.SQUAREROOT;
                    label.setText(Math.sqrt(num1) + "");
                } else if(mode == Mode.OPERATOR || mode == Mode.SECOND) {
                    label.setText("Error");
                }
            }
        });

        // Square function
        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(mode == Mode.FIRST) {
                    mode = Mode.OPERATOR;
                    operator = Operator.SQUAREROOT;
                    label.setText(Math.pow(num1, 2) + "");
                } else if(mode == Mode.OPERATOR || mode == Mode.SECOND) {
                    label.setText("Error");
                }
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
