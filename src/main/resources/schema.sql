-- src/main/resources/schema.sql
CREATE TABLE IF NOT EXISTS loans (
                                     loan_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     loan_amount DOUBLE NOT NULL,
                                     interest_rate DOUBLE NOT NULL,
                                     term_in_months INTEGER NOT NULL,
                                     monthly_payment DOUBLE NOT NULL,
                                     total_interest DOUBLE NOT NULL,
                                     total_payment DOUBLE NOT NULL,
                                     created_at TIMESTAMP,
                                     updated_at TIMESTAMP,
                                     version BIGINT DEFAULT 0
);

CREATE TABLE IF NOT EXISTS payment_details (
                                               payment_detail_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                               loan_id BIGINT NOT NULL,
                                               payment_number INTEGER NOT NULL,
                                               payment_date DATE NOT NULL,
                                               payment_amount DOUBLE NOT NULL,
                                               principal_amount DOUBLE NOT NULL,
                                               interest_amount DOUBLE NOT NULL,
                                               remaining_balance DOUBLE NOT NULL,
                                               created_at TIMESTAMP,
                                               updated_at TIMESTAMP,
                                               version BIGINT DEFAULT 0,
                                               FOREIGN KEY (loan_id) REFERENCES loans(loan_id)
    );
