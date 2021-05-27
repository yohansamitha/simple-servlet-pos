package lk.ijse.javaee.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "CustomerServlet2", urlPatterns = "/customer2")
public class CustomerServlet2 extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String salary = request.getParameter("salary");
        System.out.println(id + " " + name + " " + address + " " + salary);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinePos", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("insert into customer (id,name,address,salary) values (?,?,?,?)");
            preparedStatement.setObject(1, id);
            preparedStatement.setObject(2, name);
            preparedStatement.setObject(3, address);
            preparedStatement.setObject(4, salary);
            boolean b = preparedStatement.executeUpdate() > 0;
            System.out.println(b + " data save");
//            PrintWriter writer = response.getWriter();
//            if (b) {
//                writer.write("Customer saved successfully");
//            } else {
//                writer.write("Customer not saved");
//            }
//            writer.close();
            response.sendRedirect("customer2");
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/onlinePos", "root", "1234");
            PreparedStatement preparedStatement = connection.prepareStatement("Select * from customer");
            ResultSet resultSet = preparedStatement.executeQuery();
            request.getServletContext().setAttribute("resultSet", resultSet);
            response.sendRedirect("index.jsp");
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
