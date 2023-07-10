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
import java.util.List;
import java.util.Map;

@WebServlet("/article/detail")
public class ArticleDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        try {
            Class.forName(Config.getDBDriverName());
            String url = Config.getDBUrl();
            conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());

            int id = Integer.parseInt(request.getParameter("id"));

            HttpSession session = request.getSession();
            int loginedMemberId = -1;

            if(session.getAttribute("loginedMemberId") != null){
                loginedMemberId = (int) session.getAttribute("loginedMemberId");
            }

            SecSql sql = new SecSql();
            sql.append("select A.*, M.name");
            sql.append("from article as A");
            sql.append("inner join member as M");
            sql.append("on A.memberId = M.id");
            sql.append("where A.id = ?", id);
            Map<String, Object> articleMap = DBUtil.selectRow(conn, sql);

            if (articleMap.isEmpty()){
                response.setContentType("text/html; charset=UTF-8");
                response.getWriter().append(String.format("<script> alert('해당 게시글이 존재하지 않습니다!!'); location.replace('list');</script>", id));
                return;
            }

            request.setAttribute("articleMap", articleMap);
            request.setAttribute("loginedMemberId", loginedMemberId);
            request.getRequestDispatcher("/article/detail.jsp").forward(request, response);

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
