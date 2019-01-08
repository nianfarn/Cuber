$(function () {
    // VARIABLES =================================================================
    var $newOrderForm = $("#new-order-form");
    var $calculatePrice = $("#calculate-price");
    $(".client-field").hide();

    // GLOBALS ===================================================================
    var TOKEN_KEY = "jwtToken";
    var LOCALE_KEY="locale";

    $(document).ajaxSend(function(e, xhr) {
        if(getJwtToken()!==null)
            xhr.setRequestHeader("Authorization","Bearer " + getJwtToken());
            xhr.setRequestHeader("Locale", getLocale());
    });

    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    // FUNCTIONS ================================================================

    function getLocale() {
        return getCookie(LOCALE_KEY);
    }

    function getCookie(name) {
        var value = "; " + document.cookie;
        var parts = value.split("; " + name + "=");
        if (parts.length === 2) return parts.pop().split(";").shift();
    }

    function obtainAddressArray() {
        $.ajax({
            url: "client/nodes",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (nodes) {
                $("#fromNode").autocomplete({
                    source: nodes
                });
                $("#toNode").autocomplete({
                    source: nodes
                });
            }
        });
    }

    $("#new-order").click(function () {
        $(".client-field").hide();
        $(".new-order-successful").hide();
        $(".new-order-failure").hide();
        $(".new-order").show();
        $("#new-order-form")[0].reset();
        $("#new-order-form").show();
        $("#new-order-title").show();
        obtainAddressArray();
    });
    function refreshStatusPageWithCourierData(courierDto) {
        console.log(courierDto);
        $("#currentNodeId").val(courierDto.currentNodeId);
        if(courierDto.readyToGo === true){
            $("#ready-to-go-radio").attr('checked', true);
            $("#not-ready-radio").attr('checked', false);
            $("#submit-current-node-id").attr('disabled', false);
        }
        if(courierDto.readyToGo === false){
            $("#ready-to-go-radio").attr('checked', false);
            $("#not-ready-radio").attr('checked', true);
            $("#submit-current-node-id").attr('disabled', true);
        }
    }
    function refreshStatus() {
        $.ajax({
            url: "courier/status/refresh",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (courierDto) {
                refreshStatusPageWithCourierData(courierDto)
            }
        });
    }

    function changeCurrentNodeId(payLoad) {
        $.ajax({
            url: "courier/status/node/change",
            type: "POST",
            data: JSON.stringify(payLoad),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (response) {
                $('#succRegModal')
                    .modal("show")
                    .find("#succRegBody")
                    .empty()
                    .html("<p>" + response.message +"</p>");
            }
        });
    }

    $("#current-node-form").submit(function (event) {
        event.preventDefault();
        var $form = $(this);
        var payLoad = {
            currentNodeId: $form.find('input[name="currentNodeId"]').val()
        };
        changeCurrentNodeId(payLoad);
    });

    function changeReadyStatus() {
        $.ajax({
            url: "courier/status/ready",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (courierDto) {
                refreshStatusPageWithCourierData(courierDto);
            }
        })
    }

    $("input[name=status]:radio").change(function () {
        changeReadyStatus();
    });

    $("#status-management").click(function () {
        $(".client-field").hide();
        refreshStatus();
        $(".status-management").show();
    });


    $("#show-all-client-orders").click(function () {
        $("#show-all-client-orders-table").empty();
        $(".client-field").hide();
        $.ajax({
            url: "/client/orders/showAll",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (orderList) {
                if (orderList.size === 0){
                    return false;
                }
                generateTable(orderList, $("#show-all-client-orders-table"));

            },
            error: function (jqXHR, textStatus, errorThrown) {

            }
        });
        $(".show-all-client-orders").show();
    });

    function addAllColumnHeaders(myList, selector) {
        var columnSet = [];
        var headerTr$ = $('<tr/>');

        for (var i = 0; i < myList.length; i++) {
            var rowHash = myList[i];
            for (var key in rowHash) {
                if ($.inArray(key, columnSet) === -1) {
                    columnSet.push(key);
                    headerTr$.append($('<th/>').html(key));
                }
            }
        }
        $(selector).append(headerTr$);

        return columnSet;
    }

    function generateTable(orderList, selector) {
        var columns = addAllColumnHeaders(orderList, selector);

        for (var i = 0; i < orderList.length; i++) {
            var row$ = $('<tr/>');
            for (var colIndex = 0; colIndex < columns.length; colIndex++) {
                var cellValue = orderList[i][columns[colIndex]];
                if (cellValue === null) cellValue = "";
                row$.append($('<td/>').html(cellValue));
            }
            $(selector).append(row$);
        }
    }

    $("#show-all-courier-orders").click(function () {
        $("#show-all-courier-orders-table").empty();
        $(".client-field").hide();
        $.ajax({
            url: "/courier/orders/showAll",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (orderList) {
                if (orderList.size === 0){
                    return false;
                }
                generateTable(orderList, $("#show-all-courier-orders-table"));

            },
            error: function (jqXHR, textStatus, errorThrown) {

            }
        });
        $(".show-all-courier-orders").show();
    });

    $calculatePrice.click(function () {

        var $form = $("#new-order-form");
        var payLoad = {
            fromNode: $form.find('input[name="fromNode"]').val(),
            toNode: $form.find('input[name="toNode"]').val()
        };
        calculatePrice(payLoad);
    });

    function calculatePrice(payLoad){
        $.ajax({
            url: "/client/orders/new/calculatePrice",
            type: "POST",
            data: JSON.stringify(payLoad),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data) {
                $(".client-field .new-order .failure").hide();
                $("#price").val(data);
                $("#submit-new-order").attr('disabled', false);
            },
            error: function () {
                $(".client-field.new-order.failure").show();
            }
        });
    }

    function createNewOrder(orderData) {
        $.ajax({
            url: "client/orders/new/submit",
            type: "POST",
            data: JSON.stringify(orderData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function () {
                $("#new-order-form").hide();
                $("#new-order-title").hide();
                $(".new-order-failure").hide();
                $(".new-order-successful").show();
            },
            error: function () {
                $(".new-order-successful").hide();
                $(".new-order-failure").show();
            }
        });
    }

    $newOrderForm.submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var orderData = {
            productName: $form.find('input[name="productName"]').val(),
            weight: $form.find('input[name="weight"]').val(),
            volume: $form.find('input[name="volume"]').val(),
            fromNode: $form.find('input[name="fromNode"]').val(),
            toNode: $form.find('input[name="toNode"]').val(),
            price: $form.find('input[name="price"]').val()
        };
        createNewOrder(orderData);
    });
});