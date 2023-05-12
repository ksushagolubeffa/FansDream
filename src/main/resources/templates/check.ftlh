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
    <#list contentList as mediaContent>
        <tr>
            <td><a href="/content/${mediaContent}">${mediaContent.name}</a></td>
            <td><a href="/content/${mediaContent}"><img src="${mediaContent.previewImage}" alt="${mediaContent.name}"></a></td>
            <td><a href="/author/${mediaContent.user}">${mediaContent.user.username}</a></td>
            <td>
                <form method="post" action="/profile/check">
                    <input type="hidden" name="content" value="${mediaContent}">
                    <button type="submit">Принять</button>
                </form>
                <form method="post" action="/profile/check">
                    <input type="hidden" name="content" value="${mediaContent}">
                    <button type="submit">Отклонить</button>
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
</body>
</html>