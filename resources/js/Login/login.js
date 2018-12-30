$('#login-btn').on('click', function () {
    $.ajax(
        {
            method: "post",
            url: "/login",
            data: {
                username: $('#inputUsername').val(),
                password: $('#inputPassword').val()
            },
            success: function (result) {
                data = JSON.parse(result);
                if (data.data.user == undefined){
                    $('#messageError').show();
                }else{
                    $('#messageError').hide();
                    document.location.href = '/login/welcome';
                }
                console.log(data);
            },
            error: function (e) {
                console.log(e);
            }
        });
});