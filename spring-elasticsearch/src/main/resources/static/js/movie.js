$(document).ready( function () {
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