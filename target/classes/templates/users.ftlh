<!DOCTYPE html>
<html>
<#include "new-menu.ftlh">
<head>
    <title>User List</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

</head>
<body>
<#--<input type="hidden" name="_csrf" value="${_csrf.token}">-->
<#--<button type = "button" onclick="this" id="get-users">Get All Users</button>-->
<#--<div id="user-list"></div>-->
<div class="container">
    <h1>User List</h1>
    <table id="user-table" class="table">
        <thead>
        <tr>
            <th>ID</th>
            <th>Name</th>
            <th>Email</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
</div>
<#--<script>-->
<#--    $(document).ready(function() {-->
<#--        $('#get-users').click(function() {-->
<#--            $.ajax({-->
<#--                url: '/users',-->
<#--                type: 'GET',-->
<#--                success: function(data) {-->
<#--                    var userList = '';-->
<#--                    $.each(data, function(index, user) {-->
<#--                        userList += '<p>' + user.username + '</p>';-->
<#--                    });-->
<#--                    $('#user-list').html(userList);-->
<#--                }-->
<#--            });-->
<#--        });-->
<#--    });-->
<#--</script>-->
<script>
    $(document).ready(function() {
        $.ajax({
            url: "/users",
            type: "GET",
            dataType: 'html',
            success: function(data) {
                var tableBody = $("#user-table");
                $.each(data, function(index, user) {
                    var row = $("<tr>");
                    $("<td>").text(user.uuid).appendTo(row);
                    $("<td>").text(user.username).appendTo(row);
                    $("<td>").text(user.email).appendTo(row);
                    $("<td>").text(user.role).appendTo(row);
                    $("<td>").text(user.balance).appendTo(row);
                    $("<td>").text(user.state).appendTo(row);
                    row.appendTo(tableBody);
                });
            },
            error: function(xhr, status, error) {
                console.log("Error fetching users: " + error);
            }
        });
    });
</script>
</body>
</html>