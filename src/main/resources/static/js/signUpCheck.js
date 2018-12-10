function checkPass() {
    //Store the password field objects into variables ...
    var pass1 = document.getElementById('pass1');
    var pass2 = document.getElementById('pass2');
    //Store the Confirmation Message Object ...
    var message = document.getElementById('confirmMessage');
    //Set the colors we will be using
    var goodColor = "#B8FFB7";
    var badColor = "#FFB3B5";
    //Compare the values in the password field
    //and the confirmation field
    if (pass1.value == pass2.value) {
        //The passwords match.
        //Set the color to the good color and inform
        //the user that they have entered the correct password
        pass2.style.backgroundColor = goodColor;
        message.style.color = goodColor;
        message.innerHTML = "Passwords match"
    } else {
        //The passwords do not match.
        //Set the color to the bad color and
        //notify the user.
        pass2.style.backgroundColor = badColor;
        message.style.color = badColor;
        message.innerHTML = "Passwords do not match!"
    }
}

// validates text only
function validateUsername(username) {
    var usernameRegexp = /^[a-zA-Z0-9]{6,32}$/;

    if (usernameRegexp.test(username.value)) {
        document.getElementById('username').style.backgroundColor = "#B8FFB7";

    }
    else {
        document.getElementById('username').style.backgroundColor = "#FFB3B5";
    }
}

function validateFirstName(firstName) {
    var firstNameRegexp = /^[a-zA-Z]{1,32}$/;

    if (firstNameRegexp.test(firstName.value)) {
        document.getElementById('firstName').style.backgroundColor = "#FFFFFF";

    }
    else {
        document.getElementById('firstName').style.backgroundColor = "#FFB3B5";
    }
}

function validateLastName(lastName) {
    var lastNameRegexp = /^[a-zA-Z]{1,32}$/;

    if (lastNameRegexp.test(lastName.value)) {
        document.getElementById('lastName').style.backgroundColor = "#FFFFFF";

    }
    else {
        document.getElementById('lastName').style.backgroundColor = "#FFB3B5";
    }
}

function validateAge(age) {
    if (age.value > 17) {
        document.getElementById('age').style.backgroundColor = "#FFFFFF";

    }
    else {
        document.getElementById('age').style.backgroundColor = "#FFB3B5";
    }
}

// validate email
function email_validate(email) {
    var regMail = /^([_a-zA-Z0-9-]+)(\.[_a-zA-Z0-9-]+)*@([a-zA-Z0-9-]+\.)+([a-zA-Z]{2,3})$/;

    if (regMail.test(email)) {
        document.getElementById('email').style.backgroundColor = "#FFFFFF";
    }
    else {
        document.getElementById('email').style.backgroundColor = "#FFB3B5";
    }
}
