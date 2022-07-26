INSERT INTO fruit(id, name)
VALUES (nextval('hibernate_sequence'), 'Cherry');
INSERT INTO fruit(id, name)
VALUES (nextval('hibernate_sequence'), 'Apple');
INSERT INTO fruit(id, name)
VALUES (nextval('hibernate_sequence'), 'Banana');

INSERT INTO organization(id, organizationId, name, contactName, contactEmail, contactPhone)
VALUES (nextval('hibernate_sequence'), 'e6a625cc-718b-48c2-ac76-1dfdff9a531e', 'Ostock', 'Illary Huaylupo',
        'illaryhs@gmail.com', '888888888');
INSERT INTO organization(id, organizationId, name, contactName, contactEmail, contactPhone)
VALUES (nextval('hibernate_sequence'), 'd898a142-de44-466c-8c88-9ceb2c2429d3', 'OptimaGrowth', 'Admin',
        'illaryhs@gmail.com', '888888888');
INSERT INTO organization(id, organizationId, name, contactName, contactEmail, contactPhone)
VALUES (nextval('hibernate_sequence'), 'e839ee96-28de-4f67-bb79-870ca89743a0', 'Ostock', 'Illary Huaylupo',
        'illaryhs@gmail.com', '888888888');

INSERT INTO license(id, licenseId, description, organizationId, productName, licenseType, comment)
VALUES (nextval('hibernate_sequence'), 'f2a9c9d4-d2c0-44fa-97fe-724d77173c62', 'Software Product',
        'd898a142-de44-466c-8c88-9ceb2c2429d3', 'Ostock', 'complete', 'I AM DEV');
INSERT INTO license(id, licenseId, description, organizationId, productName, licenseType, comment)
VALUES (nextval('hibernate_sequence'), '279709ff-e6d5-4a54-8b55-a5c37542025b', 'Software Product',
        'e839ee96-28de-4f67-bb79-870ca89743a0', 'Ostock', 'complete', 'I AM DEV');
