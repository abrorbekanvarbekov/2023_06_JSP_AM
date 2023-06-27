<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Map<String, Object>> articleListMap = (List<Map<String, Object>>)request.getAttribute("articleListMap");
%>

<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <h1>게시물 리스트 V5</h1>
    <ul>
        <%
            for (Map<String, Object> articleMap : articleListMap) {
        %>
        <li><%= articleMap.get("id")%>, <%= articleMap.get("regDate")%>,<a href="detail?id=<%=articleMap.get("id")%>"><%= articleMap.get("title")%></a></li>
        <% } %>
    </ul>
    <div><a href="../HomeMainServlet">GoTo Main Page</a></div>




    
<%--    <h1>게시물 리스트 V1</h1>--%>
<%--    <ul>--%>
<%--        <li><%= articleListMap.get(0).get("id")%>, <%= articleListMap.get(0).get("regDate")%>,<%= articleListMap.get(0).get("title")%></li>--%>
<%--        <li><%= articleListMap.get(1).get("id")%>, <%= articleListMap.get(1).get("regDate")%>,<%= articleListMap.get(1).get("title")%></li>--%>
<%--        <li><%= articleListMap.get(2).get("id")%>, <%= articleListMap.get(2).get("regDate")%>,<%= articleListMap.get(2).get("title")%></li>--%>
<%--    </ul>--%>

<%--    <h1>게시물 리스트 V2</h1>--%>
<%--    <ul>--%>
<%--        <%--%>
<%--            for (int i = 0; i < 3; i++) {--%>
<%--        %>--%>
<%--        <li><%= articleListMap.get(i).get("id")%>, <%= articleListMap.get(i).get("regDate")%>,<%= articleListMap.get(i).get("title")%></li>--%>
<%--        <% } %>--%>
<%--    </ul>--%>

<%--    <h1>게시물 리스트 V3</h1>--%>
<%--    <ul>--%>
<%--        <%--%>
<%--            for (int i = 0; i < 3; i++) {--%>
<%--                Map<String, Object> articleMap = articleListMap.get(i);--%>
<%--        %>--%>
<%--        <li><%= articleMap.get("id")%>, <%= articleMap.get("regDate")%>,<%= articleMap.get("title")%></li>--%>
<%--        <% } %>--%>
<%--    </ul>--%>

<%--    <h1>게시물 리스트 V4</h1>--%>
<%--    <ul>--%>
<%--        <%--%>
<%--            for (int i = 0; i < articleListMap.size(); i++) {--%>
<%--                Map<String, Object> articleMap = articleListMap.get(i);--%>
<%--        %>--%>
<%--        <li><%= articleMap.get("id")%>, <%= articleMap.get("regDate")%>,<%= articleMap.get("title")%></li>--%>
<%--        <% } %>--%>
<%--    </ul>--%>
</body>
</html>
