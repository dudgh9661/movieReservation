var main = {
    init : function() {
        var _this = this;
        $(".test td").on('click'), function() {
            console.log($(this).text());
        // $('#btn-confirm').on('click', function() {
        //     _this.confirm();
        // });
    }
    // confirm : function() {
    //     // var data = $('#btn-confirm').serialize();
    //     var data = {
    //         theaterId: $('#theaterId').val(),
    //         name: $('#name').val(),
    //         time: $('#time').val()
    //         // customerNumber: $('#customerNumber option:selected').val(),
    //         // seatNumber: $('#seatNumber option:selected').val()
    //     };
        //
        // $.ajax({
        //     type: 'POST',
        //     url: '/reservation-confirm',
        //     dataType: "text",
        //     contentType: 'application/json; charset=UTF-8',
        //     data: JSON.stringify(data)
        // }).done(function(data) {
        //     // alert('예약 확인 단계로 넘어갑니다.');
        //     alert(JSON.stringify(data));
        //     console.log(data);
        //     window.location.href='/reservation-confirm';
        // }).fail(function(error) {
        //     // alert("hhh");
        //     alert(JSON.stringify(error));
        //     console.log("HI");
        // });
    }
};

main.init();