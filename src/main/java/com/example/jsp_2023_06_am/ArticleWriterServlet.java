package com.example.jsp_2023_06_am;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import util.DBUtil;
import util.SecSql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@WebServlet("/article/write")
public class ArticleWriterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        int loginedMemberId = -1;

        if(session.getAttribute("loginedMemberId") == null){
            request.setAttribute("loginedMemberId", loginedMemberId);
            response.getWriter().append("<script> location.replace('../');</script>");
            return;
        }

        request.getRequestDispatcher("/article/write.jsp").forward(request, response);
    }

}
