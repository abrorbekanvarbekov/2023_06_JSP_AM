
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>

    <script>
        function joinFormSubmit(form){
            form.userId.value = form.userId.value.trim();
            form.userPw.value = form.userPw.value.trim();

            if (form.userId.value.length == 0){
                alert("아이디를 입려해주세요!");
                form.userId.focus();
                return;
            }

            if (form.userPw.value.length == 0){
                alert("비밀번호를 입려해주세요!");
                form.userPw.focus();
                return;
            }

            form.submit();
        }
    </script>
<h1>회원 가입</h1>
    <form action="doLogin" method="get" onsubmit="joinFormSubmit(this); return false;">
        <label for="userId">UserId : </label>
        <input type="text" name="userId" id="userId">
        <br>
        <label for="userPw">UserPw : </label>
        <input type="password" name="userPw" id="userPw">
        <br>
        <button>JOin</button>
    </form>
    <button><a href="join">MemberJoin</a></button>
</body>
</html>
