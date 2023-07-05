package com.example.jsp_2023_06_am;

import com.example.jsp_2023_06_am.config.Config;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import util.DBUtil;
import util.SecSql;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;

@WebServlet(name = "ArticleModifyServlet", value = "/article/modify")
public class ArticleModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        int loginedMemberId = -1;

        if(session.getAttribute("loginedMemberId") == null){
            request.setAttribute("loginedMemberId", loginedMemberId);
            response.getWriter().append("<script> location.replace('../member/login');</script>");
            return;
        }

        Connection conn = null;

        try {
            Class.forName(Config.getDBDriverName());
            String url = Config.getDBUrl();
            conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());

            int id = Integer.parseInt(request.getParameter("id"));

            SecSql sql = new SecSql();
            sql.append("SELECT * ");
            sql.append("FROM article");
            sql.append("WHERE id = ?", id);
            Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

            request.setAttribute("articleMap", articleMap);
            request.getRequestDispatcher("/article/modify.jsp").forward(request, response);

        } catch (ClassNotFoundException e) {
            System.out.println("드라이버 로딩 실패");
        } catch (SQLException e) {
            System.out.println("에러: " + e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
