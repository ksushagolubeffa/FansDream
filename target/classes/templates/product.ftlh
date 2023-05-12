<!DOCTYPE html>
<html>
<head>
    <title>Список продуктов</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script>
        $(document).ready(function() {
            // Загрузка списка продуктов
            $.ajax({
                url: "/products",
                type: "GET",
                dataType: "json",
                success: function(data) {
                    // Отображение списка продуктов
                    var products = data.products;
                    for (var i = 0; i < products.length; i++) {
                        var product = products[i];
                        // var image = "<img src='" + product.image + "' alt='" + product.name + "' width='100' height='100'>";
                        var name = "<a href='#' onclick='showProduct(" + product + ")'>" + product.name + "</a>";
                        $("#products").append("<li>" + image + " " + name + " " + product.price + " руб.</li>");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Ошибка загрузки продуктов: " + textStatus + " " + errorThrown);
                }
            });
        });

        // Показ детальной информации о продукте
        function showProduct(product) {
            $.ajax({
                url: "/products/" + product,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    // Отображение детальной информации о продукте
                    var product = data.product;
                    var image = "<img src='" + product.image + "' alt='" + product.name + "' width='200' height='200'>";
                    var name = "<h2>" + product.name + "</h2>";
                    var description = "<p>" + product.description + "</p>";
                    var price = "<p>" + product.price + " руб.</p>";
                    $("#product-details").html(image + name + description + price);
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.log("Ошибка загрузки детальной информации о продукте: " + textStatus + " " + errorThrown);
                }
            });
        }
    </script>
</head>
<body>
<h1>Список продуктов</h1>
<ul id="products"></ul>
<div id="product-details"></div>
</body>
</html>