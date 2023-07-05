<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Map<String, Object>> articleListMap = (List<Map<String, Object>>)request.getAttribute("articleListMap");
    int totalPage = (int) request.getAttribute("totalPage");
    int currentPage = (int) request.getAttribute("page");
    int from = (int) request.getAttribute("from");
    int end = (int) request.getAttribute("end");
    int loginedMemberId = (int) request.getAttribute("loginedMemberId");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>게시물 리스트</h1>

    <table border="1">
        <tr>
            <th>id</th>
            <th>regDate</th>
            <th>Title</th>
            <th>MemberId</th>
        </tr>
        <%
            for (Map<String, Object> articleMap : articleListMap) {
        %>
        <tr>
            <td><%= articleMap.get("id")%></td>
            <td><%= articleMap.get("regDate")%></td>
            <td><a href="detail?id=<%=articleMap.get("id")%>"><%= articleMap.get("title")%></a></td>
            <td><a href=""><%= articleMap.get("memberId")%></a></td>
        </tr>
        <% } %>
    </table>

    <style type="text/css">
        .paging > a.red{
            color: red;
            font-weight: bold;
            font-size: 1.5rem;
        }
    </style>

    <div class="paging">
        <% if (currentPage > 1) {%>
            <a href="list?page=1">◀</a>
        <%}%>

        <%
            for (int i = from; i <= end; i++) {
        %>
            <a class="<%=currentPage == i ? "red" : ""%>" href="list?page=<%=i%>"><%=i%></a>
        <%}%>

        <% if (currentPage < totalPage) {%>
            <a href="list?page=<%=totalPage%>">▶</a>
        <%}%>
    </div>

    <button style="margin-top: 20px"><a href="../HomeMainServlet">GoTo Main Page</a></button>

    <%
        if (loginedMemberId != -1){
    %>
            <button style="margin-top: 20px"><a href="write">GoTo Article Writer</a></button>
    <%
        }
    %>
</body>
</html>
