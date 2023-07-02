
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>게시글 작성</h1>
    <form action="doWrite" method="get">
        <label for="title">Title : </label>
        <input type="text" name="title" id="title">
        <br>
        <label for="body">Body : </label>
        <input type="text" name="body" id="body">
        <br>
        <button>작성</button>
    </form>
</body>
</html>
