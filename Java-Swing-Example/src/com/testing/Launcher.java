package com.testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.Flow;

public class Launcher {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Sum calculator");
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(400,500);
                frame.setLocationRelativeTo(null);

                JMenuBar menuBar = new JMenuBar();

                JMenu menu = new JMenu("Menu");
                menuBar.add(menu);

                JMenuItem item1 = new JMenuItem("Sum");
                item1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
                menu.add(item1);

                frame.add(menuBar, BorderLayout.NORTH);

                JPanel surroundingPanel = new JPanel(new BorderLayout());

                JPanel panel1 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10,10));
                JTextField number1 = new JTextField(10);
                JTextField number2 = new JTextField(10);

                panel1.add(number1);
                panel1.add(number2);

                JPanel panel2 = new JPanel(new GridLayout());
                JButton button = new JButton("Sum values");
                button.setFocusPainted(false);
                button.setMnemonic(KeyEvent.VK_S);

                panel2.add(button);

                JPanel panel3 = new JPanel(new BorderLayout());
                JLabel sum = new JLabel();
                sum.setFont(new Font("Arial", Font.PLAIN, 25));
                sum.setForeground(Color.white);
                sum.setPreferredSize(new Dimension(400, 100));
                sum.setHorizontalAlignment(SwingConstants.CENTER);

                panel3.add(sum);

                attachSumActionListener(button, number1, number2, sum);
                attachSumActionListener(item1, number1, number2, sum);

                panel1.setBackground(Color.LIGHT_GRAY);
                panel2.setBackground(Color.YELLOW);
                panel3.setBackground(Color.black);

                surroundingPanel.add(panel1, BorderLayout.NORTH);
                surroundingPanel.add(panel2, BorderLayout.CENTER);
                surroundingPanel.add(panel3, BorderLayout.SOUTH);

                frame.add(surroundingPanel, BorderLayout.CENTER);

                frame.setVisible(true);
            }
        });
    }

    private static <T extends AbstractButton, U extends JTextField, V extends JLabel>void attachSumActionListener(T button, U number1, U number2, V sum) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int firstValue = Integer.parseInt(number1.getText());
                    int secondValue = Integer.parseInt(number2.getText());
                    int calculation = firstValue + secondValue;

                    sum.setText(String.valueOf(calculation));
                } catch(Exception ex) {
                    sum.setText("Invalid input");
                }
            }
        });
    }
}
