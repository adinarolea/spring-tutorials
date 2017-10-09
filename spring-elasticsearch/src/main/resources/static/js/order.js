$(document).ready( function () {
    $.ajax(
    {
        type: "GET",
        headers: {Accept: 'application/json'},
        dataType: 'json',
        url: "/orders,
        success: function(data) {
            $("#orders-table").bootstrapTable('load', data);
        }
    });
});