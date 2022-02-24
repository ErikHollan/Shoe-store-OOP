import java.io.FileInputStream;
import java.sql.*;
import java.util.*;

public class Repository {
    private Properties p = new Properties();

    public Repository() {
        try {
            p.load(new FileInputStream("src/config.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(p.getProperty("url"),
                    p.getProperty("username"),
                    p.getProperty("password"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public String addToCart(int custId, int orderId, int shoeId, int quantity) {

        Connection con = getConnection();
        try {
            CallableStatement stm = con.prepareCall("CALL addtocart(?,?,?,?)");
            stm.setInt(1, custId);
            stm.setInt(2, orderId);
            stm.setInt(3, shoeId);
            stm.setInt(4, quantity);
            stm.execute();
        } catch (SQLException e) {
            return (e.getMessage() + " (" + e.getErrorCode() + ")");
        }

        return "Order added";
    }


    public List<OrderContent> getOrderContent(int number) throws SQLException {

        Connection con = getConnection();
        List<OrderContent> ordercontent = new ArrayList<>();

        PreparedStatement getContent = con.prepareStatement("SELECT * from ordercontent where orderid =" + number);
        ResultSet rs = getContent.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id");
            int shoeId = rs.getInt("shoeId");
            int orderId = rs.getInt("orderId");
            int quantity = rs.getInt("quantity");
            ordercontent.add(new OrderContent(id, shoeId, orderId, quantity));
        }
        return ordercontent;
    }

    public List<Orders> getCustomerOrders(int customerId) throws SQLException {
        Connection con = getConnection();
        PreparedStatement getorders = con.prepareStatement("SELECT * from orders");
        ResultSet rsOrders = getorders.executeQuery();
        List<Orders> ordersList = new ArrayList<>();

        while (rsOrders.next()) {
            int customersId = rsOrders.getInt("customerId");
            if (customersId == customerId) {
                int id = rsOrders.getInt("id");
                String orderdate = rsOrders.getString("orderDate");
                ordersList.add(new Orders(id, customersId, orderdate));
            }

        }
        return ordersList;
    }


    public List<Shoe> getShoe() throws SQLException {

        Connection con = getConnection();
        List<Shoe> shoeList = new ArrayList<>();

        PreparedStatement getShoes = con.prepareStatement("SELECT * from shoe");
        ResultSet rs = getShoes.executeQuery();

        while (rs.next()) {

            int id = rs.getInt("id");
            int size = rs.getInt("size");
            String colour = rs.getString("colour");
            int genderID = rs.getInt("genderId");
            int brandID = rs.getInt("brandId");
            int categoryID = rs.getInt("categoryId");
            int price = rs.getInt("price");
            int quantity = rs.getInt("quantity");
            shoeList.add(new Shoe(id, size, colour, genderID, brandID, categoryID, price, quantity));
        }
        return shoeList;
    }

    public User getUser(String name, String password) throws SQLException {

        User user1 = null;
        Connection con = getConnection();

        PreparedStatement getUser = con.prepareStatement("SELECT * from user");
        ResultSet rsuser = getUser.executeQuery();

        while (rsuser.next()) {
            if (name.equals(rsuser.getString("username"))) {
                if (password.equals(rsuser.getString("password"))) {
                    int customerId = rsuser.getInt("customerid");
                    int id = rsuser.getInt("id");
                    user1 = new User(id, name, password, customerId);
                    System.out.println("Välkommen " + user1.getUsername());
                    break;
                } else {
                    System.out.println("Fel lösenord");
                    break;
                }
            } else {
                System.out.println("fel lösenord");
            }
        }
        return user1;
    }
}