function renderBNChoKham(listView) {
    function renderRow(view) {
        return '<a onclick="showInfo(' + view.patientId + ')" href="javascript:void(0)" class="list-group-item list-group-item-action">' + view.patientName + '</a>';
    }

    $('#list-patient-container').html('');
    var newHtml = '';
    for (var i = 0; i < listView.length; i++) {
        newHtml += renderRow(listView[i]);
    }
    $('#list-patient-container').html(newHtml);
}

let onProcessDiagnose = false;

function showInfo(patientId) {
    if (onProcessDiagnose) return;
    //find info inlist
    for (var i = 0; i < _listBN.length; i++) {
        if (_listBN[i].patientId == patientId) {
            //set info
            $('#id-request').val(_listBN[i].requestId);
            $('#id-patient').text(_listBN[i].patientId);
            $('#name-patient').text(_listBN[i].patientName);
            if (_listBN[i].gender == true) {
                $('#patient-gender').text('Nam');
            } else {
                $('#patient-gender').text('Nữ');
            }
            $('#address-patient').text(_listBN[i].address);
            $('#phone-patient').text(_listBN[i].phone);
            $('#job-patient').text(_listBN[i].address);
            $('#nation-patient').text(_listBN[i].phone);
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

//setup before find patient
var _tableHistoryPatient;
$('#btn-history').on('click', function () {
    //get history of patients
    debugger;
    $.ajax(
        {
            method: "post",
            url: "/medical",
            data: {
                action: 'fetch_history_patient',
                patientId: $('#id-patient').text(),
            },
            success: function (result) {
                console.log(result);
                if (_tableHistoryPatient != undefined) {
                    _tableHistoryPatient.dataTable().fnDestroy();
                }
                let data = JSON.parse(result);
                let listView = JSON.parse(data.data.listMedicalRecord);
                if (listView != null) {
                    renderHistory(listView);
                    $('#modal-history-patient').modal('show');
                } else {
                    alert("Không có bệnh án")
                }
            },
            error: function (e) {
                alert("Có lỗi xảy ra!")
            }
        }
    );

    function renderHistory(listView) {
        $('#table-history-patient-body').html('');
        let newHtml = '';
        for (let i = 0; i < listView.length; i++) {
            let view = listView[i];
            newHtml += '<tr id="patient-row-' + i + '">\n' +
                '<td>' + new Date(view.createdDtm).customFormat('#DD#/#MM#/#YYYY#') + '</td>\n' +
                '<td>' + view.symptom + '</td>\n' +
                '<td>' + view.guess + '</td>\n' +
                '<td>' + view.note + '</td>\n' +
                '</tr>';
        }
        $('#table-history-patient-body').html(newHtml);

    }

});


$('#btn-save-diagnose').on('click', function () {
    $.ajax(
        {
            method: "post",
            url: "/medical",
            data: {
                action: 'save_diagnose',
                requestId: $('#id-request').val(),
                patientId: $('#id-patient').text(),
                guess: $('#guess-sick').val(),
                reason: $('#reason-sick').val(),
                symptom: $('#symptom-sick').val(),
                note: $('#note-sick').val(),
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
$(document).ready(function () {
    fetchRequestPatientList();
    setInterval(function () {
        fetchRequestPatientList(true);
    }, 3000);

});

function fetchRequestPatientList(withInteval) {
    $.ajax(
        {
            method: "post",
            url: "/medical",
            data: {
                action: 'fetch_request_patient',
            },
            success: function (result) {
                data = JSON.parse(result);
                if (data.data.listRequest == undefined) {
                    if (withInteval) {
                        console.log("Hiện không có bệnh nhân nào đang chờ khám");
                    } else {
                        alert("Hiện không có bệnh nhân nào đang chờ khám");
                    }
                } else {
                    var listView = JSON.parse(data.data.listRequest);
                    _listBN = listView;
                    renderBNChoKham(listView);
                }
            },
            error: function (e) {
                console.log(e);
            }
        });
}
