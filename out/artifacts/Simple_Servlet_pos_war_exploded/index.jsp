<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.SQLException" %><%--
  Created by IntelliJ IDEA.
  User: yohan
  Date: 5/25/2021
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer JSP</title>
    <link href="assests/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>

<main class="container-fluid">
    <section class="row">
        <h1>Customer Manage</h1>
    </section>
    <section class="row">
        <div class="col-md-6">
            <form action="customer2" method="post">
                <div>
                    <label class="form-label" for="id">Customer ID</label>
                    <input class="form-control" id="id" name="id" type="text">
                </div>
                <div>
                    <label class="form-label" for="name">Customer Name</label>
                    <input class="form-control" id="name" name="name" type="text">
                </div>
                <div>
                    <label class="form-label" for="address">Customer Address</label>
                    <input class="form-control" id="address" name="address" type="text">
                </div>
                <div>
                    <label class="form-label" for="salary">Customer Salary</label>
                    <input class="form-control" id="salary" name="salary" type="text">
                </div>
                <div class="mt-3">
                    <button class="btn btn-primary" type="submit">Save Customer</button>
                </div>
            </form>
        </div>
        <div class="col">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">First</th>
                    <th scope="col">Last</th>
                    <th scope="col">Handle</th>
                </tr>
                </thead>
                <tbody>
                <%
                    ResultSet resultSet = (ResultSet) request.getServletContext().getAttribute("resultSet");
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            try {
                                String id = resultSet.getString(1);
                                String name = resultSet.getString(2);
                                String address = resultSet.getString(3);
                                String salary = resultSet.getString(4);
                                System.out.println(id + " " + name + " " + address + " " + salary);
                %>
                <tr>
                    <td><%=id%>
                    </td>
                    <td><%=name%>
                    </td>
                    <td><%=address%>
                    </td>
                    <td><%=salary%>
                    </td>
                </tr>
                <%
                            } catch (SQLException throwables) {
                                throwables.printStackTrace();
                            }
                        }
                    }
                %>
                </tbody>
            </table>
        </div>
    </section>
    <section class="row">
        <div class="col mt-1">
            <form action="customer2" method="get">
                <button class="btn btn-primary" type="submit">customer 2</button>
            </form>
        </div>
    </section>
</main>


</body>
</html>
