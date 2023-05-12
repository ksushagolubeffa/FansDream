<!DOCTYPE html>
<html>
<head>
    <title>Корзина товаров</title>
</head>
<body>
<h1>Корзина товаров</h1>
<form action="/cart" method="post">
    <table>
        <thead>
        <tr>
            <th>Фото</th>
            <th>Название</th>
            <th>Цена</th>
            <th>Выбрать</th>
        </tr>
        </thead>
        <tbody>
        <#list products as item>
            <tr>
                <td><img src="${item.image}" alt="${item.imageName}"></td>
                <td>${item.name}</td>
                <td>${item.price}</td>
                <td><input type="checkbox" name="selectedItems" value="${item}"></td>
            </tr>
            <form action="/cart" method="POST">
                <input type="hidden" name="_method" value="DELETE">
                <button type="submit">Удалить товар</button>
            </form>
        </#list>
        </tbody>
    </table>
    <p>Итоговая сумма: ${total}</p>
    <button type="submit">Купить</button>
</form>
</body>
</html>