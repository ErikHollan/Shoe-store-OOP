import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.*;
import java.util.Properties;

public class LogIn extends JPanel{

    JLabel usernameLabel = new JLabel("Username:");
    JLabel passwordLabel = new JLabel("Password:");
    JButton loginButton = new JButton("Login");
    JTextArea userTextArea = new JTextArea();
    JButton createUser = new JButton("Create user");
    JPasswordField passwordField = new JPasswordField();
    JCheckBox showPasswordBox = new JCheckBox("Show password");

    LogIn() {


        setBackground(Color.DARK_GRAY);
        setPreferredSize(new Dimension(400, 500));
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 20, 30);
        gbc.anchor = GridBagConstraints.CENTER;

        loginButton.addMouseListener(buttonClick);
        createUser.addMouseListener(buttonClick);

        usernameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        usernameLabel.setForeground(Color.white);
        usernameLabel.setOpaque(false);

        userTextArea.setFont(new Font("Arial", Font.ITALIC, 16));
        userTextArea.setPreferredSize(new Dimension(150, 22));
        userTextArea.setLineWrap(true);
        userTextArea.setOpaque(true);

        passwordField.setFont(new Font("Arial", Font.ITALIC, 16));
        passwordField.setPreferredSize(new Dimension(157, 30));

        passwordLabel.setFont(new Font("Arial", Font.BOLD, 18));
        passwordLabel.setForeground(Color.white);
        passwordLabel.setOpaque(false);

        showPasswordBox.setForeground(Color.white);
        showPasswordBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (showPasswordBox.isSelected()) {
                    passwordField.setEchoChar((char) 0);
                } else {
                    passwordField.setEchoChar('*');
                }
            }
        });

        loginButton.setFont(new Font("Arial", Font.ITALIC, 16));
        loginButton.setPreferredSize(new Dimension(150, 30));

        createUser.setFont(new Font("Arial", Font.ITALIC, 16));
        createUser.setPreferredSize(new Dimension(150, 30));

        gbc.gridy = 0;
        gbc.gridx = 0;
        add(usernameLabel, gbc);

        gbc.gridy = 0;
        gbc.gridx = 1;
        add(userTextArea, gbc);

        gbc.gridy = 1;
        gbc.gridx = 0;
        add(passwordLabel, gbc);

        gbc.gridy = 1;
        gbc.gridx = 1;
        add(passwordField, gbc);

        gbc.gridy = 2;
        gbc.gridx = 1;
        add(showPasswordBox, gbc);

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        add(loginButton, gbc);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 5;
        add(createUser, gbc);
    }

    MouseAdapter buttonClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) throws ClassCastException {

            Object src = e.getSource();
            String s;

            try {
                Properties p = new Properties();
                p.load(new FileInputStream("src/config.properties"));

                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection(p.getProperty("url"),
                        p.getProperty("username"),
                        p.getProperty("password"));

                PreparedStatement user = con.prepareStatement("Select * from user");
                ResultSet rsuser = user.executeQuery();


            if (src == loginButton) {
                String username = userTextArea.getText();
                String password = passwordField.getText();
                while (rsuser.next())
                {
                    if (username.equals(rsuser.getString("username"))){
                        if(password.equals(rsuser.getString("password"))) {
                            int customerId = rsuser.getInt("customerid");
                            int id = rsuser.getInt("id");
                            User user1 = new User(id, username, password, customerId);
                            System.out.println("Välkommen " + user1.getUsername());
                            Window.window.setVisible(false);
                           FileWriter output = new FileWriter("src/LoggedIn.txt");
                            output.write("" + customerId);
                            output.close();
                            PlaceOrder placeOrder = new PlaceOrder(null);
                            break;

                        } else {
                            JOptionPane.showMessageDialog(null, "Fel Lösenord");
                            break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null,"användare hittas ej");
                    }
                }

            }
        }
            catch (Exception k){
                k.printStackTrace();
            }
        }

    };


    public static void main(String[] args) {
        Window window = new Window();
        window.pack(); //måste ha detta här för att window skall bli rätt size
    }

}
