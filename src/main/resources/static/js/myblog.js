function selectTag(tag){
    var previous = $("#tag").val();
    if (previous.indexOf(tag) == -1){
        if (previous)
            $("#tag").val(previous+"/"+tag);
        else
            $("#tag").val(tag);
    }
}

function showTags() {
    $("#tags").show();
}