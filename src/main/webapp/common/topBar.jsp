<%--
  Created by IntelliJ IDEA.
  User: MSI
  Date: 2023-07-11
  Time: 오후 6:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>


<%
    if (loginedMemberId == -1){
%>
<button style="margin-top: 20px"><a href="member/join">Join</a></button>
<button style="margin-top: 20px"><a href="member/login">Login</a></button>
<%
    }
%>

<%
    if (loginedMemberId != -1){
%>
<button style="margin-top: 20px"><a href="member/logout">Logout</a></button>
<%
    }
%>