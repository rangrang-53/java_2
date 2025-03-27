$(document).ready(() => {
    setupAjax();
    checkToken();

    $("#order-in").click((e) => {

        e.preventDefault();
        console.log("버튼 클릭 됨");

        let bookIsbn = $('#bookIsbn').val();
        let quantity = $('#quantity').val();

        let sendData = {
            isbn: bookIsbn,
            quantity: quantity
        };

        console.log(sendData);

        $.ajax({
            type: "POST",
            url: "/webs/api/order",
            headers: {
                "Authorization": "Bearer " + localStorage.getItem("accessToken")
            },
            data: JSON.stringify(sendData), // 데이터를 JSON 형식으로 변환
            contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
            dataType: 'json', // 서버에서 받을 데이터의 타입
            success: (response) => {
                alert('주문 완료!')
                console.log('response :: ', response)
                window.location.href = '/webs/orders/check';
            },
            error: (xhr) => {
                if (xhr.status === 419) {
                    // Refresh Token을 통해 Access Token 재발급 요청
                    handleTokenExpiration();
                    alert('다시 한번 시도해주세요.');
                } else {
                    // 다른 오류 처리
                    console.error('요청 오류 발생:', xhr);
                }
                location.reload(true);
            }

        });
    });

});