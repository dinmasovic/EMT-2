CREATE MATERIALIZED VIEW host_accommodation_count
AS
SELECT
    h.id AS host_id,
    h.name,
    h.surname,
    COUNT(p.id) AS accommodation_count
FROM
    host h
        LEFT JOIN
    place p ON h.id = p.host_id
GROUP BY
    h.id, h.name, h.surname;

CREATE MATERIALIZED VIEW hosts_by_country AS
SELECT
    c.id AS country_id,
    c.name AS country_name,
    COUNT(h.id) AS host_count
FROM
    country c
        LEFT JOIN
    host h ON h.country_id = c.id
GROUP BY
    c.id, c.name;
