<!DOCTYPE html>
<html>
<head>
    <title>Редактирование продукта</title>
</head>
<body>
<h1>Редактирование продукта "${product.name}"</h1>
<form action="/products/edit/${product.uuid}" method="post" enctype="multipart/form-data">
    <label for="name">Название:</label>
    <input type="text" id="name" name="name" value="${product.name}">
    <br>
    <label for="description">Описание:</label>
    <textarea id="description" name="description" >${product.description}</textarea>
    <br>
    <label for="price">Цена:</label>
    <input type="number" id="price" name="price" value="${product.price}">
    <br>
    <label for="image">Фото:</label>
    <input type="file" id="image" name="image">
    <br>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
    <input type="submit" value="Подтвердить изменения">
</form>
</body>
</html>
