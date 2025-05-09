-- Exemple de donnée initiale (facultatif)
INSERT INTO subscription (agency_name, plan_type, start_date, end_date, active) VALUES
('AgenceTest', 'Basic', CURRENT_DATE, DATEADD('MONTH', 1, CURRENT_DATE), true);

INSERT INTO payment (subscription_id, amount, payment_date) VALUES
(1, 99.99, CURRENT_DATE);