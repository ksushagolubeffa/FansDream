<!DOCTYPE html>
<html>
<head>
    <title>Список всех пользователей</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>Users</h1>
<ul id="user-list"></ul>
<script>
    $(document).ready(function() {
        $.ajax({
            url: "/users",
            type: "GET",
            dataType: "json",
            success: function(users) {
                $.each(users, function(index, user) {
                    $("#user-list").append("<li>" + user.username + "</li>" + user.state + "</li>" + user.role);
                });
            }
        });
    });
</script>
</body>
</html>