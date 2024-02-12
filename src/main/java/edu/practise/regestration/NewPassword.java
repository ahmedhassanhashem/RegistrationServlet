package edu.practise.regestration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/newPassword")
public class NewPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession httpSession = request.getSession();
        String password = request.getParameter("password");
        String confPassword = request.getParameter("confPassword");
        RequestDispatcher dispatcher;

        if(password != null && confPassword != null && password.equals(confPassword)){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/login", "root", "");
                PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET password = ? WHERE email = ?");
                preparedStatement.setString(1, password);
                preparedStatement.setString(2, (String) httpSession.getAttribute("email"));

                int row = preparedStatement.executeUpdate();
                if(row > 0){
                    request.setAttribute("status", "OTPsuccess");
                    dispatcher = request.getRequestDispatcher("login.jsp");
                }else {
                    request.setAttribute("status", "OTPfailed");
                    dispatcher = request.getRequestDispatcher("login.jsp");
                }
                dispatcher.forward(request, response);

            }catch (ClassNotFoundException | SQLException e){

            }
        }
    }
}
