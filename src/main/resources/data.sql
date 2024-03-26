INSERT INTO roles (role_name) VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO users (first_name, last_name, phone_nr, email, password)
VALUES ('John', 'Doe', '123456789', 'john@example.com', 'password123');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);

INSERT INTO rooms (room_number, price, room_type, capacity)
VALUES ('101', 100.00, 'Standard', 2),
       ('102', 150.00, 'Deluxe', 3);

INSERT INTO bookings (user_id, booking_date, start_date, end_date)
VALUES (1, '2024-03-26 12:00:00', '2024-04-01', '2024-04-03');
INSERT INTO booking_dates (date, booking_id, room_id)
VALUES ('2024-04-01', 1, 1);
INSERT INTO booking_dates (date, booking_id, room_id)
VALUES ('2024-04-02', 1, 1);