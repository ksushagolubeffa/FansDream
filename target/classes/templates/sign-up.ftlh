<!DOCTYPE html>
<html>
<head>
    <title>FANS-DREAM</title>
</head>
<body>
<form method="POST" action="/signUp">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" required><br>
    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required><br>
    <label for="image">Фотография:</label>
    <input type="file" id="image" name="image"><br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>
<a href="/signIn">Войти</a>
</body>
</html>