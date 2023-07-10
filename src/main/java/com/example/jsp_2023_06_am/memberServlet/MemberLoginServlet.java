package com.example.jsp_2023_06_am.memberServlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/member/login")
public class MemberLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        if(session.getAttribute("loginedMemberId") != null){
            response.setContentType("text/html; charset=UTF-8");
            response.getWriter().append("<script> alert('이미 로그인 상태입니다.'); location.replace('../');</script>");
            return;
        }
        request.getRequestDispatcher("/member/login.jsp").forward(request, response);
    }

}
