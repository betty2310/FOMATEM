/* 



*/
CREATE OR REPLACE FUNCTION update_timeEntry_footballerAbility()
RETURNS TRIGGER AS $$
    BEGIN
        UPDATE footballer_ability
        SET last_update = now() + INTERVAL '7 hours' -- UTC+7
        WHERE footballer_id = new.footballer_id;
        RETURN new;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_timeEntry_footballerAbility
AFTER INSERT OR UPDATE ON footballer_ability
-- Avoid trigger loops itself multiple times
FOR EACH ROW WHEN (pg_trigger_depth() < 1) 
EXECUTE FUNCTION update_timeEntry_footballerAbility();

/* 



*/
CREATE FUNCTION calc_rating() RETURNS trigger
    LANGUAGE plpgsql
AS
$$
DECLARE
        _as_gk INT;
        _as_lb _as_gk%TYPE; _as_rb _as_gk%TYPE; _as_cb _as_gk%TYPE;
        _as_dmf _as_gk%TYPE; _as_cmf _as_gk%TYPE; _as_rmf _as_gk%TYPE; _as_lmf _as_gk%TYPE; _as_amf _as_gk%TYPE;
        _as_lwf _as_gk%TYPE; _as_rwf _as_gk%TYPE; _as_ss _as_gk%TYPE; _as_cf _as_gk%TYPE;
        defval _as_gk%TYPE;
        _new footballer_ability%ROWTYPE;
    BEGIN
        _new = new;
        _as_gk = (_new.form + _new.injury_resistance + _new.defensive_engagement
                      + _new.gk_awareness + _new.gk_catching + _new.gk_parrying
                      + _new.gk_reflexes + _new.gk_reach) / 8;

        
        defval = _new.form + _new.injury_resistance + _new.defensive_awareness
                      + _new.tackling + _new.aggression + _new.defensive_engagement
                      + _new.jumping + _new.physical_contact;
        _as_lb = (defval + _new.speed
                      + _new.acceleration + _new.weak_foot_usage + _new.weak_foot_accuracy) / 12;
        _as_rb = _as_lb;
        _as_cb = (defval + _new.ball_control
                      + _new.tight_possession + _new.low_pass + _new.lofted_pass
                      + _new.heading) / 13;

        
        defval = _new.form + _new.injury_resistance + _new.offensive_awareness
                       + _new.ball_control + _new.low_pass + _new.lofted_pass
                       + _new.balance + _new.stamina;
        _as_dmf = (defval + _new.defensive_awareness
                       + _new.defensive_engagement + _new.tight_possession) / 11;
        _as_cmf = (defval + _new.heading
                       + _new.set_piece_taking + _new.curl + _new.speed
                       + _new.dribbling) / 13;
        _as_rmf = (defval + _new.weak_foot_usage
                       + _new.weak_foot_accuracy + _new.speed) / 12;
        _as_lmf = _as_rmf;
        _as_amf = (defval + _new.finishing
                       + _new.set_piece_taking + _new.kicking_power + _new.dribbling) / 12;

        
        defval = _new.form + _new.injury_resistance + _new.curl
                       + _new.kicking_power + _new.jumping + _new.offensive_awareness
                       + _new.finishing + _new.dribbling;
        _as_lwf = (defval + _new.weak_foot_usage
                       + _new.weak_foot_accuracy + _new.speed) / 11;
        _as_rwf = _as_lwf;
        _as_ss = (defval  + _new.low_pass
                       + _new.lofted_pass + _new.heading + _new.set_piece_taking) / 12;
        _as_cf = (defval + _new.weak_foot_usage
                       + _new.weak_foot_accuracy + _new.balance + _new.physical_contact
                       + _new.ball_control) / 13;


        IF tg_op = 'INSERT' THEN
            INSERT INTO footballer_rating
            VALUES (_new.footballer_id, now() + INTERVAL '7 hours', _as_gk, _as_lb, _as_cb,
                    _as_rb, _as_dmf, _as_lmf, _as_rmf, _as_cmf,
                    _as_amf, _as_lwf, _as_rwf, _as_ss, _as_cf);
        ELSE
            UPDATE footballer_rating
            SET last_update = now() + INTERVAL '7 hours', as_gk = _as_gk, as_lb = _as_lb,
                as_cb = _as_cb, as_rb = _as_rb, as_dmf = _as_dmf,
                as_lmf = _as_lmf, as_rmf = _as_rmf, as_cmf = _as_cmf,
                as_amf = _as_amf, as_lwf = _as_lwf, as_rwf = _as_rwf,
                as_ss = _as_ss, as_cf = _as_cf
            WHERE footballer_id = _new.footballer_id;
        END IF;


        RETURN new;
    END;
$$;

/* 



*/
CREATE FUNCTION footballer_age(footballer_id_inp integer, OUT age integer) RETURNS integer
    LANGUAGE plpgsql
AS
$$
BEGIN
        SELECT date_part('y', age(dob))::INT INTO age FROM footballer
        WHERE footballer_id = footballer_id_inp;
    END;
$$;

/* 



*/
CREATE OR REPLACE FUNCTION year_between_date(IN date1 timestamp, IN date2 timestamp, OUT yeardiff DOUBLE PRECISION)
AS $$
    BEGIN
				IF date2 IS NULL THEN
            date2 := now() + INTERVAL '7h';
        END IF;
				-- Tinh do dai khoang thoi gian bang ngay / so ngay trong 1 nam
        SELECT abs(date_part('d', date1 - date2) / 365.2425)::numeric(5,2) INTO yeardiff;
    END;
$$ LANGUAGE plpgsql;

/* 



*/
CREATE OR REPLACE FUNCTION update_log_footballer()
RETURNS TRIGGER AS $$
    BEGIN
        IF tg_op = 'DELETE' THEN
            INSERT INTO footballer_log
            VALUES ('D', now() + INTERVAL '7 hours', old.footballer_id);
        ELSIF tg_op = 'INSERT' THEN
            INSERT INTO footballer_log
            VALUES ('I', now() + INTERVAL '7 hours', new.footballer_id);
        ELSE
            INSERT INTO footballer_log
            VALUES ('U', now() + INTERVAL '7 hours', new.footballer_id);
        END IF;
        RETURN new;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_log_footballer
AFTER INSERT OR UPDATE OR DELETE ON footballer
FOR EACH ROW EXECUTE FUNCTION update_log_footballer();


CREATE OR REPLACE FUNCTION update_log_club()
RETURNS TRIGGER AS $$
    BEGIN
        IF tg_op = 'DELETE' THEN
            INSERT INTO club_log
            VALUES ('D', now() + INTERVAL '7 hours', old.club_id);
        ELSIF tg_op = 'INSERT' THEN
            INSERT INTO club_log
            VALUES ('I', now() + INTERVAL '7 hours', new.club_id);
        ELSE
            INSERT INTO club_log
            VALUES ('U', now() + INTERVAL '7 hours', new.club_id);
        END IF;
        RETURN new;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_log_club
AFTER INSERT OR UPDATE OR DELETE ON club
FOR EACH ROW EXECUTE FUNCTION update_log_club();


CREATE OR REPLACE FUNCTION update_log_coach()
RETURNS TRIGGER AS $$
    BEGIN
        IF tg_op = 'DELETE' THEN
            INSERT INTO coach_log
            VALUES ('D', now() + INTERVAL '7 hours', old.coach_id);
        ELSIF tg_op = 'INSERT' THEN
            INSERT INTO coach_log
            VALUES ('I', now() + INTERVAL '7 hours', new.coach_id);
        ELSE
            INSERT INTO coach_log
            VALUES ('U', now() + INTERVAL '7 hours', new.coach_id);
        END IF;
        RETURN new;
    END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_update_log_coach
AFTER INSERT OR UPDATE OR DELETE ON coach
FOR EACH ROW EXECUTE FUNCTION update_log_coach();

/*



*/
CREATE OR REPLACE FUNCTION calc_club_footballer(IN club_id_in INT, OUT number_of_footballer INT)
AS $$
    BEGIN
        SELECT count(*) INTO number_of_footballer FROM footballer
        WHERE club_id = club_id_in;
    END;
$$ LANGUAGE plpgsql;

/*




*/
CREATE OR REPLACE FUNCTION func_footballer_ranking(IN footballer_id_in INT, IN pos VARCHAR(6), OUT ranking BIGINT)
AS $$
    BEGIN
        EXECUTE format('SELECT rank_col
                        FROM (SELECT footballer_id, dense_rank() OVER (ORDER BY %I DESC) AS rank_col
                              FROM footballer_rating) AS temp_ranking
                        WHERE footballer_id = $1', pos)
        USING footballer_id_in
        INTO ranking;
    END
$$ LANGUAGE plpgsql;
