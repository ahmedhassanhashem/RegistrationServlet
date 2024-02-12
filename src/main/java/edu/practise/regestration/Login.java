package edu.practise.regestration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.*;

@WebServlet("/login")
public class Login extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        HttpSession session = request.getSession();
        RequestDispatcher dispatcher;

        if(username == null | username.equals("") | password == null | password.equals("")){
            request.setAttribute("status", "invalid");
            dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/login", "root", "");
            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT username, password FROM users WHERE username = ? AND password = ?");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                session.setAttribute("name", resultSet.getString("username"));
                dispatcher = request.getRequestDispatcher("index.jsp");
            }else{
                request.setAttribute("status", "failed");
                dispatcher = request.getRequestDispatcher("login.jsp");
            }
            dispatcher.forward(request, response);
        }catch (SQLException | ClassNotFoundException e){
            e.printStackTrace();
        }
    }
}
