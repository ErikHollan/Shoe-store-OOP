import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.SQLException;

public class AccountOverview extends JPanel {


    JButton basketButton = new JButton("Lägg till i varukorg");
    JButton depositButton = new JButton("Visa varukorg");
    JButton orderButton = new JButton("Beställ");
    JButton logOutButton = new JButton("Logga ut");

    JLabel userName = new JLabel("", SwingConstants.CENTER);
    JLabel userId = new JLabel("", SwingConstants.CENTER);
    JLabel dailyAccLabel = new JLabel("Daily acc", SwingConstants.CENTER);
    JLabel savingsAccLabel = new JLabel("Saving acc", SwingConstants.CENTER);
    JLabel amount = new JLabel("Belopp", SwingConstants.CENTER);

    String[] accountList = {" ", "from daily to savings", "from savings to daily"};
    JComboBox accountsDropDownList = new JComboBox(accountList);

    String[] accountList2 = {" ", "Daily", "Savings"};
    JComboBox accountsDropDownList2 = new JComboBox(accountList2);

    JTextArea transferInput = new JTextArea();

    GridBagConstraints gbc = new GridBagConstraints();


    public AccountOverview() throws IOException {

        basketButton.addMouseListener(buttonClick);
        logOutButton.addMouseListener(buttonClick);
        depositButton.addMouseListener(buttonClick);

        setLayout(new GridBagLayout());
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        amount.setForeground(Color.white);
        amount.setFont(new Font("Arial", Font.ITALIC, 12));

        add(userName);
        add(userId);
        add(dailyAccLabel);
        add(savingsAccLabel);
        add(logOutButton);
        add(depositButton);
        add(orderButton);

        add(transferInput).setVisible(false);
        add(amount).setVisible(false);
        add(accountsDropDownList).setVisible(false);
        add(accountsDropDownList2).setVisible(false);

        gbc.fill = GridBagConstraints.BOTH;



        //setSize(700, 500);
        setBackground(Color.DARK_GRAY);

        userId.setFont(new Font("Arial", Font.ITALIC, 16));
        userId.setForeground(Color.white);
        userName.setFont(new Font("Arial", Font.ITALIC, 16));
        userName.setForeground(Color.white);


        dailyAccLabel.setForeground(Color.white);
        dailyAccLabel.setPreferredSize(new Dimension(150, 100));
        dailyAccLabel.setFont(new Font("Arial", Font.PLAIN, 22));

        savingsAccLabel.setForeground(Color.white);
        savingsAccLabel.setPreferredSize(new Dimension(150, 100));
        savingsAccLabel.setFont(new Font("Arial", Font.PLAIN, 22));

        userInput(transferInput);

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(userName, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(userId, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(dailyAccLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(savingsAccLabel, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        add(basketButton, gbc);

        gbc.gridy = 3;
        gbc.gridx = 1;
        add(depositButton, gbc);


        gbc.gridy = 5;
        gbc.gridx = 0;
        add(accountsDropDownList2, gbc);

        gbc.gridy = 6;
        gbc.gridx = 0;
        add(accountsDropDownList, gbc);


        gbc.gridy = 9;
        gbc.gridx = 0;
        add(amount, gbc);

        gbc.gridy = 10;
        gbc.gridx = 0;
        add(transferInput, gbc);

        gbc.gridy = 11;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(logOutButton, gbc);

    }

    MouseAdapter buttonClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) throws ClassCastException {

            Object src = e.getSource();


            if (src == basketButton) {



            if (src == depositButton) {


                }
            }

            if (src == logOutButton) {
                try {
                    Window.window.swapPage(Window.Page.LOGIN);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }

        }

    };

    private JLabel text(JLabel jLabel) {
        jLabel.setFont(new Font("Arial", Font.BOLD, 18));
        jLabel.setOpaque(false);
        return jLabel;
    }

    private JTextArea userInput(JTextArea jTextArea) {
        jTextArea.setFont(new Font("Arial", Font.ITALIC, 16));
        jTextArea.setLineWrap(true);
        return jTextArea;
    }


}
