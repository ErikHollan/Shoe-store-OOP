import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class OrderHistory extends JDialog {
    private JTable table1;
    private JButton backaButton;
    private JPanel test;
    int custId;

    public OrderHistory(JFrame parent) throws SQLException {
        super(parent);
        setContentPane(test);
        setPreferredSize(new Dimension(400, 500));
        setVisible(true);
        setModal(true);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        createTable();
        pack();


        try {
            BufferedReader input = new BufferedReader(new FileReader("src/LoggedIn.txt"));
            custId = Integer.parseInt(input.readLine());
        } catch (IOException ex) {
            ex.printStackTrace();
        }


    }

    private void createTable() throws SQLException {

        Repository repository = new Repository();
        Object[][] test1 = new Object[20][20];

        try {
            Properties p = new Properties();
            p.load(new FileInputStream("src/config.properties"));

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(p.getProperty("url"),
                    p.getProperty("username"),
                    p.getProperty("password"));

            List<Integer> Shoes = new ArrayList<>();

            for (int i = 0; i < repository.getCustomerOrders(3).size(); i++) {
                Shoes = (repository
                        .getOrderContent(repository
                                .getCustomerOrders(3)
                                .get(i).id).stream()
                        .map(OrderContent::getShoeId)
                        .collect(Collectors.toList()));
            }
            System.out.println(Shoes);

            PreparedStatement getshoes = con.prepareStatement("SELECT * from shoe");
            ResultSet rsShoe = getshoes.executeQuery();
            List<Shoe> shoeList = new ArrayList<>();

            while (rsShoe.next()) {
                for (int i = 0; i < Shoes.size(); i++) {
                    int shoesId = Shoes.get(i);
                    if (shoesId == rsShoe.getInt("id")) {
                        int id = rsShoe.getInt("id");
                        int size = rsShoe.getInt("size");
                        String colour = rsShoe.getString("colour");
                        int genderId = rsShoe.getInt("genderId");
                        int brandId = rsShoe.getInt("brandId");
                        int categoryId = rsShoe.getInt("categoryId");
                        int price = rsShoe.getInt("price");
                        int quantity = rsShoe.getInt("quantity");

                        shoeList.add(new Shoe(id, size, colour, genderId, brandId, categoryId, price, quantity));
                    }
                }
            }

            for (int i = 0; i < shoeList.size(); i++)
            {
                test1[i][0] = repository.getShoe().get(i).getSize();
                test1[i][1]= repository.getShoe().get(i).getColour();
                test1[i][2] = repository.getShoe().get(i).isGender();
                test1[i][3]= repository.getShoe().get(i).isBrand();
                test1[i][4]= repository.getShoe().get(i).isCategory();
                test1[i][5]= repository.getShoe().get(i).getPrice();
            }



            table1.setModel(new DefaultTableModel(
                    test1,
                    new String[]{"Size" , "Colour" , "Gender" , "Brand" , "Category" , "Price"}));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}