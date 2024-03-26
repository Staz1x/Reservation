CREATE TABLE IF NOT EXISTS roles (
                                     id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     role_name VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS users (
                                     user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     first_name VARCHAR(255) NOT NULL,
                                     last_name VARCHAR(255) NOT NULL,
                                     phone_nr VARCHAR(20) NOT NULL,
                                     email VARCHAR(255) NOT NULL UNIQUE,
                                     password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_role (
                                          user_id BIGINT,
                                          role_id BIGINT,
                                          PRIMARY KEY (user_id, role_id),
                                          FOREIGN KEY (user_id) REFERENCES users(user_id),
                                          FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE IF NOT EXISTS rooms (
                                     room_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                     room_number VARCHAR(20),
                                     price DECIMAL(10, 2),
                                     room_type VARCHAR(255),
                                     capacity INT
);

CREATE TABLE IF NOT EXISTS bookings (
                                        booking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                        user_id BIGINT,
                                        booking_date TIMESTAMP,
                                        start_date DATE,
                                        end_date DATE,
                                        FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE IF NOT EXISTS booking_dates (
                                             booking_date_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                             date DATE,
                                             booking_id BIGINT,
                                             room_id BIGINT,
                                             FOREIGN KEY (booking_id) REFERENCES bookings(booking_id),
                                             FOREIGN KEY (room_id) REFERENCES rooms(room_id)
);