<!DOCTYPE html>
<html>
<head>
    <title>Список продуктов</title>
</head>
<body>
<h1>Список продуктов</h1>
<table>
    <thead>
    <tr>
        <th>Название</th>
        <th>Описание</th>
        <th>Цена</th>
    </tr>
    </thead>
    <tbody>
    <#list products as product>
        <tr>
            <td>${product.name}</td>
            <td>${product.description}</td>
            <td>${product.price}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
