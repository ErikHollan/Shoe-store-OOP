import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class PlaceOrder extends JDialog {
    private JButton tömVarukorgButton;
    private JButton läggTillButton;
    private JButton beställButton;
    private JButton loggaUtButton;
    private JTable table1;
    private JPanel test;
    Object[] items;
    private JComboBox comboBox1;
    private JComboBox comboBox2;
    private JComboBox comboBox3;
    private JTextArea textArea1;
    private JTextField quantityTextField;
    private JTextField yourOrderNumberTextField;
    private JButton seOrderhistorikButton;
    Repository repository = new Repository();
    int custId;

    public PlaceOrder(JFrame parent) throws SQLException {
        super(parent);
        setContentPane(test);
        setPreferredSize(new Dimension(400, 500));
        setVisible(true);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createTable();
        createComboBoxes();
        pack();

        läggTillButton.addMouseListener(buttonClick);
        tömVarukorgButton.addMouseListener(buttonClick);
        beställButton.addMouseListener(buttonClick);
        loggaUtButton.addMouseListener(buttonClick);
        seOrderhistorikButton.addMouseListener(buttonClick);

        try {
            BufferedReader input = new BufferedReader(new FileReader("src/LoggedIn.txt"));
            custId = Integer.parseInt(input.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    MouseAdapter buttonClick = new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) throws ClassCastException {

            Object src = e.getSource();
            int row = table1.getSelectedRow();
            String value = "";


            if (src == läggTillButton) {
                for (int i = 0; i < table1.getColumnCount()-1; i++) {
                    value += table1.getModel().getValueAt(row, i).toString() + " ";
                }
                textArea1.setText(value);
            }

            if (src == seOrderhistorikButton)
            {
                try {
                    OrderHistory orderHistory = new OrderHistory(null);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                setVisible(false);
            }

            if (src == tömVarukorgButton){
                value = "";
                textArea1.setText(value);
            }
            if (src == beställButton){
                  String s =  repository.addToCart(custId,
                            Integer.parseInt(yourOrderNumberTextField.getText()),
                            table1.getSelectedRow()+1,
                            Integer.parseInt(quantityTextField.getText()));

               JOptionPane.showMessageDialog(null,s);


            }


            if (src == loggaUtButton){
                Window window = new Window();
                try {
                    window.swapPage(Window.Page.LOGIN);
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
                dispose();
            }

        }

    };

    private void createTable() throws SQLException {

        Repository repository = new Repository();
        Object[][]test1 = new Object[12][7];

        for (int i = 0; i < repository.getShoe().size(); i++)
        {
            test1[i][0] = repository.getShoe().get(i).getSize();
            test1[i][1]= repository.getShoe().get(i).getColour();
            test1[i][2] = repository.getShoe().get(i).isGender();
            test1[i][3]= repository.getShoe().get(i).isBrand();
            test1[i][4]= repository.getShoe().get(i).isCategory();
            test1[i][5]= repository.getShoe().get(i).getPrice();
            test1[i][6]= repository.getShoe().get(i).getQuantity();
        }

        table1.setModel(new DefaultTableModel(
                test1,
                new String[]{"Size" , "Colour" , "Gender" , "Brand" , "Category" , "Price" , "Quantity"}));
    }
    private void createComboBoxes() {
        String[] gender = {" ", "Male", "Female"};
        String[] brand = {" ", "Adidas", "Converse", "Ecco", "Nike"};
        String[] category = {" ", "Dining", "Sneakers", "Sport", "Sandals", "Winter"};

        for (String genders : gender) {
            comboBox1.addItem(genders);
        }
        for (String brands : brand) {
            comboBox2.addItem(brands);
        }
        for (String categorys : category) {
            comboBox3.addItem(categorys);
        }

    }

    public static void main(String[] args) throws SQLException, IOException {

    }
}
