<!DOCTYPE html>
<html>
<head>
    <title>FANS-DREAM</title>
</head>
<body>
<h1>Редактирование медиаконтента</h1>
<form action="/media/${content}/edit" method="post">
    <label for="photo">Фотография:</label>
    <input type="file" id="photo" name="photo" accept="image/*">
    <br>
    <label for="video">Видео:</label>
    <input type="file" id="video" name="video"accept="video/*"><br>
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" value="${content.name}">
    <br>
    <label for="description">Описание:</label>
    <textarea id="description" name="description">${content.description}</textarea>
    <br>
    <#if isAdmin>
        <button type="submit">Применить изменения</button>
    <#else>
        <button type="submit">Отправить на рассмотрение</button>
    </#if>
</form>
</body>
</html>
