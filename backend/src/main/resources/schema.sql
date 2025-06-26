-- Dropping existing tables if they exist
DROP TABLE IF EXISTS payment;
DROP TABLE IF EXISTS subscription;
-- Creating new tables for the subscription and payment system
CREATE TABLE subscription (
    id BIGSERIAL PRIMARY KEY,
    agency_name VARCHAR(255) NOT NULL,
    plan_type VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    active BOOLEAN NOT NULL
);

CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY,
    subscription_id BIGINT NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    payment_date DATE NOT NULL,
    FOREIGN KEY (subscription_id) REFERENCES subscription(id)
);