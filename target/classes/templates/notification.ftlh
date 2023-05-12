<!DOCTYPE html>
<html>
<head>
    <title>Список уведомлений</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            // Загрузка списка уведомлений
            $.ajax({
                url: "/profile/notifications",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    // Отображение списка уведомлений
                    var notifications = data.notifications;
                    for (var i = 0; i < notifications.length; i++) {
                        var notification = notifications[i];
                        var message = notification.message ? notification.message : notification.username + " оставил комментарий: " + notification.comment;
                        var readButton = "<button onclick='markAsRead(" + notification.id + ")'>Прочитано</button>";
                        $("#notifications").append("<li>" + message + " " + readButton + "</li>");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Ошибка загрузки уведомлений: " + textStatus + " " + errorThrown);
                }
            });
        });

        // Отметка уведомления как прочитанного
        function markAsRead(notificationId) {
            $.ajax({
                url: "/profile/notifications/" + notificationId,
                type: "DELETE",
                success: function() {
                    // Удаление уведомления из списка
                    $("#notifications li").each(function() {
                        if ($(this).text().indexOf(notificationId) !== -1) {
                            $(this).remove();
                        }
                    });
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Ошибка отметки уведомления как прочитанного: " + textStatus + " " + errorThrown);
                }
            });
        }
    </script>
</head>
<body>
<h1>Список уведомлений</h1>
<ul id="notifications"></ul>
</body>
</html>