$(document).ready(() => {
    setupAjax();
    checkToken();
});

let goOrder = () => {
    window.location.href = "/webs/orders"
}

let getCatalog = () => {
    let isbn = $('#isbn').val();
    let title = $('#title').val();
    let author = $('#author').val();
    let price = $('#price').val();

    $.ajax({
        type: "GET",
        url: "/webs/api/catalog",
        dataType: "json",
        success: (response) => {
            $('#catalogList').empty();
            if(response.length === 0){
                $('#catalogList').append(`
                <tr>
                    <td colspan="4" style="text-align: center;">책이 존재하지 않습니다.</td>
                </tr>
                `);
            } else {
                response.forEach(book => {
                    $('#catalogList').append(`
                    <tr>
                        <td>${book.isbn}</td>
                        <td>${book.title}</td>
                        <td>${book.author}</td>
                        <td>${book.price}</td>
                    </tr>
                    `);
                });
            }
        },
        error: (xhr) => {
            console.error('요청 오류 발생:', xhr);
        }
    });

}

let createCatalog = () => {
    let isbn = $('#isbn').val();
    let title = $('#title').val();
    let author = $('#author').val();
    let price = $('#price').val();

    let sendData = {
        isbn : isbn,
        title : title,
        author : author,
        price : price
    }

    $.ajax({
        method: 'POST',
        url: '/webs/api/catalog',
        data: JSON.stringify(sendData), // 데이터를 JSON 형식으로 변환
        contentType: 'application/json; charset=utf-8', // 전송 데이터의 타입
        dataType: 'json', // 서버에서 받을 데이터의 타입
        success: (response) => {
            alert('책 등록 완료!')
            console.log('response :: ', response)
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
}