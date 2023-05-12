<!DOCTYPE html>
<html>
<head>
    <title>Список медиаконтента</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('.media-content-link').click(function(event) {
                event.preventDefault();
                var url = $(this).attr('href');
                $.get(url, function(data) {
                    $('#content').html(data);
                });
            });
            $('.author-link').click(function(event) {
                event.preventDefault();
                var url = $(this).attr('href');
                $.get(url, function(data) {
                    $('#content').html(data);
                });
            });
        });
    </script>
</head>
<body>
<h1>Список медиаконтента</h1>
<div id="content">
    <table>
        <thead>
        <tr>
            <th>Название</th>
            <th>Картинка</th>
            <th>Автор</th>
        </tr>
        </thead>
        <tbody>
        <#list mediaContentList as mediaContent>
            <tr>
                <td><a href="/content/${mediaContent.id}" class="media-content-link">${mediaContent.title}</a></td>
                <td><a href="/content/${mediaContent.id}" class="media-content-link"><img src="${mediaContent.image}" alt="${mediaContent.title}"></a></td>
                <td><a href="/author/${mediaContent.authorId}" class="author-link">${mediaContent.authorName}</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</div>
</body>
</html>


