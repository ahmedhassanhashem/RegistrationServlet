package edu.practise.regestration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/registration")
public class Registration extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("pass");
        String re_password = request.getParameter("re_pass");
        String contact = request.getParameter("contact");

        RequestDispatcher dispatcher;
        Connection connection = null;

        if(name == null | name.equals("") | password == null | password.equals("") |
        email == null | email.equals("") | contact == null | contact.equals("")){
            request.setAttribute("status", "invalid");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        if(!password.equals(re_password)){
            request.setAttribute("status", "password");
            dispatcher = request.getRequestDispatcher("registration.jsp");
            dispatcher.forward(request, response);
        }
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/login", "root", "");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO users(username, email, password, mobile) VALUES(?, ?, ?, ?)");
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            preparedStatement.setString(4,contact);

            int rowCount = preparedStatement.executeUpdate();
            dispatcher = request.getRequestDispatcher("registration.jsp");
            if (rowCount > 0) {
                request.setAttribute("status", "success");
            }
            else {
                request.setAttribute("status", "failed");
            }

            dispatcher.forward(request, response);

        }catch(SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}