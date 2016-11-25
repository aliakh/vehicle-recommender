$(document).ready(function () {
  initSSE();
  initLoadFilter();
});

function initSSE() {

    if (!!window.EventSource) {
        var eventSource = new EventSource("/source/load/sse");

        function add(message) {
            console.log(message);
        }

        eventSource.onmessage = function (e) {
            var event = JSON.parse(e.data);
            var progress = event.progress
            var value = Math.floor(100*progress.cur/progress.max);
            var leftTime = progress.leftTime;
            var remindedTime = progress.remindedTime;

            $('.progress-bar').css('width', value+'%').attr('aria-valuenow', value).text(value + '%');;
            add(progress.cur + " / " + progress.max + " items " + value + "% " + formatTime(leftTime) + " / " + formatTime(remindedTime) + " min.");
        };

        eventSource.onopen = function (e) {
            add('connection was opened');
        };

        eventSource.onerror = function (e) {
            if (e.readyState == EventSource.CONNECTING) {
                add('event: CONNECTING');
            } else if (e.readyState == EventSource.OPEN) {
                add('event: OPEN');
            } else if (e.readyState == EventSource.CLOSING) {
                add('event: CLOSING');
            } else if (e.readyState == EventSource.CLOSED) {
                add('event: CLOSED');
            }
        };
    } else {
        alert('The browser does not support Server-Sent Events');
    }
}

function formatTime(ms) {
  var s = Math.floor(ms/1000);
  var m = Math.floor(s/60);
  s -= m*60;
  return m.toString()+":"+(s<10?"0":"")+s.toString();
}

function initLoadFilter(makes,years) {
    $("#load-btn").click(function () {
        startLoad($("#filter-makes").val(), $("#filter-years").val());
    });
}

function startLoad(makes,years) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/source/load?makes="+makes+"&years="+years,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success: function (data, status, jqXHR) {
            alert("Loading succeed");
        },

        error: function (jqXHR, status) {
            alert("Loading failed: "+status);
        }
    });
}