<!doctype html>
<#import "/templates/ajax.ftlh" as json>
<html>
<script>
    var data = ${getAllMediaContentsJson()?json_string};
    for (var i = 0; i < data.length; i++) {
        var mediaContent = data[i];
        for (var key in mediaContent) {
            if (mediaContent.hasOwnProperty(key)) {
                var value = mediaContent[key];
                var element = $('<p>').text(key + ': ' + value);
                $('#mediaContentList').append(element);
            }
        }
    }
</script>
</html>