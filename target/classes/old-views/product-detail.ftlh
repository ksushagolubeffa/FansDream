<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Детальная информация о товаре</title>
</head>
<body>
<h1>${product.name}</h1>
<img src="/products/${product.uuid}/image" alt="${product.name}">
<p>${product.description}</p>
<p>${product.price} ₽</p>
<p>${product.usdPrice} $</p>
<p>${product.eurPrice} €</p>
<p>Дата создания: ${product.date}</p>

<#if isAdmin || isAuth>
    <h2>Добавить комментарий</h2>
    <form method="post" action="/products/${product.uuid}/addComment">
        <label for="comment">Комментарий:</label>
        <textarea name="comment"></textarea>
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Добавить комментарий</button>
    </form>
</#if>

<h2>Комментарии</h2>
<ul>
    <#list comments as comment>
        <li>${comment.username}: ${comment.text}
            <br> ${comment.date}
            <#if (isAuth || isAdmin) && currentUser.username == comment.username>
                <form action="/products/${product.uuid}/deleteComment" method="POST">
                    <input type="hidden" name="commentId" value="${comment.uuid}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit">Удалить</button>
                </form>
            </#if>
        </li>
    </#list>
</ul>

<#if isAdmin>
    <div>
        <h3>Редактировать</h3>
        <a href="/products/edit/${product.uuid}">Редактировать</a>
    </div>
    <br><br>
    <form method="post" action="/products/${product.uuid}/delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Удалить товар</button>
    </form>
</#if>

<#if isAdmin || isAuth>
    <h2>Добавить товар в корзину</h2>
    <form method="post" action="/products/${product.uuid}/addToCart">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Добавить в корзину</button>
    </form>
</#if>
</body>
</html>