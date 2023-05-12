<!DOCTYPE html>
<html>
<head>
    <title>Список заказов пользователя</title>
</head>
<body>
<h1>Список заказов пользователя</h1>
<table>
    <thead>
    <tr>
        <th>Название продукта</th>
        <th>Дата заказа</th>
    </tr>
    </thead>
    <tbody>
    <#list products as product>
        <tr>
            <td>${product.image}</td>
            <td>${product.name}</td>
            <td>${product.price}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
