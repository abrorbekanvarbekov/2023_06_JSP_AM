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

@WebServlet("/article/modify")
public class ArticleModifyServlet extends HttpServlet {
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
            sql.append("SELECT * ");
            sql.append("FROM article");
            sql.append("WHERE id = ?", id);
            Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

            if (articleMap.isEmpty()){
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().append(String.format("<script> alert('해당 게시글이 존재하지 않습니다!!'); location.replace('list');</script>"));
                return;
            }

            HttpSession session = request.getSession();
            int loginedMemberId = -1;

            if(session.getAttribute("loginedMemberId") != null){
                loginedMemberId = (int) session.getAttribute("loginedMemberId");
            }

            if(loginedMemberId == -1){
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().append("<script> alert('로그인 후 이용해주세요!!'); location.replace('../member/login');</script>");
                return;
            }

            System.out.println(loginedMemberId);
            System.out.println(articleMap.get("memberId"));

            if (Integer.parseInt((String) articleMap.get("memberId")) != loginedMemberId){
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().append(String.format("<script> alert('해당 게시글에 대한 권한이 없습니다!!'); location.replace('detail?id=%d');</script>",id));
                return;
            }

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
