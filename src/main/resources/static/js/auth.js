$(function () {
    // VARIABLES =============================================================
    var TOKEN_KEY = "jwtToken"
    var $signout = $("#sign-out").hide();
    var $loggedIn = $("#loggedIn").hide();
    var $response = $("#response");
    var $login = $("#loginForm").show();
    var $register = $("#regForm").hide();
    var $regError = $('#RegErrorModal');
    var $CLUI = $("#client-interface").hide();
    var $COUI = $("#courier-interface").hide();

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

    function doLogin(loginData) {
        $.ajax({
            url: "/auth",
            type: "POST",
            data: JSON.stringify(loginData),
            contentType: "application/json; charset=utf-8",
            dataType: "json",
            success: function (data, textStatus, jqXHR) {
                console.log(data);
                setJwtToken(data.token);
                $login.hide();
                $signout.show();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                if (jqXHR.status === 401 || jqXHR.status === 403) {
                    $('#loginErrorModal')
                        .modal("open")
                        .modal("show")
                        .find("#loginErrorBody")
                        .empty()
                        .html("<p>" + jqXHR.responseText + "</p>");
                } else {
                    throw new Error("an unexpected error occured: " + jqXHR.message);
                }
            }
        });
    }

    function doLogout() {
        removeJwtToken();
        $login.show();
        $signout.hide();
    }

    function createAuthorizationTokenHeader() {
        var token = getJwtToken();
        if (token) {
            return {"Authorization": "Bearer " + token};
        } else {
            return {};
        }
    }


    function showResponse(statusCode, message) {
        $response
            .empty()
            .text("status code: " + statusCode + "\n-------------------------\n" + message);
    }


    $login.submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val()
        };

        doLogin(formData);
    });

    $signout.click(doLogout);

    $("#to-reg").click(function () {
        $login.hide();
        $register.show();
    });

    $("#to-log").click(function () {
        $register.hide();
        $login.show();
    });


    $("#adminServiceBtn").click(function () {
        $.ajax({
            url: "/protected",
            type: "GET",
            contentType: "application/json; charset=utf-8",
            headers: createAuthorizationTokenHeader(),
            success: function (data, textStatus, jqXHR) {
                showResponse(jqXHR.status, data);
            },
            error: function (jqXHR, textStatus, errorThrown) {
                showResponse(jqXHR.status, errorThrown);
            }
        });
    });

    $loggedIn.click(function () {
        $loggedIn
            .toggleClass("text-hidden")
            .toggleClass("text-shown");
    });

    if (getJwtToken()) {
        $login.hide();
        $signout.show();
    }

    $register.submit(function (event) {
        event.preventDefault();

        var $form = $(this);
        var formData = {
            username: $form.find('input[name="username"]').val(),
            password: $form.find('input[name="password"]').val(),
            firstName: $form.find('input[name="firstName"]').val(),
            lastName: $form.find('input[name="lastName"]').val(),
            age: $form.find('input[name="age"]').val(),
            email: $form.find('input[name="email"]').val()
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
