
function renderBNChoKham(listView) {
    function renderRow(view) {
        return '<a onclick="showInfo('+view.patientId+')" href="javascript:void(0)" class="list-group-item list-group-item-action">'+view.patientName+'</a>';
    }

    $('#list-patient-container').html('');
    var newHtml = '';
    for(var i = 0; i< listView.length; i++){
        newHtml += renderRow(listView[i]);
    }
    $('#list-patient-container').html(newHtml);
}
let onProcessDiagnose = false;
function showInfo(patientId) {
    if (onProcessDiagnose) return;
    //find info inlist
    for(var i = 0; i< _listBN.length; i++){
        if (_listBN[i].patientId == patientId){
            //set info
            $('#id-request').val(_listBN[i].requestId);
            $('#id-patient').text(_listBN[i].patientId);
            $('#name-patient').text(_listBN[i].patientName);
            if (_listBN[i].gender == true) {
                $('#patient-gender').text('Nam');
            }else{
                $('#patient-gender').text('Nữ');
            }
            $('#address-patient').text(_listBN[i].address);
            $('#phone-patient').text(_listBN[i].phone);
            $('#bd-patient').text(new Date(_listBN[i].birthday).customFormat('#DD#/#MM#/#YYYY#'));
        }
    }
}

$('#btn-khambenh').on('click', function () {
    if ($('#id-patient').text() == '') return;
    onProcessDiagnose = true;
    $('#btn-khambenh').attr('disabled', 'disabled');
    $('#btn-save-diagnose').removeAttr('disabled');
});

$('#btn-save-diagnose').on('click', function () {
    $.ajax(
        {
            method: "post",
            url: "/medical",
            data: {
                action: 'save_diagnose',
                requestId : $('#id-request').val(),
                patientId : $('#id-patient').text(),
                guess : $('#guess-sick').val(),
                reason : $('#reason-sick').val(),
                symptom : $('#symptom-sick').val(),
                note : $('#note-sick').val(),
            },
            success: function (result) {
                location.reload();
            },
            error: function (e) {
                alert("Có lỗi xảy ra!")
            }
        });
});


var _listBN;
$( document ).ready(function() {
    fetchRequestPatientList();
});

function fetchRequestPatientList() {
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
                    alert("Hiện không có bệnh nhân nào đang chờ khám");
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
