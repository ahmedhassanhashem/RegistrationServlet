package edu.practise.regestration;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Properties;
import java.util.Random;

@WebServlet("/forgotPassword")
public class ResetPassword extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String to = request.getParameter("email"); //Recipient's email address
        RequestDispatcher dispatcher;
        int otp = 0;
        HttpSession httpSession = request.getSession();

        if(to != null || !to.equals("")){
            otp = new Random().nextInt(125598);


            Properties properties = new Properties();
            properties.put("mail.smtp.host", "smtp.gmail.com");
            properties.put("mail.smtp.port", "587");
            properties.put("mail.smtp.auth", "true");
            properties.put("mail.smtp.starttls.enable", "true");
            properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
//            properties.put("mail.smtp.socketFactory.port", "456");
//            properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

            Session mailSession = Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("poshspareparts@gmail.com", "aykcdlzamsatwcab");
                }
            });


            try{
                MimeMessage message = new MimeMessage(mailSession);
                message.setFrom(new InternetAddress("poshspareparts@gmail.com"));
                message.addRecipient(Message.RecipientType.TO, new InternetAddress("ahmedhassanhimself@gmail.com"));
                message.setSubject("Reset Password");
                message.setText("Your OTP is " + otp);

                Transport.send(message);
                System.out.println("Message Send Successfully");
            }catch (MessagingException e){
                throw new RuntimeException(e);
            }
            dispatcher = request.getRequestDispatcher("EnterOtp.jsp");
            httpSession.setAttribute("message", "OTP is sent");
            httpSession.setAttribute("otp", otp);
            httpSession.setAttribute("email", to);
            dispatcher.forward(request, response);
        }
    }
    //    String password = "mxklskkahvbesoxo";
//    String mail = "a27m3d@yahoo.com";
}
