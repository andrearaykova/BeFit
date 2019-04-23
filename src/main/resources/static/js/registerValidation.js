$(function () {
    $("#form").submit(function (event) {
        let username = $("#username").val();
        let password = $("#password").val();
        let confirmPassword = $("#confirmPassword").val();
        let email = $("#email").val();

        if (username === "" || password === "" ||
            confirmPassword === "" || email === "" ||
            password !== confirmPassword) {
            event.preventDefault();
            return false;
        }
        return true;

    })
});