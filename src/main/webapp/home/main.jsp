
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Main Page</h1>

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
    <button style="margin-top: 20px"><a href="article/list">GoTo Article List</a></button>
</body>
</html>
