package lk.ijse.javaee.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customer")
public class CustomerServlet extends HttpServlet {

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
            PrintWriter writer = response.getWriter();
            if (b) {
                writer.write("Customer saved successfully");
            } else {
                writer.write("Customer not saved");
            }
            writer.close();
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
            response.setContentType("text/html");
            PrintWriter writer = response.getWriter();
            writer.write("<link href=\"assests/css/bootstrap.min.css\" rel=\"stylesheet\">");
            writer.write("<table class=\"table table-hover\">");
            writer.write("<thead>");
            writer.write("    <tr><td scope=\"col\">#</td><td scope=\"col\">First</td><td scope=\"col\">Last</td><td scope=\"col\">Handle</td></tr>");
            writer.write("</thead>");
            writer.write("  <tbody>");
            while (resultSet.next()) {
                String id = resultSet.getString(1);
                String name = resultSet.getString(2);
                String address = resultSet.getString(3);
                String salary = resultSet.getString(4);
                System.out.println(id + " " + name + " " + address + " " + salary);
//                writer.write(id + " " + name + " " + address + " " + salary + "\n");
                writer.write("<tr>");
//                writer.write("<th scope=\"row\">1</th>");
                writer.write("      <td >" + id + "</td>");
                writer.write("      <td>" + name + "</td>");
                writer.write("      <td>" + address + "</td>");
                writer.write("      <td>" + salary + "</td>");
                writer.write("    </tr>");
            }
            writer.write("  </tbody>");
            writer.write("</table>");
            writer.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
