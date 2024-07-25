CREATE TABLE IF NOT EXISTS product(
        id UUID PRIMARY KEY,
        code VARCHAR(10) NOT NULL UNIQUE,
        name VARCHAR(64),
        price_eur DECIMAL(10,2),
        price_usd DECIMAL (10,2),
        is_available BOOLEAN,
        created_at TIMESTAMPTZ NOT NULL,
        updated_at TIMESTAMPTZ NOT NULL,
        deleted_at TIMESTAMP WITH TIME ZONE
);