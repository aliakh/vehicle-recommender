function initSSE() {
    if (!!window.EventSource) {
        var eventSource = new EventSource("/source/load/sse");

        var elements = document.getElementById("messages");

        function add(message) {
            //var element = document.createElement("li");
            //element.innerHTML = message;
            //elements.appendChild(element);
            console.log(message);
        }

        eventSource.onmessage = function (e) {
            var event = JSON.parse(e.data);
            var progress = event.progress
            var value = Math.floor(100*progress.cur/progress.max);
            var leftTime = progress.leftTime;
            var remindedTime = progress.remindedTime;

            $('.progress-bar').css('width', value+'%').attr('aria-valuenow', value);
            add(progress.cur + " " + progress.max + " " + value + " " + leftTime + " " + remindedTime);
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