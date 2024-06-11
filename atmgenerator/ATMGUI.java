import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

public class ATMGUI extends JFrame {

    private BankAccount account;

    public ATMGUI(BankAccount account) {
        this.account = account;
        createAndShowGUI();
    }

    private void createAndShowGUI() {
        setTitle("ATM Machine");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(240, 240, 240));

        JPanel topPanel = new JPanel();
        topPanel.setBackground(new Color(50, 150, 200));
        JLabel titleLabel = new JLabel("ATM Machine");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        topPanel.add(titleLabel);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        centerPanel.setBackground(new Color(240, 240, 240));

        JLabel balanceLabel = new JLabel("Your current balance is: ₹" + formatCurrency(account.getBalance()));
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        centerPanel.add(balanceLabel);

        JButton checkBalanceButton = new JButton("Check Balance");
        checkBalanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Your balance is: ₹" + formatCurrency(account.getBalance()));
            }
        });
        centerPanel.add(checkBalanceButton);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter the amount to deposit:");
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0) {
                        account.deposit(amount);
                        updateBalanceLabel(balanceLabel);
                        JOptionPane.showMessageDialog(null, "Deposit successful. Your new balance is: ₹" + formatCurrency(account.getBalance()));
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Deposit amount must be greater than zero.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });
        centerPanel.add(depositButton);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = JOptionPane.showInputDialog(null, "Enter the amount to withdraw:");
                try {
                    double amount = Double.parseDouble(input);
                    if (amount > 0 && account.getBalance() >= amount) {
                        account.withdraw(amount);
                        updateBalanceLabel(balanceLabel);
                        JOptionPane.showMessageDialog(null, "Withdrawal successful. Your new balance is: ₹" + formatCurrency(account.getBalance()));
                    } else if (amount <= 0) {
                        JOptionPane.showMessageDialog(null, "Invalid amount. Withdrawal amount must be greater than zero.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Insufficient balance.");
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        });
        centerPanel.add(withdrawButton);

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the ATM window
            }
        });
        centerPanel.add(exitButton);

        mainPanel.add(centerPanel, BorderLayout.CENTER);

        getContentPane().add(mainPanel);
        setVisible(true);
    }

    private void updateBalanceLabel(JLabel balanceLabel) {
        balanceLabel.setText("Your current balance is: ₹" + formatCurrency(account.getBalance()));
    }

    private String formatCurrency(double amount) {
        DecimalFormat formatter = new DecimalFormat("###,###.##");
        return formatter.format(amount);
    }

    public static void main(String[] args) {
        BankAccount account = new BankAccount(1000); // Starting with a balance of ₹1000
        new ATMGUI(account);
    }
}
