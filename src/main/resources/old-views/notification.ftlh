<!DOCTYPE html>
<html>
<head>
    <title>Уведомления</title>
    <#include "../templates/new-menu.ftlh">
</head>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.0.0/css/bootstrap.min.css">
<h1>Уведомления</h1><hr>
<body>
<ul>
    <#list notifications as notification>
        <li>
            <td><a href="/media/${notification.content.uuid}">Контент: ${notification.content.name}</a></td>
            <br>
            <#if notification.message??>
            <td><a>${notification.message}</a></td>
            <#else>
                <td><a href="/users/${notification.author.uuid}">Автор комментария: ${notification.author.username}</a></td>
                <br>
                <td><a>${notification.comment.text}</a></td>
            </#if>
            <form method="POST" action="/profile/notifications/delete/${notification.uuid}" >
                <input type="hidden" name="_csrf" value="${_csrf.token}">
                <button type = "submit">Прочитано</button>
            </form>
        </li>
    </#list>
    <form method="POST" action="/profile/notifications" >
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <button type = "submit">Очистить уведомления</button>
    </form>
</ul>
</body>
</html>