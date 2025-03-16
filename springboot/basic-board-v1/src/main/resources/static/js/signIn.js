$(document).ready(() => {

    $('#signin').click(() => {
        let userId = $('#user_id').val();
        let password = $('#password').val();

        let formData = {
            username: userId,
            password: password,
        }

        $.ajax({
            type: 'POST',
            url: '/login', //서버의 엔드포인트 URL
            data: $.param(formData), // 데이터를 URL 인코딩된 형식으로 변환
            contentType: 'application/x-www-form-urlencoded; charset=utf-8',
            dataType: 'json', //서버에서 받을 데이터의 타입
            success: (response) => {
                console.log(response);
                alert('로그인 되었습니다!')

            },
            error: (error) => {
                console.log('오류발생: ', error);
                alert('로그인 중 오류가 발생하였습니다.');

            }
        })
    })

});