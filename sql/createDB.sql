DROP TABLE IF EXISTS club CASCADE;
DROP TABLE IF EXISTS club_league CASCADE;
DROP TABLE IF EXISTS club_trophy CASCADE;
DROP TABLE IF EXISTS coach CASCADE;
DROP TABLE IF EXISTS enrollment CASCADE;
DROP TABLE IF EXISTS footballer CASCADE;
DROP TABLE IF EXISTS footballer_position CASCADE;
DROP TABLE IF EXISTS footballer_ability CASCADE;
DROP TABLE IF EXISTS footballer_rating CASCADE;
DROP TABLE IF EXISTS footballer_skill CASCADE;
DROP TABLE IF EXISTS training CASCADE;

-------------
---DOMAINS---
-------------

/*
stronger foot domain
 * L : left foot
 * R : right foot
 * A : all
*/
CREATE DOMAIN _STRONGER_FOOT CHAR CHECK ( VALUE IN ('L', 'R', 'A'));
CREATE DOMAIN _ABILITY SMALLINT CHECK ( VALUE >= 40 AND VALUE <= 100);

/*
footballer's position  domain
 * GK : goal keeper
 * LB : left back
 * RB : right back
 * CB : centre back
 * DMF : defensive midfielder
 * CMF : centre midfielder
 * AMF : attack midfielder
 * RMF : right midfielder
 * LMF : left midfielder
 * LWF : left winger forward
 * RWF : right winger forward
 * SS : second striker
 * CF : centre forward
*/
CREATE DOMAIN _POS VARCHAR(3) CHECK ( VALUE IN
                                      ('GK', 'LB', 'RB', 'CB', 'DMF', 'CMF', 'AMF', 'LMF', 'RMF', 'LWF', 'RWF', 'SS',
                                       'CF'));
CREATE DOMAIN _RATE SMALLINT CHECK ( VALUE > 0 AND VALUE <= 100);

------------
---TABLES---
------------
CREATE TABLE coach
(
    coach_id SERIAL,
    name     VARCHAR(255) NOT NULL,
    dob      DATE         NOT NULL,
    PRIMARY KEY (coach_id)
);

CREATE TABLE club
(
    club_id  SERIAL,
    coach_id SERIAL,
    country  VARCHAR(64)  NOT NULL,
    name     VARCHAR(255) NOT NULL,
    PRIMARY KEY (club_id),
    FOREIGN KEY (coach_id) REFERENCES coach (coach_id)
);

CREATE TABLE footballer
(
    footballer_id SERIAL,
    name          VARCHAR(255) NOT NULL,
    nationality   VARCHAR(64)  NOT NULL,
    height        INTEGER      NOT NULL,
    weight        INTEGER      NOT NULL,
    dob           DATE         NOT NULL,
    stronger_foot CHAR         NOT NULL,
    club_id       SERIAL,
    PRIMARY KEY (footballer_id),
    FOREIGN KEY (club_id) REFERENCES club (club_id)
);

CREATE TABLE club_league
(
    club_id SERIAL,
    league  TEXT NOT NULL,
    FOREIGN KEY (club_id) REFERENCES club (club_id)
);

CREATE TABLE club_trophy
(
    club_id SERIAL,
    name    TEXT,
    date    DATE,
    FOREIGN KEY (club_id) REFERENCES club (club_id)
);

CREATE TABLE footballer_ability
(
    footballer_id        INTEGER NOT NULL,
    last_update          TIMESTAMP,
    offensive_awareness  _ABILITY,
    ball_control         _ABILITY,
    dribbling            _ABILITY,
    tight_possession     _ABILITY,
    low_pass             _ABILITY,
    lofted_pass          _ABILITY,
    finishing            _ABILITY,
    heading              _ABILITY,
    set_piece_taking     _ABILITY,
    curl                 _ABILITY,
    speed                _ABILITY,
    acceleration         _ABILITY,
    kicking_power        _ABILITY,
    jumping              _ABILITY,
    physical_contact     _ABILITY,
    balance              _ABILITY,
    stamina              _ABILITY,
    defensive_awareness  _ABILITY,
    tackling             _ABILITY,
    aggression           _ABILITY,
    defensive_engagement _ABILITY,
    gk_awareness         _ABILITY,
    gk_catching          _ABILITY,
    gk_parrying          _ABILITY,
    gk_reflexes          _ABILITY,
    gk_reach             _ABILITY,
    weak_foot_usage      _ABILITY,
    weak_foot_accuracy   _ABILITY,
    form                 _ABILITY,
    injury_resistance    _ABILITY,
    FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id)
);

CREATE TABLE footballer_position
(
    footballer_id INTEGER,
    position      _POS,
    FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id)
);

CREATE TABLE footballer_rating
(
    footballer_id INTEGER,
    last_update   TIMESTAMP,
    as_gk         _RATE,
    as_lb         _RATE,
    as_cb         _RATE,
    as_rb         _RATE,
    as_dmf        _RATE,
    as_lmf        _RATE,
    as_rmf        _RATE,
    as_cmf        _RATE,
    as_amf        _RATE,
    as_lwf        _RATE,
    as_rwf        _RATE,
    as_ss         _RATE,
    as_cf         _RATE,
    FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id)
);

CREATE TABLE footballer_skill
(
    footballer_id INTEGER,
    skill         TEXT,
    FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id)
);

---------------
---RELATIONS---
---------------
CREATE TABLE enrollment
(
    footballer_id    SERIAL,
    club_id          SERIAL,
    enrollment_start DATE    NOT NULL,
    enrollment_end   DATE,
    price            INTEGER NOT NULL,
    salary           INTEGER NOT NULL,
    FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id),
    FOREIGN KEY (club_id) REFERENCES club (club_id)
);

CREATE TABLE training
(
    club_id        SERIAL,
    coach_id       SERIAL,
    training_start DATE NOT NULL,
    training_end   DATE,
    FOREIGN KEY (club_id) REFERENCES club (club_id),
    FOREIGN KEY (coach_id) REFERENCES coach (coach_id)
);
