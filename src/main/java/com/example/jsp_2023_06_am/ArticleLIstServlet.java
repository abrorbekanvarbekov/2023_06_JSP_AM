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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet(name = "ArticleListServlet", value = "/article/list")
public class ArticleLIstServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        try {
            Class.forName(Config.getDBDriverName());
            String url = Config.getDBUrl();
            conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());

            int page = 1;

            if (request.getParameter("page") != null && request.getParameter("page").length() != 0){
                page = Integer.parseInt(request.getParameter("page"));
            }

            int itemsInPage = 10;
            int limitFrom = (page-1) * itemsInPage;

            SecSql sql = new SecSql();
            sql.append("SELECT COUNT(*)");
            sql.append("FROM article");
            int articleCount = DBUtil.selectRowIntValue(conn, sql);
            int totalPage = (int) Math.ceil((double) articleCount / itemsInPage);

            int pageSize = 5;

            int from = page - pageSize;
            if (from < 1){
                from = 1;
            }
            int end = page + pageSize;
            if (end > totalPage){
                end = totalPage;
            }
            sql = new SecSql();
            sql.append("SELECT * ");
            sql.append("FROM article");
            sql.append("ORDER BY ID DESC");
            sql.append("LIMIT ?, 10;", limitFrom);

            List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);

            request.setAttribute("page", page);
            request.setAttribute("totalPage", totalPage);
            request.setAttribute("articleListMap", articleListMap);
            request.setAttribute("from", from);
            request.setAttribute("end", end);

            request.getRequestDispatcher("/article/list.jsp").forward(request, response);


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
