// $('#signUpButton').prop('disabled', true);
//
// function checkPasswordMatch() {
//     let password = $("#newPassword").val();
//     let confirmPassword = $("#confirmPassword").val();
//
//     if (password !== confirmPassword) {
//         $("#divCheckPasswordMatch").html("Passwords do not match!");
//         $('#signUpButton').prop('disabled', true);
//     } else {
//         $("#divCheckPasswordMatch").html("Passwords match.");
//         $('#signUpButton').prop('disabled', false);
//     }
// }

$('#signUpButton').prop('disabled', true);
$("#divCheckPasswordsNotMatch").hide();
$("#divCheckPasswordsMatch").hide();

function checkPasswordMatch() {
    let password = $("#newPassword").val();
    let confirmPassword = $("#confirmPassword").val();

    if (password !== confirmPassword) {
        $("#divCheckPasswordsNotMatch").show();
        $("#divCheckPasswordsMatch").hide();
        $('#signUpButton').prop('disabled', true);
    } else {
        $("#divCheckPasswordsNotMatch").hide();
        $("#divCheckPasswordsMatch").show();
        $('#signUpButton').prop('disabled', false);
    }
}


$(document).ready(function () {
    $("#newPassword, #confirmPassword").keyup(checkPasswordMatch);
});


$(function () {
    let $region = $('#region');
    let $city = $('#city');

    let showCity = function (selectedRegion) {
        $('#city option').hide();
        $city.find('option').filter(function () {
            let v = this.value;
            switch (selectedRegion) {
                case'1':
                    if (v < 19) {
                        return v;
                    }
                    break;
                case'2':
                    if (v >= 19 && v < 37) {
                        return v;
                    }
                    break;
                case'3':
                    if (v >= 37 && v < 53) {
                        return v;
                    }
                    break;
                case'4':
                    if (v >= 53 && v < 68) {
                        return v;
                    }
                    break;
                case'5':
                    if (v >= 68 && v < 93) {
                        return v;
                    }
                    break;
                case '6':
                    if (v >= 93) {
                        return v;
                    }
                    break;
            }
        }).show();
        let e = document.getElementById('city');
        let userCity = e.options[e.selectedIndex].value;
        if (userCity !== 1) {
            document.getElementById('city').value = userCity;
        } else {
            switch (selectedRegion) {
                case'1':
                    document.getElementById('city').value = '1'
                    break;
                case '2':
                    document.getElementById('city').value = '19'
                    break;
                case '3':
                    document.getElementById('city').value = '37'
                    break;
                case '4':
                    document.getElementById('city').value = '53'
                    break;
                case '5':
                    document.getElementById('city').value = '68'
                    break;
                case '6':
                    document.getElementById('city').value = '93'
                    break;
            }
        }

    };

    let region = $region.val();
    showCity(region);

    $region.change(function () {
        showCity($(this).val());
    });
})


// let $region = $('#region');
// let $city = $('#city');
// $region.change(function () {
//     let val = $region.val();
//     $city.find('option').each(function () {
//         let v = $city.val();
//         if (val === 1) {
//             this.disabled = v > 18;
//         } else if (val === 2) {
//             this.disabled = v <= 18 && v > 36;
//         } else if (val === 3) {
//             this.disabled = v <= 36 && v > 52;
//         } else if (val === 4) {
//             this.disabled = v <= 52 && v > 67;
//         } else if (val === 5) {
//             this.disabled = v <= 67 && v > 92;
//         } else if (val === 6) {
//             this.disabled = v <= 92;
//         }
//
//     })
//     $city.val($city.find('option:not(:disabled):first').val());
// });