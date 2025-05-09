CREATE TABLE country (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         continent VARCHAR(255) NOT NULL
);

CREATE TABLE host (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      surname VARCHAR(255) NOT NULL,
                      country_id INTEGER,
                      CONSTRAINT fk_host_country FOREIGN KEY (country_id)
                          REFERENCES country(id)
                          ON DELETE SET NULL
);

CREATE TABLE place (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       category VARCHAR(50) NOT NULL,
                       host_id INTEGER,
                       num_rooms INTEGER NOT NULL,
                       rented BOOLEAN DEFAULT FALSE,
                       CONSTRAINT fk_place_host FOREIGN KEY (host_id)
                           REFERENCES host(id)
                           ON DELETE SET NULL
);
CREATE TABLE app_users (
                           username VARCHAR(255) PRIMARY KEY,
                           password VARCHAR(255) NOT NULL,
                           name VARCHAR(255) NOT NULL,
                           surname VARCHAR(255) NOT NULL,
                           role VARCHAR(50) NOT NULL,
                           is_account_non_expired BOOLEAN DEFAULT TRUE,
                           is_account_non_locked BOOLEAN DEFAULT TRUE,
                           is_credentials_non_expired BOOLEAN DEFAULT TRUE,
                           is_enabled BOOLEAN DEFAULT TRUE
);
CREATE TABLE user_rented_places (
                                    user_username VARCHAR(255),
                                    place_id INTEGER,
                                    PRIMARY KEY (user_username, place_id),
                                    FOREIGN KEY (user_username) REFERENCES app_users(username) ON DELETE CASCADE,
                                    FOREIGN KEY (place_id) REFERENCES place(id) ON DELETE CASCADE
);
CREATE TABLE user_planning_to_rent (
                                       user_username VARCHAR(255),
                                       place_id INTEGER,
                                       PRIMARY KEY (user_username, place_id),
                                       FOREIGN KEY (user_username) REFERENCES app_users(username) ON DELETE CASCADE,
                                       FOREIGN KEY (place_id) REFERENCES place(id) ON DELETE CASCADE
);




