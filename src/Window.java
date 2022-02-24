import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;

public class Window extends JFrame{
    public static Window window;
    public enum Page {
        LOGIN,
    }

    JPanel currentPage = new JPanel();

    public Window() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
        setSize(400,500);
        setBackground(Color.DARK_GRAY);

    }
    public void swapPage(Page page) throws IOException, SQLException, ClassNotFoundException {
        remove(currentPage);
        switch (page) {
            case LOGIN: {
                currentPage = new LogIn();
                break;
            }

        }
        add(currentPage);
        revalidate();
    }

    public static void main(String[] args) throws IOException, SQLException, ClassNotFoundException {
        window = new Window();
        window.swapPage(Page.LOGIN);
    }


}
