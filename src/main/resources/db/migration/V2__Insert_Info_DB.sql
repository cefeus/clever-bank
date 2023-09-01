INSERT INTO clever_bank.users (id, owner)
VALUES ('acf22a78-45bf-11ee-be56-0242ac120002', 'Иванов Сидор Петрович'),
       ('acf23a78-45bf-12ee-be56-0242ac120002', 'Сидоров Петр Иванович'),
       ('acf22a88-45bf-21ee-be56-0244ac120002', 'Петров Иван Сидорович'),
       ('acf32a78-47bf-11ee-be56-0262ac120002', 'Наличный Артем Андреевич');

INSERT INTO clever_bank.banks (id, name)
VALUES (1, 'CleverBank'),
       (2, 'Belarusbank'),
       (3, 'Tinkoff'),
       (4, 'Priorbank');

INSERT INTO clever_bank.accounts (number, owner_id, bank_id, balance, accrual_date, creation_date)
VALUES ('BY20OLMP31350000001000000933', 'acf22a78-45bf-11ee-be56-0242ac120002', 1, 7072.00, '2023-08-28', '2018-07-05'),
       ('BY20OLMP31350000001000000934', 'acf23a78-45bf-12ee-be56-0242ac120002', 2, 2500, '2023-08-25', '2005-07-02');


