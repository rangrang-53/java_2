CREATE TABLE IF NOT EXISTS orders (
                                      id BIGINT PRIMARY KEY NOT NULL AUTO_INCREMENT,
                                      book_isbn VARCHAR(255) NOT NULL,
    book_name VARCHAR(255),
    book_price DECIMAL(10, 2), -- DECIMAL 타입으로 변경
    quantity INT NOT NULL,
    status VARCHAR(255) NOT NULL,
    created_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP, -- 현재 시간 자동 설정
    last_modified_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP, -- 자동 갱신 시간
    version INT NOT NULL
    );