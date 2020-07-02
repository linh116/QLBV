"use strict";
//*** This code is copyright 2002-2016 by Gavin Kistner, !@phrogz.net
//*** It is covered under the license viewable at http://phrogz.net/JS/_ReuseLicense.txt
Date.prototype.customFormat = function(formatString){
    let YYYY,YY,MMMM,MMM,MM,M,DDDD,DDD,DD,D,hhhh,hhh,hh,h,mm,m,ss,s,ampm,AMPM,dMod,th;
    YY = ((YYYY=this.getFullYear())+"").slice(-2);
    MM = (M=this.getMonth()+1)<10?('0'+M):M;
    MMM = (MMMM=["January","February","March","April","May","June","July","August","September","October","November","December"][M-1]).substring(0,3);
    DD = (D=this.getDate())<10?('0'+D):D;
    DDD = (DDDD=["Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"][this.getDay()]).substring(0,3);
    th=(D>=10&&D<=20)?'th':((dMod=D%10)==1)?'st':(dMod==2)?'nd':(dMod==3)?'rd':'th';
    formatString = formatString.replace("#YYYY#",YYYY).replace("#YY#",YY).replace("#MMMM#",MMMM).replace("#MMM#",MMM).replace("#MM#",MM).replace("#M#",M).replace("#DDDD#",DDDD).replace("#DDD#",DDD).replace("#DD#",DD).replace("#D#",D).replace("#th#",th);
    h=(hhh=this.getHours());
    if (h==0) h=24;
    if (h>12) h-=12;
    hh = h<10?('0'+h):h;
    hhhh = hhh<10?('0'+hhh):hhh;
    AMPM=(ampm=hhh<12?'am':'pm').toUpperCase();
    mm=(m=this.getMinutes())<10?('0'+m):m;
    ss=(s=this.getSeconds())<10?('0'+s):s;
    return formatString.replace("#hhhh#",hhhh).replace("#hhh#",hhh).replace("#hh#",hh).replace("#h#",h).replace("#mm#",mm).replace("#m#",m).replace("#ss#",ss).replace("#s#",s).replace("#ampm#",ampm).replace("#AMPM#",AMPM);
};

// date range picker common
$('.daterange-picker-common').daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    locale: {
        "format": "DD/MM/YYYY",
        "applyLabel": "Chọn",
        "cancelLabel": "Hủy",
        "weekLabel": "W",
        "daysOfWeek": [
            "CN",
            "T2",
            "T3",
            "T4",
            "T5",
            "T6",
            "T7"
        ],
        "monthNames": [
            "Tháng 1",
            "Tháng 2",
            "Tháng 3",
            "Tháng 4",
            "Tháng 5",
            "Tháng 6",
            "Tháng 7",
            "Tháng 8",
            "Tháng 9",
            "Tháng 10",
            "Tháng 11",
            "Tháng 12"
        ],
        "firstDay": 1
    },
});

function convertFromDateStrToLong(dateStr){
    if (dateStr != undefined && dateStr != ''){
        let dateSplit = dateStr.split("/");
        if (dateSplit.length == 3){
            return new Date(dateSplit[1] +"/" + dateSplit[0] + "/" + dateSplit[2]).getTime();
        }else
            return 0;
    }
}

Function.prototype.clone = function() {
    let that = this;
    let temp = function temporary() { return that.apply(this, arguments); };
    for(let key in this) {
        if (this.hasOwnProperty(key)) {
            temp[key] = this[key];
        }
    }
    return temp;
};


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
    function doneTyping () {
        getAndShowPatientAtModal(callbackFunc);
    }

    $birthdayInput.on('change', function () {
        getAndShowPatientAtModal(callbackFunc);
    });

    function renderPatient(listView) {
        $('#table-find-patient-body').html('');
        let newHtml = '';
        for (let i = 0; i< listView.length; i++){
            let view = listView[i];
            newHtml += '<tr id="patient-row-'+i+'">\n' +
                '<td>'+view.patientId+'</td>\n' +
                '<td>'+view.patientName+'</td>\n' +
                '<td>'+view.address+'</td>\n' +
                '<td>'+view.phone+'</td>\n' +
                '<td>'+new Date(view.birthday).customFormat('#DD#/#MM#/#YYYY#')+'</td>\n' +
                '</tr>';
        }
        $('#table-find-patient-body').html(newHtml);

    }
    function getAndShowPatientAtModal(callbackFunc){
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
                    if (_tableSearchPatient != undefined){
                        _tableSearchPatient.dataTable().fnDestroy();
                    }
                    let data = JSON.parse(result);
                    let listView = JSON.parse(data.data.listPatient);
                    if (listView != undefined && listView != 'null'){
                        renderPatient(listView);
                        for (let i = 0; i < listView.length; i++){
                            $('#patient-row-'+i).on('click', function () {
                                callbackFunc(listView[i]);
                                $('#modal-find-patient').modal('hide');
                            });
                        }
                    }else{
                        $('#table-find-patient-body').html('');
                    }
                    _tableSearchPatient = $('#table-find-patient').dataTable({
                        "searching": false,
                        "bInfo" : false,
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




