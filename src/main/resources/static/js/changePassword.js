var form;
var passwordUrl = "profile/password/";

function makeEditable() {
    form = $('#passwordForm');
    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });
    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

}

function changePass() {
    makeEditable();
    $('#modalTitle').html("Password changing");
    form.find(":input").val("");
    $('#editPassword').modal();

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
}

function savePassword() {
    $.ajax({
        type: "POST",
        url: passwordUrl,
        data: form.serialize(),
        success: function () {
            $('#editPassword').modal('hide');
            successNoty("Password changed!");
        }
    });
}

var failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(key) {
    closeNoty();
    noty({
        text: key,
        type: 'success',
        layout: 'bottomRight',
        timeout: 2000
    });
}

function failNoty(jqXHR) {
    closeNoty();
    var errorInfo = $.parseJSON(jqXHR.responseText);
    failedNote = noty({
        text: errorInfo.detail,
        type: 'error',
        layout: 'bottomRight',
        timeout: 8000
    });
}
