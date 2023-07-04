package com.example.jsp_2023_06_am.memberServlet;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;

@WebServlet(name = "MemberJoinServlet", value = "/member")
public class MemberJoinServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/member/memberJoin.jsp").forward(request,response);
    }

}
