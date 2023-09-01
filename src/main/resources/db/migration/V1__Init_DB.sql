CREATE TABLE clever_bank.accounts
(
    number        character(30)       NOT NULL,
    owner_id      uuid                NOT NULL,
    bank_id       smallint            NOT NULL,
    balance       numeric DEFAULT 0.0 NOT NULL,
    accrual_date  date                NOT NULL,
    creation_date timestamp without time zone NOT NULL,
    primary key (number)
);

CREATE TABLE clever_bank.transactions
(
    id               uuid                     NOT NULL,
    acc_from         character(30)            NOT NULL,
    acc_to           character(30)            NOT NULL,
    transaction_type character(10)            NOT NULL,
    amount           numeric                  NOT NULL,
    created_at       timestamp with time zone NOT NULL,
    primary key (id)
);

CREATE TABLE clever_bank.banks
(
    id   bigint            NOT NULL,
    name character varying NOT NULL,
    primary key (id)
);

CREATE TABLE clever_bank.users
(
    id    uuid              NOT NULL,
    owner character varying NOT NULL,
    primary key (id)
);

ALTER TABLE accounts ADD CONSTRAINT fk_bank
    FOREIGN KEY (bank_id) REFERENCES banks (id);
ALTER TABLE accounts ADD CONSTRAINT fk_owner
    FOREIGN KEY (owner_id) REFERENCES users (id);

ALTER TABLE transactions ADD CONSTRAINT fk_acc_from
    FOREIGN KEY (acc_from) REFERENCES accounts (number);
ALTER TABLE transactions ADD CONSTRAINT fk_acc_to
    FOREIGN KEY (acc_to) REFERENCES accounts (number);





