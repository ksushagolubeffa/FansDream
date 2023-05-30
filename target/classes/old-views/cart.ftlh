<!DOCTYPE html>
<html>
<head>
    <title>Корзина товаров</title>
    <#include "../templates/new-menu.ftlh">
</head>
<body>
<h1>Корзина товаров</h1>

    <table>
        <thead>
        <tr>
            <th>Фото</th>
            <th>Название</th>
            <th>Цена(руб.)</th>
            <th>Цена(дол.)</th>
            <th>Цена(евро)</th>
<#--            <th>Выбрать</th>-->
            <th>Удалить товар</th>
        </tr>
        </thead>
        <tbody>
        <#list orders as order>
            <tr>
                <td><img src="/products/${order.product.uuid}/image" alt="${order.product.name}"></td>
                <td>${order.product.name}</td>
                <td>${order.product.price} ₽</td>
                <td>${order.product.usdPrice} $</td>
                <td>${order.product.eurPrice} €</td>
<#--                <td><input type="checkbox" onclick="selectOrder(this, ${order.uuid})"></td>-->
                <td> <form action="/cart/${order.uuid}" method="POST">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit">Удалить товар</button>
                </form></td>
            </tr>
        </#list>
        </tbody>
    </table>
<#if count>
    <p>Итоговая сумма: ${total} рублей/ ${usd} долларов/ ${eur} евро</p>
    <form action="/cart" method="post">
        <td><input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Купить</button></td>
    </form>
    <td> <form action="/cart/deleteAll" method="post">
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <button type="submit">Удалить все</button>
        </form>
    </td>
</#if>
</body>
</html>