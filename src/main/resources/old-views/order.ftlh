<!DOCTYPE html>
<html>
<head>
    <#include "../templates/new-menu.ftlh">
    <title>Список заказов пользователя</title>
</head>
<body>
<h1>Список заказов пользователя</h1>
<table>
    <thead>
    <tr>
        <th>Название продукта</th>
        <th>Цена продукта (руб.)</th>
        <th>Цена продукта (дол.)</th>
        <th>Цена продукта (ев.)</th>
        <th>Дата заказа</th>
    </tr>
    </thead>
    <tbody>
    <#list products as product>
        <tr>
<#--            <td>${product.image}</td>-->
            <td><a href="/products/${product.uuid}">${product.name}</a></td>
            <td>${product.price} ₽</td>
            <td>${product.usdPrice} $</td>
            <td>${product.eurPrice} €</td>
            <td>${product.date}</td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>
