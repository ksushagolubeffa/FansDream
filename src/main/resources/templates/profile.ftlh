<!DOCTYPE html>
<html>
<body>
<div>
<#--    <img src="${user.image}" alt="Аватар">-->
    <h2>${user.username}</h2>
    <p>Баланс: ${user.balance}</p>
    <a href="/profile/edit">Редактировать</a>
</div>

<div>
    <h3>Уведомления</h3>
    <a href="/profile/notifications">Посмотреть все уведомления</a>
</div>

<div>
    <h3>Лайки</h3>
    <a href="/profile/likes">Посмотреть все лайки</a>
</div>

<div>
    <h3>Медиаконтент</h3>
    <a href="/profile/media">Посмотреть весь медиаконтент</a>
</div>

<div>
    <h3>Заказы</h3>
    <a href="/profile/orders">Посмотреть все заказы</a>
</div>
<#if isAdmin>
    <div>
        <h3>Все пользователи</h3>
        <a href="/users">Посмотреть всех пользователей</a>
    </div>
    <div>
        <h3>Заявки на публикацию</h3>
        <a href="/profile/check">Посмотреть все заявки</a>
    </div>
</#if>
<form action="/logout" method="post">
    <button type="submit">Выйти из аккаунта</button>
</form>
<br>
<form action="/profile" method="post">
    <button type="submit">Удалить страницу</button>
</form>
</body>
</html>