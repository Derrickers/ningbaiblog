function selectTag(tag){
    var previous = $("#tag").val();
    if (previous.indexOf(tag) === -1){
        if (previous)
            $("#tag").val(previous+"/"+tag);
        else
            $("#tag").val(tag);
    }
}

function showTags() {
    $("#tags").show();
}

function showPassword() {
    let classList = $("#showOrHide").get(0).classList;
    if(classList.contains("glyphicon-eye-open")){
        $("#showOrHide").removeClass("glyphicon-eye-open");
        $("#showOrHide").addClass("glyphicon-eye-close");
    }else{
        $("#showOrHide").removeClass("glyphicon-eye-close");
        $("#showOrHide").addClass("glyphicon-eye-open");
    }
    let type = $("#password").attr('type');
    if(type === "password"){
        $("#password").get(0).setAttribute("type","text");
    }else{
        $("#password").get(0).setAttribute("type","password");
    }
}

function chooseSex(index) {
    $("#sex-0").removeClass("mylabel-active");
    $("#sex-1").removeClass("mylabel-active");
    $("#sex-2").removeClass("mylabel-active");
    if(index === 0){
        $("#sex-0").addClass(" mylabel-active");
    }else if(index === 1){
        $("#sex-1").addClass("mylabel-active");
    }else if(index === 2){
        $("#sex-2").addClass("mylabel-active");
    }
}

function summitModify() {
    if (!$("#sex-0").get(0).classList.contains("mylabel-active")) {
        $("#sex--input-0").attr("disabled", "disabled");
    }
    if (!$("#sex-1").get(0).classList.contains("mylabel-active")) {
        $("#sex-input-1").attr("disabled", "disabled");
    }
    if (!$("#sex-2").get(0).classList.contains("mylabel-active")) {
        $("#sex-input-2").attr("disabled", "disabled");
    }
    $("#modify-form").submit();
}
