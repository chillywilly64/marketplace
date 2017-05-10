$(function(){
    $('.filterable .btn-filter').click(function(){
        var $panel = $(this).parents('.filterable'),
        $filters = $panel.find('.filters input'),
        $tbody = $panel.find('.table-responsive-responsive tbody');
        if ($filters.prop('disabled') == true) {
            $filters.prop('disabled', false);
            $filters.first().focus();
        } else {
            $filters.val('').prop('disabled', true);
            $tbody.find('.no-result').remove();
            $tbody.find('tr').show();
        }
    });

    $('.filterable .filters input').keyup(function(e){
        /* Ignore tab key */
        var code = e.keyCode || e.which;
        if (code == '9') return;
        /* Useful DOM data and selectors */
        var $input = $(this),
        inputContent = $input.val().toLowerCase(),
        $panel = $input.parents('.filterable'),
        column = $panel.find('.filters th').index($input.parents('th')),
        $table = $panel.find('.table-responsive'),
        $rows = $table.find('tbody tr');
        /* Dirtiest filter function ever ;) */
        var $filteredRows = $rows.filter(function(){
            var value = $(this).find('td').eq(column).text().toLowerCase();
            return value.indexOf(inputContent) === -1;
        });
        /* Clean previous no-result if exist */
        $table.find('tbody .no-result').remove();
        /* Show all rows, hide filtered ones (never do that outside of a demo ! xD) */
        $rows.show();
        $filteredRows.hide();
        /* Prepend no-result row if all rows are filtered */
        if ($filteredRows.length === $rows.length) {
            $table.find('tbody').prepend($('<tr class="no-result text-center"><td colspan="'+ $table.find('.filters th').length +'">No result found</td></tr>'));
        }
    });

    var showAllItems = $('#show-all-items');
    var showUsersItems = $('#show-users-items');

    showItems('all-items');

    showAllItems.click(function(e){
        showItems('all-items');
        e.preventDefault();
    });

    showUsersItems.click(function(e){
        showItems('my-items');
        e.preventDefault();
    });

    var table = $('.table-responsive');

    $('th')
        .wrapInner('<span title="sort this column"/>')
        .each(function(){

            var th = $(this),
                thIndex = th.index(),
                inverse = false;

            th.click(function(){

                table.find('td').filter(function(){

                    return $(this).index() === thIndex;

                }).sortElements(function(a, b){

                    if( $.text([a]) == $.text([b]) )
                        return 0;

                    return $.text([a]) > $.text([b]) ?
                        inverse ? -1 : 1
                        : inverse ? 1 : -1;

                }, function(){

                    return this.parentNode; 

                });

                inverse = !inverse;

            });

        }); 
});

function deleteItem(id) {
    $.post("delete-item", {id: id});
    $(("#items-body #" + id + " td")).remove();
};

function buyItem(id) {
   updateRow('buy-item', {id: id});
}

function bid(id) {
    var bid = $('#' + id + ' #bid').val();
    updateRow('bid', {id: id, bid: bid});
}

function updateRow(url, data) {
    $.getJSON(url, data).done(function (json) {
        $(("#items-body #" + data.id + " td")).remove();
        $("#itemRowTemplate").tmpl(json).appendTo("#items-body #" + data.id);
    }).fail(function(xhr, status, error){
        $("#error p").empty();
        $("#error p").append(xhr.responseText);
        $("#error").show();
    });
}

function showBids(id) {
    $('#bids-body tr').remove();
    $.getJSON('bids', {id: id}).done(function (json) {
        $("#bidsTemplate").tmpl(json).appendTo("#bids-body");
    });
}

function showItems(url) {
    $('#items-body tr').remove();
    $.getJSON(url, function(data){
        $("#itemsTemplate").tmpl(data).appendTo("#items-body");
    });
}