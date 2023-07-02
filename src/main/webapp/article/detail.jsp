<%@ page import="java.util.Map" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Map<String, Object> article = (Map<String, Object>) request.getAttribute("articleMap");
%>

<html>
<head>
    <title>Detail page</title>
</head>
<body>
    <h1><a href="list"><%= (int) article.get("id")%>번 게시글 상세보기</a></h1>

    <table border="1">
        <tr>
            <th>id</th>
            <th>regDate</th>
            <th>Title</th>
            <th>Body</th>
        </tr>
        <tr>
            <td><%= (int) article.get("id")%></td>
            <td><%= (LocalDateTime) article.get("regDate")%></td>
            <td><%= (String) article.get("title")%></td>
            <td><%= (String) article.get("body")%></td>
        </tr>
    </table>

    <button style="margin-top: 20px"><a href="doDelete?id=<%= (int) article.get("id")%>" onclick="if(confirm('정말 삭제하시겠습니까?') == false) return false;">Delete</a></button>
    <button style="margin-top: 20px"><a href="modify?id=<%= (int) article.get("id")%>">Modify</a></button>
    <button style="margin-top: 20px"><a href="list">List</a></button>
</body>
</html>
