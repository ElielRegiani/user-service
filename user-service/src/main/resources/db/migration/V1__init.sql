CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE INDEX idx_user_phone ON users (phone);

CREATE TABLE preferences (
    user_id BIGINT PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE,
    risk_profile VARCHAR(20) NOT NULL
);

CREATE TABLE preferences_favorite_assets (
    user_id BIGINT NOT NULL REFERENCES preferences (user_id) ON DELETE CASCADE,
    asset_symbol VARCHAR(32) NOT NULL,
    PRIMARY KEY (user_id, asset_symbol)
);

CREATE TABLE portfolio (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    symbol VARCHAR(32) NOT NULL,
    quantity NUMERIC(19, 8) NOT NULL,
    average_price NUMERIC(19, 8) NOT NULL,
    UNIQUE (user_id, symbol)
);

CREATE INDEX idx_portfolio_user ON portfolio (user_id);

CREATE TABLE alerts (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE,
    symbol VARCHAR(32) NOT NULL,
    condition_type VARCHAR(20) NOT NULL,
    threshold NUMERIC(19, 8) NOT NULL,
    UNIQUE (user_id, symbol, condition_type)
);

CREATE INDEX idx_alerts_user ON alerts (user_id);
