package com.example.jsp_2023_06_am;

import com.example.jsp_2023_06_am.config.Config;
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

@WebServlet("/article/doWrite")
public class ArticleDoWriteServlet extends HttpServlet {
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

        Connection conn = null;
        try {
            Class.forName(Config.getDBDriverName());
            String url = Config.getDBUrl();
            conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());


            String title = request.getParameter("title");
            String body = request.getParameter("body");

            int memberId = (int) session.getAttribute("loginedMemberId");

            SecSql sql = new SecSql();
            sql.append("INSERT article");
            sql.append("SET regDate = NOW(),");
            sql.append("updateDate = NOW(),");
            sql.append("title = ?", title);
            sql.append(",`body` = ?", body);
            sql.append(",memberId = ?", memberId);

            int id = DBUtil.insert(conn, sql);

            response.getWriter().append(String.format("<script> alert('%d번 게시글이 생성 되었습니다'); location.replace('list');</script>", id));

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
