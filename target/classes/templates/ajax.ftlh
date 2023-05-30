<!doctype html>

<html>
<script>
    $.ajax({
        url: '/media/json',
        type: 'GET',
        dataType: 'json',
        success: function(data) {
            // Process the data and display it on the page
            for (var i = 0; i < data.length; i++) {
                var mediaContent = data[i];
                var previewImage = mediaContent.previewImage;
                var name = mediaContent.name;
                var author = mediaContent.user.username;

                // Create HTML elements for the preview image, name, and author
                var previewImageElement = $('<img>').attr('src', previewImage);
                var nameElement = $('<h2>').text(name);
                var authorElement = $('<p>').text('By ' + author);

                // Append the elements to the page
                $('#mediaContentList').append(previewImageElement, nameElement, authorElement);
            }
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // Handle the error
        }

    });
</script>
</html>