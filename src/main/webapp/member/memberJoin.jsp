
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<head>
    <title>Title</title>
</head>
<body>
    <script>
        function joinFormSubmit(form){
            form.userId.value = form.userId.value.trim();
            form.userPw.value = form.userPw.value.trim();
            form.userPwChk.value = form.userPwChk.value.trim();
            form.name.value = form.name.value.trim();

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

            if (form.userPwChk.value.length == 0){
                alert("비밀번호 확인을 입려해주세요!");
                form.userPwChk.focus();
                return;
            }

            if (form.name.value.length == 0){
                alert("이름을 입려해주세요!");
                form.name.focus();
                return;
            }

            if (form.userPw.value != form.userPwChk.value){
                alert("비밀번호가 일치하지 않습니다!");
                form.userPw.value = null;
                form.userPwChk.value = null;
                form.userPw.focus();
                return;
            }

            form.submit();
        }
    </script>

  <h1>회원 가입</h1>
      <form action="doJoin" method="get" onsubmit="joinFormSubmit(this); return false;">
          <label for="userId">UserId : </label>
          <input type="text" name="userId" id="userId">
          <br>
          <label for="userPw">UserPw : </label>
          <input type="password" name="userPw" id="userPw">
          <br>
          <label for="userPwChk">pwCheck : </label>
          <input type="password" name="userPwChk" id="userPwChk">
          <br>
          <label for="name">Name : </label>
          <input type="text" name="name" id="name">
          <br>
          <button>JOin</button>
      </form>
      <button><a href="login">MemberJoin</a></button>
</body>
</html>
