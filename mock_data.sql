CREATE TABLE employees(
id serial PRIMARY KEY,
"first" varchar(25) NOT null,
"last" varchar(25) NOT NULL,
email TEXT UNIQUE,
"password" varchar(25) NOT NULL,
isManager boolean NOT NULL
);

CREATE TABLE tickets(
id serial PRIMARY KEY,
employee_id int REFERENCES employees,
"cost" decimal(5,2),
description TEXT NOT NULL,
status boolean
);

DROP TABLE tickets;

INSERT INTO employees ("first", "last", email, "password", isManager)
VALUES 
('Cesare', 'Elcox', 'celcox1@google.com.hk', 'WowKeeW9gvc6', true),
('Wit', 'Beert', 'wbeert2@booking.com', 'BHIu4KXvSQ', false),
('Justinian', 'Thomasen', 'jthomasen3@prnewswire.com', 'IKxlRh', false),
('Shaine', 'Gethouse', 'sgethouse4@devhub.com', 'gKq05SmWlpm', false),
('Aurthur', 'Toxell', 'atoxell5@goodreads.com', '802Fh2mD3', false),
('Pru', 'Thormwell', 'pthormwell6@youtube.com', '6S2RPDMR', false),
('Natale', 'Hinckes', 'nhinckes7@free.fr', 'WnTTreysy', false),
('Stanley', 'Jarlmann', 'sjarlmann8@drupal.org', '4yIsXwMHh8', false);

INSERT INTO tickets (employee_id, "cost", description, status)
VALUES
(4, 12.02, 'I would like to submit a ticket.', true),
(2, 8.42, 'Thanks for the reimbursement.', true),
(5, 10.17, 'wait I dont get a refund?', false),
(7, 13.41, 'This is insane!', false),
(8, 9.00, 'Here is my ticket.', true);
