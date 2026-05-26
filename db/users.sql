CREATE TABLE public.users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR(255),
    first_name VARCHAR(255),
    last_name VARCHAR(255),
    gender VARCHAR(50),
    phone VARCHAR(50),
    address VARCHAR(255)
);