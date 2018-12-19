$(function () {
    // VARIABLES =================================================================

    var $newOrderForm = $("#new-order-form");
    var $calculatePrice = $("#calculate-price");
    $(".client-field").hide();

    // GLOBALS ===================================================================
    var TOKEN_KEY = "jwtToken";

    $(document).ajaxSend(function(e, xhr, options) {
        if(getJwtToken()!==null)
            xhr.setRequestHeader("Authorization","Bearer " + getJwtToken());
    });

    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    // FUNCTIONS ================================================================

    $("#new-order").click(function () {
        $(".client-field").hide();
        $(".new-order-successful").hide();
        $(".new-order-failure").hide();
        $(".new-order").show();
        $("#new-order-form")[0].reset();
        $("#new-order-form").show();
        $("#new-order-title").show();
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
            url: "dashboard/courier/status/refresh",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (courierDto, textStatus, jqXHR) {
                refreshStatusPageWithCourierData(courierDto)
            }
        });
    }

    function changeCurrentNodeId(payLoad) {
        $.ajax({
            url: "dashboard/courier/status/node/change",
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
            url: "dashboard/courier/status/ready",
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

    $("#show-all-client-orders").click(function () {
        $("#show-all-client-orders-table").empty();
        $(".client-field").hide();
        $.ajax({
            url: "/dashboard/client/orders/showAll",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (orderList, textStatus, jqXHR) {
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

    $("#show-all-courier-orders").click(function () {
        $("#show-all-courier-orders-table").empty();
        $(".client-field").hide();
        $.ajax({
            url: "/dashboard/courier/orders/showAll",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (orderList, textStatus, jqXHR) {
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
            fromNodeId: $form.find('input[name="fromNodeId"]').val(),
            toNodeId: $form.find('input[name="toNodeId"]').val()
        };
        calculatePrice(payLoad);
    });

    function calculatePrice(payLoad){
        $.ajax({
            url: "/dashboard/client/orders/new/calculatePrice",
            type: "POST",
            data: JSON.stringify(payLoad),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                $(".client-field .new-order .failure").hide();
                $("#price").val(data);
                $("#submit-new-order").attr('disabled', false);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $(".client-field.new-order.failure").show();
            }
        });
    }

    function createNewOrder(orderData) {
        $.ajax({
            url: "dashboard/client/orders/new/submit",
            type: "POST",
            data: JSON.stringify(orderData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                $("#new-order-form").hide();
                $("#new-order-title").hide();
                $(".new-order-failure").hide();
                $(".new-order-successful").show();
            },
            error: function (jqXHR, textStatus, errorThrown) {
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
            fromNodeId: $form.find('input[name="fromNodeId"]').val(),
            toNodeId: $form.find('input[name="toNodeId"]').val(),
            price: $form.find('input[name="price"]').val()
        };
        createNewOrder(orderData);
    });


});