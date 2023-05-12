<!DOCTYPE html>
<html>
<head>
    <title>FANS-DREAM</title>
</head>
<body>
<form method="POST" action="/profile/edit" enctype="multipart/form-data">
    <label for="avatar">Аватар:</label>
    <input type="file" id="image" name="image"><br>

    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" value="${user.username}" required><br>

    <label for="password">Пароль:</label>
    <input type="password" id="password" name="password" required><br>

    <button type="submit">Применить изменения</button>
</form>
</body>
</html>