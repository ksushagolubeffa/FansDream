<!DOCTYPE html>
<html>
<head>
    <title>Детальная информация о контенте</title>
</head>
<body>
<h1>${content.name}</h1>
<video width="320" height="240" controls>
    <source src="/media/${content.uuid}/video" type="${content.video.contentType}"/>
</video>
<p>${content.description}</p>
<p>${likes} лайков</p>
<p>Дата создания: ${content.date}</p>
<p>Автор: <a href="/users/${content.user.getUuid()}">${content.user.username}</a></p>

<#if isAuthor || isAdmin>
    <form method="post" action="/media/${content.uuid}/delete">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Удалить контент</button>
    </form>
    <div>
        <h3>Редактировать</h3>
        <a href="/media/edit/${content.uuid}">Редактировать</a>
    </div>
</#if>

<#if isAuth || isAdmin>
<h2>Добавить комментарий</h2>
<form action="/media/${content.uuid}/addComment" method="POST">
    <br>
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
                <form action="/media/${content.uuid}/deleteComment" method="POST">
                    <input type="hidden" name="commentId" value="${comment.uuid}">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit">Удалить</button>
                </form>
            </#if>
        </li>
    </#list>
</ul>

<#if isAuthor || isAdmin>
<#if isLiked>
    <form action="/media/${content.uuid}/removeLike" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Удалить лайк</button>
    </form>
<#else>
    <form action="/media/${content.uuid}/addLike" method="POST">
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type="submit">Поставить лайк</button>
    </form>
</#if>
</#if>
</body>
</html>