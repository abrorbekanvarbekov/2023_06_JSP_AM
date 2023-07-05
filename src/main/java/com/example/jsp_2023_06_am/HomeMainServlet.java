package com.example.jsp_2023_06_am;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "HomeMainServlet", value = "/")
public class HomeMainServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        int loginedMemberId = -1;

        if(session.getAttribute("loginedMemberId") != null){
            loginedMemberId = (int) session.getAttribute("loginedMemberId");
        }

        request.setAttribute("loginedMemberId", loginedMemberId);
        request.getRequestDispatcher("/home/main.jsp").forward(request, response);
    }

}
