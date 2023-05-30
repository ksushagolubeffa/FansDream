<!DOCTYPE html>
<html>
<head>
    <title>FANS-DREAM</title>
</head>
<body>
<h1>Редактирование медиаконтента</h1>
<form action="/media/edit/${content.uuid}" method="POST" enctype="multipart/form-data">
    <label for="previewImage">Фотография:</label>
    <input type="file" id="previewImage" name="previewImage" accept="image/*">
    <br>
<#--    <label for="video">Видео:</label>-->
<#--    <input type="file" id="video" name="video" accept="video/*"><br>-->
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" value="${content.name}">
    <br>
    <label for="description">Описание:</label>
    <textarea id="description" name="description">${content.description}</textarea>
    <br>
    <#if isAdmin>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Применить изменения</button>
    <#else>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Отправить на рассмотрение</button>
    </#if>
</form>
</body>
</html>
