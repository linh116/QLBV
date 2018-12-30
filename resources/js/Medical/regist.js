$('#btn-regist').on('click', function () {
    if ($('#name-patient').val() == ''){
        alert('Tên bệnh nhân không được bỏ trống.');
        return;
    }
    let patientGender = $('input[name="sex-patient"]')[0].checked == true ? 1 : 0;
    $.ajax(
        {
            method: "post",
            url: "/medical/regist",
            data: {
                patientId: $('#id-patient').val(),
                patientName: $('#name-patient').val(),
                patientGender: patientGender,
                patientAddress: $('#address-patient').val(),
                patientPhone: $('#phone-patient').val(),
                patientBD: $('#bd-patient').val(),
            },
            success: function (result) {
                let data = JSON.parse(result);
                alert("success");
                initTablePatientWaiting();
                resetForm();
            },
            error: function (result) {
                let data = JSON.parse(result.responseText);
                alert(data.data.errorMsg);
            }
        });
});

$('#btn-reset').on('click', function () {
    resetForm();
});

function resetForm() {
    $('#id-patient').val('');
    $('#name-patient').val('');
    $('input[name="sex-patient"]')[0].checked = true;
    $('#address-patient').val('');
    $('#phone-patient').val('');
    $('#bd-patient').val(new Date().customFormat('#DD#/#MM#/#YYYY#'));
}


function renderBNChoKham(listView) {
    function renderRow(view) {
        return '<a href="javascript:void(0)" class="list-group-item list-group-item-action">'+view.patientName+'</a>';
    }

    $('#list-patient-container').html('');
    var newHtml = '';
    for(var i = 0; i< listView.length; i++){
        newHtml += renderRow(listView[i]);
    }
    $('#list-patient-container').html(newHtml);
}
/*function showInfo(patientId) {
    //find info inlist
    for(var i = 0; i< _listBN.length; i++){
        if (_listBN[i].patientId = patientId){
            //set info
            $('#id-patient').text(_listBN[i].patientId);
            $('#name-patient').text(_listBN[i].patientName);
            $('#address-patient').text(_listBN[i].address);
            $('#phone-patient').text(_listBN[i].phone);
            $('#bd-patient').text(new Date(_listBN[i].birthday).customFormat('#DD#/#MM#/#YYYY#'));
        }
    }
}*/





var _listBN;
function initTablePatientWaiting(){
    $.ajax(
        {
            method: "post",
            url: "/medical",
            data: {
                action: 'fetch_request_patient',
            },
            success: function (result) {
                data = JSON.parse(result);
                if (data.data.listRequest == undefined){
                }else{
                    var listView = JSON.parse(data.data.listRequest);
                    _listBN = listView;
                    renderBNChoKham(listView);
                }
                console.log(data);
            },
            error: function (e) {
                console.log(e);
            }
        });
}

$('#btn-get-old-patient').on('click', function () {
    $('#searchNgaySinh').val('');
    var funcCallback = function (patient) {
        $('#id-patient').val(patient.patientId);
        $('#name-patient').val(patient.patientName);
        if (patient.gender == true) {
            $('input[name="sex-patient"]')[0].checked = true;
        }else{
            $('input[name="sex-patient"]')[1].checked = true;
        }
        $('#address-patient').val(patient.address);
        $('#phone-patient').val(patient.phone);
        $('#bd-patient').val(new Date(patient.birthday).customFormat('#DD#/#MM#/#YYYY#'));
    };
    showModalSearchPatient(funcCallback);
});



$( document ).ready(function() {
    initTablePatientWaiting();
});
