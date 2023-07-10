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

@WebServlet(name = "ArticleWriteServlet", value = "/article/doModify")
public class ArticleDoModifyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        HttpSession session = request.getSession();
        int loginedMemberId = -1;

        if(session.getAttribute("loginedMemberId") == null){
            request.setAttribute("loginedMemberId", loginedMemberId);
            response.getWriter().append("<script> alert('로그인 후 이용해주세요!!'); location.replace('../member/login');</script>");
            return;
        }

        Connection conn = null;
        try {
            Class.forName(Config.getDBDriverName());
            String url = Config.getDBUrl();
            conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());


            String title = request.getParameter("title");
            String body = request.getParameter("body");
            int id = Integer.parseInt(request.getParameter("id"));

            SecSql sql = new SecSql();
            sql.append("UPDATE article");
            sql.append("SET updateDate = NOW(),");
            sql.append("title = ?", title);
            sql.append(",`body` = ?", body);
            sql.append("WHERE id = ?", id);

            DBUtil.update(conn, sql);

            response.getWriter().append(String.format("<script> alert('%d번 게시글이 수정되었습니다'); location.replace('detail?id=%d');</script>", id, id));

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

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
