$(document).ready( function () {
    $("#movies-table").bootstrapTable({
        cache: false,
        striped: true,
        pagination: true,
        pageSize: 5,
        pageList: [5, 10, 25, 50, 100, 200]

    });
    $.ajax(
    {
        type: "GET",
        headers: {Accept: 'application/json'},
        dataType: 'json',
        url: "/movies",
        success: function(data) {
            $("#movies-table").bootstrapTable('load', data);
        }
    });
});