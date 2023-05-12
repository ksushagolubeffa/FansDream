<!DOCTYPE html>
<html>
<head>
    <title>Детальная информация о контенте</title>
</head>
<body>
<h1>${content.name}</h1>
<p>${content.description}</p>
<p>${content.likes} лайков</p>
<p>Автор: <a href="/users/${content.user.getUuid()}">${content.user.username}</a></p>

<#if isAuthorOrAdmin>
    <form method="post" action="/media/${content}">
        <input type="hidden" name="_method" value="DELETE">
        <button type="submit">Удалить контент</button>
    </form>
    <div>
        <h3>Редактировать</h3>
        <a href="/media/edit">Редактировать</a>
    </div>
</#if>

<h2>Добавить комментарий</h2>
<form action="/media/${content}/addComment" method="POST">
    <label for="username">Имя пользователя:</label>
    <input type="text" id="username" name="username" value="${currentUser}" readonly>
    <br>
    <label for="text">Комментарий:</label>
    <textarea id="text" name="text" required></textarea>
    <br>
    <button type="submit">Добавить комментарий</button>
</form>

<h2>Комментарии</h2>
<ul>
    <#list comments as comment>
        <li>${comment.username}: ${comment.text}
            <#if currentUser == comment.username>
                <form action="/comments/${comment}/deleteComment" method="POST">
                    <input type="hidden" name="_method" value="DELETE">
                    <button type="submit">Удалить</button>
                </form>
            </#if>
        </li>
    </#list>
</ul>

<#if isLiked>
    <form action="/content/${content}/removeLike" method="POST">
        <input type="hidden" name="_method" value="DELETE">
        <button type="submit">Удалить лайк</button>
    </form>
<#else>
    <form action="/content/${content}" method="POST">
        <button type="submit">Поставить лайк</button>
    </form>
</#if>
</body>
</html>