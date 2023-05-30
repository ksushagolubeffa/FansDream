<!DOCTYPE html>
<html>
<head>
    <title>User List</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h1>User List</h1>
<table>
    <thead>
    <tr>
        <th>Username</th>
        <th>Email</th>
        <th>Role</th>
        <th>Balance</th>
        <th>State</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody id="userlist">
    </tbody>
</table>
<#--<script>-->
<#--    $(document).ready(function() {-->
<#--        // Load user list on page load-->
<#--        $.getJSON('/users', function(json) {-->
<#--            var tr=[];-->
<#--            for (var i = 0; i < json.length; i++) {-->
<#--                tr.push('<tr>');-->
<#--                tr.push('<td>' + json[i].uuid + '</td>');-->
<#--                tr.push('<td>' + json[i].username + '</td>');-->
<#--                tr.push('<td>' + json[i].email + '</td>');-->
<#--                tr.push('<td>' + json[i].role + '</td>');-->
<#--                tr.push('<td>' + json[i].balance + '</td>');-->
<#--                tr.push('<td>' + json[i].state + '</td>');-->
<#--                // tr.push('<td>' + json[i].image + '</td>');-->
<#--                tr.push('</tr>');-->
<#--            }-->
<#--            $('table').append($(tr.join('')));-->
<#--        });-->

<#--        // Handle click on username-->
<#--        $(document).on('click', '.username', function() {-->
<#--            let uuid = $(this).data('uuid');-->
<#--            window.location.href = '/users/' + uuid;-->
<#--        });-->

<#--        // Handle click on block button-->
<#--        $(document).on('click', '.block-button', function() {-->
<#--            let uuid = $(this).data('uuid');-->
<#--            let state = $(this).data('state');-->
<#--            let newState = state == 'ACTIVE' ? 'NOT_ACTIVE' : 'ACTIVE';-->
<#--            $.ajax({-->
<#--                url: '/users/' + uuid + '/state',-->
<#--                type: 'PUT',-->
<#--                data: JSON.stringify({ state: newState }),-->
<#--                contentType: 'application/json',-->
<#--                success: function() {-->
<#--                    loadUserList();-->
<#--                }-->
<#--            });-->
<#--        });-->
<#--    });-->

<#--    // function loadUserList() {-->
<#--    //     $.get('/users', function(users) {-->
<#--    //         let html = '';-->
<#--    //         users.forEach(function(user) {-->
<#--    //             html += '<tr>';-->
<#--    //             html += '<td class="username" data-uuid="' + user.uuid + '">' + user.username + '</td>';-->
<#--    //             html += '<td>' + user.email + '</td>';-->
<#--    //             html += '<td>' + user.role + '</td>';-->
<#--    //             html += '<td>' + user.balance + '</td>';-->
<#--    //             html += '<td>' + user.state + '</td>';-->
<#--    //             html += '<td><button class="block-button" data-uuid="' + user.uuid + '" data-state="' + user.state + '">' + (user.state == 'ACTIVE' ? 'Block' : 'Unblock') + '</button></td>';-->
<#--    //             html += '</tr>';-->
<#--    //         });-->
<#--    //         $('#userlist').html(html);-->
<#--    //     });-->
<#--    // }-->

<#--    // $(document).ready(function() {-->
<#--    //     $.getJSON('http://localhost:8080/users', function(json) {-->
<#--    //         var tr=[];-->
<#--    //         for (var i = 0; i < json.length; i++) {-->
<#--    //             tr.push('<tr>');-->
<#--    //             tr.push('<td>' + json[i].uuid + '</td>');-->
<#--    //             tr.push('<td>' + json[i].username + '</td>');-->
<#--    //             tr.push('<td>' + json[i].email + '</td>');-->
<#--    //             tr.push('<td>' + json[i].role + '</td>');-->
<#--    //             tr.push('<td>' + json[i].balance + '</td>');-->
<#--    //             tr.push('<td>' + json[i].state + '</td>');-->
<#--    //             // tr.push('<td>' + json[i].image + '</td>');-->
<#--    //             tr.push('</tr>');-->
<#--    //         }-->
<#--    //         $('table').append($(tr.join('')));-->
<#--    //     });-->
<#--    //-->
<#--    //     });-->

<#--    // function loadUserList(){-->
<#--    //     $(document).ready(function() {-->
<#--    //         $.getJSON('/users', function(json) {-->
<#--    //             var tr=[];-->
<#--    //             for (var i = 0; i < json.length; i++) {-->
<#--    //                 tr.push('<tr>');-->
<#--    //                 tr.push('<td>' + json[i].uuid + '</td>');-->
<#--    //                 tr.push('<td>' + json[i].username + '</td>');-->
<#--    //                 tr.push('<td>' + json[i].email + '</td>');-->
<#--    //                 tr.push('<td>' + json[i].role + '</td>');-->
<#--    //                 tr.push('<td>' + json[i].balance + '</td>');-->
<#--    //                 tr.push('<td>' + json[i].state + '</td>');-->
<#--    //                 // tr.push('<td>' + json[i].image + '</td>');-->
<#--    //                 tr.push('</tr>');-->
<#--    //             }-->
<#--    //             $('table').append($(tr.join('')));-->
<#--    //         });-->
<#--    //-->
<#--    //     });-->
<#--       // $.ajax({method: "GET", url: '/users',})-->
<#--       //     .done(function (response){-->
<#--       //         alert(response)-->
<#--       //     })-->
<#--       //     .fail(function (){-->
<#--       //         alert("Error")-->
<#--       //     })-->
<#--    }-->
<#--</script>-->
</body>
</html>