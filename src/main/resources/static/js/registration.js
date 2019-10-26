$(function() {
     $("#register-form").validate({
        rules: {
            login: {
                 alphanumeric: true,
                 nowhitespace: true
            },
            password: {
                minlength: 6
            },
            confirm_password: {
                 minlength: 6,
                 equalTo: "#register-form #password"
            }
        },
         messages: {
             login: {
                 alphanumeric: "Login must include only letters and numbers",
                 nowwhitespace: "Login must include only letters and numbers"
             },
             confirm_password: {
                equalTo: "Passwords does not match"
             }
         }
     });
 });
