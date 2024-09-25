CREATE TABLE IF NOT EXISTS matches (
    id BIGSERIAL PRIMARY KEY,
    player_one_id BIGINT REFERENCES players(id),
    player_two_id BIGINT REFERENCES players(id),
    winner_id BIGINT REFERENCES players(id),
    CONSTRAINT player_ids_not_equal CHECK (player_one_id <> player_two_id)
);
