$(document).ready(function () {
    $('#makes').empty();
    getMakes();

    $("#makes").change(function () {
        $('#models').empty();
        $('#years').empty();
        $('#styles').empty();
        var make = $("#makes option:selected").val();
        getModels(make);
    });

    $("#models").change(function () {
        $('#years').empty();
        $('#styles').empty();
        var make = $("#makes option:selected").val();
        var model = $("#models option:selected").val();
        getYears(make, model);
    });

    $("#years").change(function () {
        $('#styles').empty();
        var make = $("#makes option:selected").val();
        var model = $("#models option:selected").val();
        var year = $("#years option:selected").val();
        getStyles(make, model, year);
    });

    $("#styles").change(function () {
        var styleId = $("#styles option:selected").val();
        makeMatch(styleId);
    });

});

function getMakes() {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/makes",
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success: function (data, status, jqXHR) {
            for (var i = 0; i < data.length; i++) {
                var value = data[i];
                $('#makes').append($('<option></option>').val(value).html(value));
            }
        },

        error: function (jqXHR, status) {
            alert(status);
        }
    });
}

function getModels(make) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/models?make=" + make,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success: function (data, status, jqXHR) {
            for (var i = 0; i < data.length; i++) {
                var value = data[i];
                $('#models').append($('<option></option>').val(value).html(value));
            }
        },

        error: function (jqXHR, status) {
            alert(status);
        }
    });
}

function getYears(make, model) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/years?make=" + make + '&model=' + model,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success: function (data, status, jqXHR) {
            $('#years').empty();
            for (var i = 0; i < data.length; i++) {
                var value = data[i];
                $('#years').append($('<option></option>').val(value).html(value));
            }
        },

        error: function (jqXHR, status) {
            alert(status);
        }
    });
}

function getStyles(make, model, year) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/styles?make=" + make + '&model=' + model + '&year=' + year,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success: function (data, status, jqXHR) {
            for (var i = 0; i < data.length; i++) {
                var value = data[i];
                $('#styles').append($('<option></option>').val(value._id).html(value.styleName));
            }
        },

        error: function (jqXHR, status) {
            alert(status);
        }
    });

}

function makeMatch(styleId) {
    $.ajax({
        type: "GET",
        url: "http://localhost:8080/match?styleId=" + styleId,
        contentType: "application/json; charset=utf-8",
        dataType: "json",

        success: function (data, status, jqXHR) {
            $('#match').empty();

            var value = data[0];
            var row = '<tr>';
            row += '<td>Score</td>';
            row += '<td>Make</td>';
            row += '<td>Model</td>';
            row += '<td>Style</td>';
            row += getAttributes1(value.numeric.groups);
            row += '</tr>';
            $('#match').append(row);

            for (var i = 0; i < data.length; i++) {
                var value = data[i];
                var row = '<tr>';
                row += '<td>' + value.score + '</td>';
                row += '<td>' + value.makeName + '</td>';
                row += '<td>' + value.modelName + '</td>';
                row += '<td>' + value.styleName + '</td>';
                row += getAttributes2(value.numeric.groups);
                row += '</tr>';
                $('#match').append(row);
            }
        },

        error: function (jqXHR, status) {
            alert(status);
        }
    });
}

function getAttributes1(value) {
    var result = '';
    var groupNames = Object.keys(value);
    for (var i = 0; i < groupNames.length; i++) {
        var groupName = groupNames[i];
        var attributes = value[groupName];
        var attributeNames = Object.keys(attributes);
        for (var j = 0; j < attributeNames.length; j++) {
            var attributeName = attributeNames[j];
            var attributeValue = attributes[attributeName];
            result += '<th>';
            result += attributeName;
            result += '</th>';
            result += '<th>';
            result += '%';
            result += '</th>';
        }
    }
    return result;
}

function getAttributes2(value) {
    var result = '';
    var groupNames = Object.keys(value);
    for (var i = 0; i < groupNames.length; i++) {
        var groupName = groupNames[i];
        var attributes = value[groupName];
        var attributeNames = Object.keys(attributes);
        for (var j = 0; j < attributeNames.length; j++) {
            var attributeName = attributeNames[j];
            var attributeValue = attributes[attributeName];
            result += '<td>';
            result += attributeValue.absolute;
            result += '</td>';
            result += '<td>';
            result += getPercent(attributeValue.percent);
            result += '</td>';
        }
    }
    return result;
}

function getPercent(value) {
    if (value == null) {
        return '?';
    } else if (value == 0) {
        return ''
    } else {
        if (value > 0) {
            return '+' + value + ' %';
        } else {
            return value + ' %';
        }
    }
}


