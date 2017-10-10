$(document).ready( function () {
    $("#movies-table").bootstrapTable({
        cache: false,
        striped: true,
        pagination: true,
        pageSize: 5,
        url: "/movies",
        sidePagination:'server',
        pagination:true,
        queryParamsType : '',
        pageList: [5, 10, 25, 50, 100, 200]

    });
});