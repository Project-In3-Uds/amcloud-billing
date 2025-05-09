CREATE TABLE subscription (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    agency_name VARCHAR(255) NOT NULL,
    plan_type VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE payment (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    subscription_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    FOREIGN KEY (subscription_id) REFERENCES subscription(id)
);