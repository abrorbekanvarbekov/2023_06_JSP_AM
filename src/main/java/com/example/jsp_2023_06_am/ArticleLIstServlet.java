package com.example.jsp_2023_06_am;

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
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://127.0.0.1:3306/jsp_article_manager?useUnicode=true&characterEncoding=utf8&autoReconnect=true&serverTimezone=Asia/Seoul&useOldAliasMetadataBehavior=true&zeroDateTimeNehavior=convertToNull";
            conn = DriverManager.getConnection(url, "root", "");

        request.getParameter("page");
            int page = 1;

            if (request.getParameter("page") != null && request.getParameter("page").length() != 0){
                page = Integer.parseInt(request.getParameter("page"));
            }

            int itemsInPage = 10;
            int limitFrom = (page-1) * itemsInPage;

            SecSql sql_Count = new SecSql();
            sql_Count.append("SELECT COUNT(*)");
            sql_Count.append("FROM article");
            int articleCount = DBUtil.selectRowIntValue(conn, sql_Count);
            int pageCount = (int) Math.ceil((double) articleCount / itemsInPage);


            SecSql sql = new SecSql();
            sql.append("SELECT * ");
            sql.append("FROM article");
            sql.append("ORDER BY ID DESC");
            sql.append("LIMIT ?, 10;", limitFrom);

            List<Map<String, Object>> articleListMap = DBUtil.selectRows(conn, sql);

            request.setAttribute("page", page);
            request.setAttribute("pageCount", pageCount);
            request.setAttribute("articleListMap", articleListMap);

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
