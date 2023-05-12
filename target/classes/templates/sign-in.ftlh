<!DOCTYPE html>
<html>
<head>
    <title>FANS-DREAM</title>
</head>
<body>
<h1>Фан сервис</h1><hr>
<h4>Авторизация</h4>
<form action="/signIn" method="post">
    Имя пользователя: <input name="username" type="username"><br><br>
    Пароль: <input type="password" name="password"><br><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Вход"/>
</form>
<a href="/signUp">Зарегистрироваться</a>
</body>
</html>