<!DOCTYPE html>
<html>
<body>
    <form action="/products/add" method="POST" enctype="multipart/form-data">
        <label for="name">Название:</label>
        <input type="text" id="name" name="name" required>
        <br>
        <label for="description">Описание:</label>
        <textarea id="description" name="description" required></textarea>
        <br>
        <label for="price">Цена:</label>
        <input type="number" id="price" name="price" required>
        <br>
        <label for="image">Фотография:</label>
        <input type="file" id="image" name="image" accept="image/*" required>
        <br>
        <button type="submit">Добавить продукт</button>
    </form>
    </body>
</html>
