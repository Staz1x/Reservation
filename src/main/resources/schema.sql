-- Skapa tabellen för användare
CREATE TABLE users (
                       user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       first_name VARCHAR(255) NOT NULL,
                       last_name VARCHAR(255) NOT NULL,
                       email VARCHAR(255) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       phone_nr VARCHAR(255) NOT NULL
);

-- Skapa tabellen för rum
CREATE TABLE rooms (
                       room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       room_number VARCHAR(255),
                       price NUMERIC(38, 2),
                       room_type VARCHAR(255)
);

-- Skapa tabellen för bokningar
CREATE TABLE bookings (
                          booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          user_id BIGINT,
                          FOREIGN KEY (user_id) REFERENCES users(user_id),
                          booking_date TIMESTAMP,
                          start_date DATE,
                          end_date DATE
);

-- Skapa tabellen för bokningsdatum
CREATE TABLE booking_dates (
                               booking_date_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                               booking_id BIGINT,
                               FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
                               date DATE,
                               room_id BIGINT,
                               FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);