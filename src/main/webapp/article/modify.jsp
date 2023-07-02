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
    <h1><%= (int) article.get("id")%>번 게시글 수정</h1>

    <form action="doModify" method="POST">
        <input type="hidden" name="id" value="<%= (int) article.get("id")%>">
        <div>글번호 : <%= (int) article.get("id")%></div>
        <div>작성일  : <%= (LocalDateTime) article.get("regDate")%></div>
        <div>제목  :<input type="text" name="title" value="<%= (String) article.get("title")%>"></div>
        <div>
            내용  :<textarea name="body" ><%= (String) article.get("body")%></textarea>
        </div>
        <div>
            <button type="submit"><a >수정</a></button>
            <a href="detail?id=<%= (int) article.get("id")%>">취소</a>
        </div>
    </form>
</body>
</html>
