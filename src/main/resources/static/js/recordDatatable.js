var serverUrl = "user/records/";
var datatableApi;

$(function () {
    datatableApi = $("#datatable").DataTable({
        "ajax": {
            "url": serverUrl,
            "dataSrc": ""
        },
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "surName"
            },
            {
                "data": "phone"
            },
            {
                "data": "email",
                "render": function (data, type, row) {
                    if (type === 'display') {
                        return '<a href="mailto:' + data + '">' + data + '</a>';
                    }
                    return data;
                }
            },
            {
                "render": renderEditBtn,
                "defaultContent": "",
                "orderable": false
            },
            {
                "render": renderDeleteBtn,
                "defaultContent": "",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "desc"
            ]
        ],

        "initComplete": makeEditable
    });

});