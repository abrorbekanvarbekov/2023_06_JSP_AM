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

@WebServlet(name = "ArticleDeleteServlet", value = "/article/doDelete")
public class ArticleDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        Connection conn = null;
        try {
            Class.forName(Config.getDBDriverName());
            String url = Config.getDBUrl();
            conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());

            int id = Integer.parseInt(request.getParameter("id"));

            SecSql sql = new SecSql();
            sql.append("DELETE FROM");
            sql.append("article");
            sql.append("WHERE id = ?", id);
            DBUtil.delete(conn, sql);

            response.getWriter().append(String.format("<script> alert('%d번 게시글이 삭제되었습니다'); location.replace('list');</script>", id));

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
