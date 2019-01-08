$(function () {
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken";
    var $signOut = $("#sign-out").hide();
    var $dashSignOut = $("#signout-dashboard");
    var $loggedIn = $("#loggedIn").hide();
    var $login = $("#loginForm");
    var $quickOrderManagement = $("#quick-order-management").hide();
    var $register = $("#regForm").hide();
    var $regError = $('#RegErrorModal');
    var $dashboard = $('#dashboard').hide();
    var $dashboardOpen = $("#dashboard-open").hide();
    var $dashboardClose = $("#dashboard-close").hide();

    // DASHBOARD SETUP

    var $dashboardNavFields = $(".client-dashboard, .courier-dashboard, .admin-dashboard").hide();
    var $clientDashFields = $(".client-dashboard").hide();
    var $courierDashFields = $(".courier-dashboard").hide();


    // GLOBALS ===============================================================
    $(document).ajaxSend(function(e, xhr, options) {
        if(getJwtToken()!==null)
            xhr.setRequestHeader("Authorization","Bearer " + getJwtToken());
    });


    // FUNCTIONS =============================================================

    function getJwtToken() {
        return localStorage.getItem(TOKEN_KEY);
    }

    function setJwtToken(token) {
        localStorage.setItem(TOKEN_KEY, token);
    }

    function removeJwtToken() {
        localStorage.removeItem(TOKEN_KEY);
    }


    function openDashboard() {
        $.ajax({
            url: "/dashboard",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            error: function (jqXHR, textStatus, errorThrown) {
                if(jqXHR.status === 200) {
                    $("#initial-frame").hide();
                    $dashboard.show();
                    if(jqXHR.responseText==="client") {
                        $dashboardNavFields.hide();
                        $clientDashFields.show();
                    }
                    if(jqXHR.responseText==="courier"){
                        $dashboardNavFields.hide();
                        $courierDashFields.show();
                    }
                }
            }
        });
    }

    $dashboardOpen.click(function () {
        openDashboard();
    });


    $dashboardClose.click(function () {
        closeDashboard();
    });
    function closeDashboard() {
        $("#initial-frame").show();
        $dashboard.hide();

    }

    function generateQuickOrderManagementImage(ordersData) {
        $("#orders-div-select-list").html("");
        $("#order-management-div").html("");
        function showOrderManagementInfo(orderData) {
            console.log(orderData);
            $(".order-management-div").html("");
            for (var key in orderData) {
                console.log(key);
            }
        }

        for (var i=0; i<ordersData.length-1; i++){
            var id = ordersData[i]['Идентификатор'] || ordersData[i]['Id'];
            var productName = ordersData[i]['Имя товара'] || ordersData[i]['Product Name'];
            var role = ordersData[ordersData.length-1]["role"];

            //orders nav
            var aElem = productName +"(#"+id+")";
            $('#orders-div-select-list')
                .append($('<li/>')
                .append($('<a/>')
                    .html(aElem))
            );
            $('#orders-div-select-list li a')
        }
    }

    function getQuickOrderManagementData() {
        $.ajax({
            url: "/frontWindow/ordersData",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (ordersData) {
                generateQuickOrderManagementImage(ordersData);
            }
        });
    }

    function openQuickOrderManagement() {
        getQuickOrderManagementData();
        $quickOrderManagement.show();
    }

    function doLogin(loginData) {
        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                setJwtToken(data.token);
                $login.hide();
                $signOut.show();
                $dashboardOpen.show();
                $dashboardClose.hide();
                openQuickOrderManagement();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401 || jqXHR.status === 403) {
                    $('#errorModal')
                        .modal("show")
                        .find("#errorBody")
                        .empty()
                        .html("<p>" + jqXHR.responseText + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + jqXHR.message);
                }
            }
        });
    }

    function closeQuickOrderManagement() {
        $quickOrderManagement.hide();
    }

    function doLogout() {
        removeJwtToken();
        closeDashboard();
        $login.show();
        $signOut.hide();
        $dashboardOpen.hide();
        closeQuickOrderManagement();
    }


    $("#adminServiceBtn").click(function () {
        $.ajax({
            url: "/protected",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            success: function (data, textStatus, jqXHR) {
            },
            error: function (jqXHR, textStatus, errorThrown) {
            }
        });
    });
    $login.submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    $signOut.click(doLogout);

    $dashSignOut.click(doLogout);


    $("#to-reg").click(function () {
        $login.hide();
        $register.show();
    });

    $("#to-log").click(function () {
        $register.hide();
        $login.show();
    });

    $loggedIn.click(function () {
        $loggedIn
            .toggleClass("text-hidden")
            .toggleClass("text-shown");
    });

    if (getJwtToken()) {
        $login.hide();
        $signOut.show();
        $dashboardOpen.show();
    }

    $register.submit(function (event) {
        event.preventDefault();

        var typeVal;
        var type = document.getElementsByName("type");
        for (var i=0; i<type.length;i++){
            if (type[i].checked){
                typeVal = type[i].value;
            }
        }
        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val(),
            firstName: $form.find('input[name="firstName"]').val(),
            lastName: $form.find('input[name="lastName"]').val(),
            age: $form.find('input[name="age"]').val(),
            email: $form.find('input[name="email"]').val(),
            type: typeVal
        };
        doRegister(formData);
    });

    function doRegister(loginData) {
        $.ajax({
            url: "/reg",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                $('#succRegModal')
                    .modal("show")
                    .find("#succRegBody")
                    .empty()
                    .html("<p>" + data.message +"</p>");
            },
            error: function (jqXHR, textStatus, errorThrown) {
                $regError
                    .modal("show")
                    .find("#RegErrorBody")
                    .empty();
                if(jqXHR.status === 400 || jqXHR.status === 401 || jqXHR.status === 403) {
                    $regError
                        .html("<p>" + jqXHR.responseJSON.message + "</p>");
                }

            }
        });
    }


});
