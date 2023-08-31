
CREATE TABLE test.accounts
(
    number       character(30)       NOT NULL,
    owner_id     uuid                NOT NULL,
    bank_id      smallint            NOT NULL,
    balance      numeric DEFAULT 0.0 NOT NULL,
    accrual_date date                NOT NULL,
    primary key (number)
);

CREATE TABLE test.transactions
(
    id               uuid                     NOT NULL,
    acc_from         character(30)            NOT NULL,
    acc_to           character(30)            NOT NULL,
    transaction_type character(10)            NOT NULL,
    amount           numeric                  NOT NULL,
    created_at       timestamp with time zone NOT NULL,
    primary key (id)

)