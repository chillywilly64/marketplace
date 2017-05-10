$(document).ready(function() {
        $('#buy_it_now').change(function(){
            if($(this).is(':checked') == true){
                $('#time_left').prop('disabled', true);
                $('#bid_inc').prop('disabled', true);
            } else {
                $('#time_left').prop('disabled', false);
                $('#bid_inc').prop('disabled', false);
            }
        });
    });