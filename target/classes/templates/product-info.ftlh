<!DOCTYPE html>
<html>
<head>
    <title>Детальная информация о товаре</title>
</head>
<body>
<h1>${product.name}</h1>
<p>${product.image}</p>
<p>${product.description}</p>
<p>${product.price}</p>

<h2>Добавить комментарий</h2>
<form method="post" action="/products/${product}/addComment">
    <label for="username">Имя пользователя:</label>
    <input type="text" name="username">
    <br>
    <label for="comment">Комментарий:</label>
    <textarea name="comment"></textarea>
    <br>
    <button type="submit">Добавить комментарий</button>
</form>

<h2>Комментарии</h2>
<ul>
    <#list comments as comment>
        <li>${comment.username}: ${comment.text}
            <#if currentUser == comment.username>
                <form action="/products/${product}/deleteComment" method="POST">
                    <input type="hidden" name="_method" value="DELETE">
                    <button type="submit">Удалить</button>
                </form>
            </#if>
        </li>
    </#list>
</ul>

<#if isAdmin>
    <div>
        <h3>Редактировать</h3>
        <a href="/media/edit">Редактировать</a>
    </div>

    <form method="post" action="/products/${product}">
        <input type="hidden" name="_method" value="DELETE">
        <button type="submit">Удалить товар</button>
    </form>
</#if>

<h2>Добавить товар в корзину</h2>
<form method="post" action="/products/${product}">
    <input type="hidden" name="product" value="${product}">
    <button type="submit">Добавить в корзину</button>
</form>
</body>
</html>