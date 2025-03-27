$(document).ready(() => {
    setupAjax();
    checkToken();
});

let goOrder = () => {
    window.location.href = "/webs/orders"
}

let getOrder = () => {
    let id = $('#id').val();
    let bookIsbn = $('#bookIsbn').val();
    let bookName = $('#bookName').val();
    let bookPrice = $('#bookPrice').val();
    let createdDate = $('#createdDate').val();
    let status = $('#status').val();

    $.ajax({
        type: "GET",
        url: "/webs/api/order",
        dataType: "json",
        success: (response) => {
            $('#orderList').empty();
            if(response.length === 0){
                $('#orderList').append(`
                <tr>
                    <td colspan="4" style="text-align: center;">주문이 존재하지 않습니다.</td>
                </tr>
                `);
            } else {
                response.forEach(book => {
                    $('#orderList').append(`
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.bookIsbn}</td>
                        <td>${book.bookName}</td>
                        <td>${book.bookPrice}</td>
                        <td>${book.quantity}</td>
                        <td>${book.status}</td>
                        <td>${book.createdDate.split('T')[0]}</td>
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