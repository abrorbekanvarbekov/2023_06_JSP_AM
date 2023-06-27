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
        <div>id : <%= (int) article.get("id")%></div>
        <div>작성일 : <%= (LocalDateTime) article.get("regDate")%> </div>
        <div>제목  : <%= (String) article.get("title")%></div>
        <div>내용  : <%= (String) article.get("body")%> </div>
</body>
</html>
