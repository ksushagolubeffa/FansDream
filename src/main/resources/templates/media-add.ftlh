<!DOCTYPE html>
<html>
<body>
<#if isAdmin>
    <form method="POST" action="/media/add" enctype="multipart/form-data">
        <label for="name">Название:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="description">Описание:</label>
        <textarea id="description" name="description" required></textarea><br>

        <label for="previewImage">Фотография:</label>
        <input type="file" id="previewImage" name="previewImage"><br>

        <label for="video">Видео:</label>
        <input type="file" id="video" name="video"><br>

        <button type="submit">Добавить</button>
    </form>
<#else>
    <form method="POST" action="/media/add" enctype="multipart/form-data">
        <label for="name">Название:</label>
        <input type="text" id="name" name="name" required><br>

        <label for="description">Описание:</label>
        <textarea id="description" name="description" required></textarea><br>

        <label for="photo">Фотография:</label>
        <input type="file" id="photo" name="photo"><br>

        <label for="video">Видео:</label>
        <input type="file" id="video" name="video"><br>

        <button type="submit">Отправить на рассмотрение</button>
    </form>
</#if>
</body>
</html>