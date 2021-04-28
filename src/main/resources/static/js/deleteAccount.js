var profileUrl = "profile/";

function deleteModal() {
    $('#confirmDelete').modal();
    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });

}

function deleteAccount() {
    $.ajax({
        type: 'DELETE',
        url: profileUrl,
        success: function () {
            // $('#confirmDelete').modal('hide');
            window.setTimeout(function() {
                window.location.replace("logout");
            }, 1500);
        }
    });
}
