-- roles data
INSERT INTO roles (role_name) VALUES
                                  ('ADMIN'),
                                  ('USER'),
                                  ('GUEST');

-- users data
INSERT INTO users (first_name, last_name, phone_nr, email, password, role_id) VALUES
                                                                                  ('John', 'Doe', '123456789', 'john@example.com', 'password', 1),
                                                                                  ('May', 'Day', '987654321', 'may@example.com', 'password456', 2);

-- rooms data
INSERT INTO rooms (room_number, price, room_type, capacity) VALUES
                                                                ('101', 100.00, 'Standard', 2),
                                                                ('102', 150.00, 'Deluxe', 4);


-- bookings data
INSERT INTO bookings (user_id, room_id, start_date, end_date) VALUES
                                                                  (1, 1, '2024-06-18', '2024-06-21'),
                                                                  (2, 1, '2024-07-01', '2024-07-05');

-- booking_dates data
-- Assuming room_id 1 for both bookings
INSERT INTO booking_dates (date, booking_id, room_id) VALUES
                                                          ('2024-06-18', 1, 1),
                                                          ('2024-06-19', 1, 1),
                                                          ('2024-06-20', 1, 1),
                                                          ('2024-06-21', 1, 1),
                                                          ('2024-07-01', 2, 1),
                                                          ('2024-07-02', 2, 1),
                                                          ('2024-07-03', 2, 1),
                                                          ('2024-07-04', 2, 1),
                                                          ('2024-07-05', 2, 1);