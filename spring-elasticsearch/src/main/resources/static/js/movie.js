function deleteButtonFormatter(value, row, index){
    var movieId = row.id;
    return "<button class=\"btn btn-danger btn-xs\" data-title=\"Delete\" data-toggle=\"modal\" onclick=\"deleteMovie(\'"+movieId+"\')\" ><span class=\"glyphicon glyphicon-trash\"></span></button>"
}

function deleteMovie(id){
    $.ajax({
        url: "/remove/movie?id="+ id,
        type: "POST",
        cache: false,
        error: function (e) {
            alert(e);
        },
        success: function () {
            var table = $('#movie-table');
            table.bootstrapTable('remove', {
                field: 'id',
                values: [id]
            });
        }
    });
}
$(document).ready( function () {
    $("#movie-table").bootstrapTable({
        cache: false,
        striped: true,
        pagination: true,
        refresh: true,
        showRefresh:true,
        pageSize: 5,
        url: "/movies",
        sidePagination:'server',
        pagination:true,
        queryParamsType : '',
        pageList: [5, 10, 25, 50]

    });
});