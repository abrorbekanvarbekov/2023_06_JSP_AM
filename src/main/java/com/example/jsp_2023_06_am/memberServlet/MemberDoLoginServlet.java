package com.example.jsp_2023_06_am.memberServlet;

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

@WebServlet("/member/doLogin")
public class MemberDoLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");

        Connection conn = null;
        try {
            Class.forName(Config.getDBDriverName());
            String url = Config.getDBUrl();
            conn = DriverManager.getConnection(url, Config.getDBUser(), Config.getDBPassword());

            String userId = request.getParameter("userId");
            String userPw = request.getParameter("userPw");

            System.out.println(userId);
            SecSql sql = new SecSql();
            sql.append("SELECT * from member");
            sql.append("WHERE userId = ?", userId);

            Map<String,Object> member = DBUtil.selectRow(conn, sql);
            System.out.println(member);
            if (member.isEmpty()){
                response.getWriter().append(String.format("<script> alert('%s 아이디가 존재한지 않음'); location.replace('login');</script>", userId));
                return;
            }

            if (member.get("userPw").equals(userPw) == false){
                response.getWriter().append(String.format("<script> alert('비밀번호가 일치하지 않음'); location.replace('login');</script>"));
                return;
            }

            HttpSession session = request.getSession();
            session.setAttribute("loginedMemberId", member.get("id"));

            response.getWriter().append(String.format("<script> alert('%s님 환영합니다~'); location.replace('../');</script>", userId));

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
