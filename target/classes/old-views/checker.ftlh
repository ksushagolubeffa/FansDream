<!DOCTYPE html>
<html>
<head>
    <title>Список медиаконтента</title>
</head>
<body>
<h1>Список медиаконтента</h1>
<table>
    <thead>
    <tr>
        <th>Название</th>
        <th>Картинка</th>
        <th>Автор</th>
    </tr>
    </thead>
    <tbody>
    <#list contentList as content>
        <tr>
            <td><a href="/media/${content.uuid}">${content.name}</a></td>
<#--            <td><a href="/content/${content}"><img src="${content.previewImage}" alt="${content.name}"></a></td>-->
            <td><a href="/users/${content.user.uuid}">${content.user.username}</a></td>
            <td>
                <form method="post" action="/profile/check/${content.uuid}/accept">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit">Принять</button>
                </form>
                <form method="post" action="/profile/check/${content.uuid}/reject">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <button type="submit">Отклонить</button>
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>