-- Insert data into the subscription table
INSERT INTO subscription (agency_name, plan_type, start_date, end_date, active) 
VALUES ('AgenceTest', 'Basic', CURRENT_DATE, CURRENT_DATE + INTERVAL '1 month', true);

-- Insert data into the payment table
INSERT INTO payment (subscription_id, amount, payment_date) 
VALUES (1, 100.00, CURRENT_DATE);