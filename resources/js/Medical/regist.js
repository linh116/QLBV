$('#btn-regist').on('click', function () {
    if ($('#name-patient').val() == '') {
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
                patientJob: $('#job-patient').val(),
                patientNation: $('#nation-patient').val(),
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
    $('#job-patient').val('');
    $('#nation-patient').val('');
    $('#fee-doctor').val('');
    $('#prescription-code').val('');
}


function renderBNChoKham(listView) {
    function renderRow(view) {
        return '<a href="javascript:void(0)" class="list-group-item list-group-item-action">' + view.patientName + '</a>';
    }

    $('#list-patient-container').html('');
    var newHtml = '';
    for (var i = 0; i < listView.length; i++) {
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

function initTablePatientWaiting() {
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
                } else {
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
        } else {
            $('input[name="sex-patient"]')[1].checked = true;
        }
        $('#address-patient').val(patient.address);
        $('#phone-patient').val(patient.phone);
        $('#bd-patient').val(new Date(patient.birthday).customFormat('#DD#/#MM#/#YYYY#'));
        $('#job-patient').val(patient.job);
        $('#nation-patient').val(patient.nation);
    };
    showModalSearchPatient(funcCallback);
});


$(document).ready(function () {
    initTablePatientWaiting();
});

// find patient at modal common
//setup before find patient
var _tableSearchPatient;

function showModalSearchPatient(callbackFunc) {
    getAndShowPatientAtModal(callbackFunc);
    $('#modal-find-patient').modal('show');

    let typingTimer;                //timer identifier
    let doneTypingInterval = 400;  //time in ms, 0.4 second
    let $nameInput = $('#searchName');
    let $birthdayInput = $('#searchNgaySinh');
    //on keyup, start the countdown
    $nameInput.on('keyup', function () {
        clearTimeout(typingTimer);
        typingTimer = setTimeout(doneTyping, doneTypingInterval);
    });

    //on keydown, clear the countdown
    $nameInput.on('keydown', function () {
        clearTimeout(typingTimer);
    });

    //user is "finished typing," do something
    function doneTyping() {
        getAndShowPatientAtModal(callbackFunc);
    }

    $birthdayInput.on('change', function () {
        getAndShowPatientAtModal(callbackFunc);
    });

    function renderPatient(listView) {
        $('#table-find-patient-body').html('');
        let newHtml = '';
        for (let i = 0; i < listView.length; i++) {
            let view = listView[i];
            newHtml += '<tr id="patient-row-' + i + '">\n' +
                '<td>' + view.patientId + '</td>\n' +
                '<td>' + view.patientName + '</td>\n' +
                '<td>' + view.address + '</td>\n' +
                '<td>' + view.phone + '</td>\n' +
                '<td>' + new Date(view.birthday).customFormat('#DD#/#MM#/#YYYY#') + '</td>\n' +
                '</tr>';
        }
        $('#table-find-patient-body').html(newHtml);

    }

    function getAndShowPatientAtModal(callbackFunc) {
        let patientName = $('#searchName').val();
        let birthday = convertFromDateStrToLong($('#searchNgaySinh').val());

        $.ajax(
            {
                method: "post",
                url: "/common/findpatient",
                data: {
                    patientName: patientName,
                    birthday: birthday
                },
                success: function (result) {
                    if (_tableSearchPatient != undefined) {
                        _tableSearchPatient.dataTable().fnDestroy();
                    }
                    let data = JSON.parse(result);
                    let listView = JSON.parse(data.data.listPatient);
                    if (listView != undefined && listView != 'null') {
                        renderPatient(listView);
                        for (let i = 0; i < listView.length; i++) {
                            $('#patient-row-' + i).on('click', function () {
                                callbackFunc(listView[i]);
                                $('#modal-find-patient').modal('hide');
                            });
                        }
                    } else {
                        $('#table-find-patient-body').html('');
                    }
                    _tableSearchPatient = $('#table-find-patient').dataTable({
                        "searching": false,
                        "bInfo": false,
                        "language": {
                            "lengthMenu": 'Hiển thị _MENU_ dòng mỗi trang',
                        }
                    });

                    console.log(data);
                },
                error: function (e) {
                    console.log(e);
                }
            });
    }

    $('#clear-btn').on('click', function () {
        $('#searchNgaySinh').val('');
        getAndShowPatientAtModal(callbackFunc);
    })
}


