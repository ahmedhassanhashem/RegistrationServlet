package edu.practise.regestration;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/validateOTP")
public class ValidateOTP extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int value = Integer.parseInt(request.getParameter("otp"));
        HttpSession httpSession = request.getSession();
        int otp = (int) httpSession.getAttribute("otp");

        RequestDispatcher dispatcher;

        if (value == otp){
            request.setAttribute("email", request.getParameter("email"));
            request.setAttribute("status", "success");
            dispatcher = request.getRequestDispatcher("newPassword.jsp");
            dispatcher.forward(request, response);
        }else{
            request.setAttribute("status", "failed");
            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            dispatcher.forward(request, response);
        }
    }
}
