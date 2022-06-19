CREATE TABLE footballer
(
    footballer_id SERIAL PRIMARY KEY,
    name          VARCHAR(255)  NOT NULL,
    nationality   VARCHAR(64)   NOT NULL,
    height        NUMERIC(3, 2) NOT NULL,
    weight        NUMERIC(3, 2) NOT NULL,
    dob           DATE          NOT NULL,
    stronger_foot CHAR(1) CHECK ( stronger_foot IN ('L', 'R', 'A') ),
    club_id       CHAR(8)       NOT NULL
);

CREATE TABLE club
(
    club_id  CHAR(8) PRIMARY KEY,
    coach_id SERIAL      NOT NULL,
    country  VARCHAR(64) NOT NULL
);

CREATE TABLE coach
(
    coach_id SERIAL PRIMARY KEY ,
    name     VARCHAR(255) NOT NULL,
    dob      DATE         NOT NULL
);

CREATE TABLE footballer_position
(
    footballer_id INT NOT NULL,
    position      VARCHAR(3) CHECK ( position IN
                                     ('GK', 'LB', 'CB', 'RB', 'DMF', 'LMF', 'RMF', 'CMF', 'AMF', 'LWF', 'SS', 'RWF',
                                      'CF') )
);

CREATE TABLE footballer_rating
(
    footballer_id INT     NOT NULL,
    club_id       CHAR(8) NOT NULL,
    as_gk         SMALLINT CHECK ( as_gk >= 0 AND as_gk <= 100 ),
    as_lb         SMALLINT CHECK ( as_lb >= 0 AND as_lb <= 100 ),
    as_cb         SMALLINT CHECK ( as_cb >= 0 AND as_cb <= 100 ),
    as_rb         SMALLINT CHECK ( as_rb >= 0 AND as_rb <= 100 ),
    as_dmf        SMALLINT CHECK ( as_dmf >= 0 AND as_dmf <= 100 ),
    as_lmf        SMALLINT CHECK ( as_lmf >= 0 AND as_lmf <= 100 ),
    as_rmf        SMALLINT CHECK ( as_rmf >= 0 AND as_rmf <= 100 ),
    as_cmf        SMALLINT CHECK ( as_cmf >= 0 AND as_cmf <= 100 ),
    as_amf        SMALLINT CHECK ( as_amf >= 0 AND as_amf <= 100 ),
    as_lwf        SMALLINT CHECK ( as_lwf >= 0 AND as_lwf <= 100 ),
    as_rwf        SMALLINT CHECK ( as_rwf >= 0 AND as_rwf <= 100 ),
    as_ss         SMALLINT CHECK ( as_ss >= 0 AND as_ss <= 100 ),
    as_cf         SMALLINT CHECK ( as_cf >= 0 AND as_cf <= 100 )
);

CREATE TABLE footballer_ability
(
    footballer_id        INT NOT NULL,
    offensive_awareness  SMALLINT CHECK ( offensive_awareness >= 0 AND offensive_awareness <= 100 ),
    ball_control         SMALLINT CHECK ( ball_control >= 0 AND ball_control <= 100 ),
    dribbling            SMALLINT CHECK ( dribbling >= 0 AND dribbling <= 100 ),
    tight_possession     SMALLINT CHECK ( tight_possession >= 0 AND tight_possession <= 100 ),
    low_pass             SMALLINT CHECK ( low_pass >= 0 AND low_pass <= 100 ),
    lofted_pass          SMALLINT CHECK ( lofted_pass >= 0 AND lofted_pass <= 100 ),
    finishing            SMALLINT CHECK ( finishing >= 0 AND finishing <= 100 ),
    heading              SMALLINT CHECK ( heading >= 0 AND heading <= 100 ),
    set_piece_taking     SMALLINT CHECK ( set_piece_taking >= 0 AND set_piece_taking <= 100 ),
    curl                 SMALLINT CHECK ( curl >= 0 AND curl <= 100 ),
    speed                SMALLINT CHECK ( speed >= 0 AND speed <= 100 ),
    acceleration         SMALLINT CHECK ( acceleration >= 0 AND acceleration <= 100 ),
    kicking_power        SMALLINT CHECK ( kicking_power >= 0 AND kicking_power <= 100 ),
    jumping              SMALLINT CHECK ( jumping >= 0 AND jumping <= 100 ),
    physical_contact     SMALLINT CHECK ( physical_contact >= 0 AND physical_contact <= 100 ),
    balance              SMALLINT CHECK ( balance >= 0 AND balance <= 100 ),
    stamina              SMALLINT CHECK ( stamina >= 0 AND stamina <= 100 ),
    defensive_awareness  SMALLINT CHECK ( defensive_awareness >= 0 AND defensive_awareness <= 100 ),
    tackling             SMALLINT CHECK ( tackling >= 0 AND tackling <= 100 ),
    aggression           SMALLINT CHECK ( aggression >= 0 AND aggression <= 100 ),
    defensive_engagement SMALLINT CHECK ( defensive_engagement >= 0 AND defensive_engagement <= 100 ),
    gk_awareness         SMALLINT CHECK ( gk_awareness >= 0 AND gk_awareness <= 100 ),
    gk_catching          SMALLINT CHECK ( gk_catching >= 0 AND gk_catching <= 100 ),
    gk_parrying          SMALLINT CHECK ( gk_parrying >= 0 AND gk_parrying <= 100 ),
    gk_reflexes          SMALLINT CHECK ( gk_reflexes >= 0 AND gk_reflexes <= 100 ),
    gk_reach             SMALLINT CHECK ( gk_reach >= 0 AND gk_reach <= 100 ),
    weak_foot_usage      SMALLINT CHECK ( weak_foot_usage >= 0 AND weak_foot_usage <= 100 ),
    weak_foot_accuracy   SMALLINT CHECK ( weak_foot_accuracy >= 0 AND weak_foot_accuracy <= 100 ),
    form                 SMALLINT CHECK ( form >= 0 AND form <= 100 ),
    injury_resistance    SMALLINT CHECK ( injury_resistance >= 0 AND injury_resistance <= 100 )
);

CREATE TABLE footballer_skill
(
    footballer_id INT NOT NULL,
    skill         TEXT
);

CREATE TABLE enrollment
(
    footballer_id    INT     NOT NULL,
    club_id          CHAR(8) NOT NULL,
    enrollment_start DATE    NOT NULL,
    enrollment_end   DATE,
    price            MONEY   NOT NULL,
    salary           MONEY   NOT NULL
);

CREATE TABLE club_league
(
    club_id CHAR(8) NOT NULL,
    league  TEXT    NOT NULL
);

CREATE TABLE club_trophy
(
    club_id CHAR(8) NOT NULL,
    name    TEXT,
    date    DATE
);

CREATE TABLE training
(
    club_id        CHAR(8) NOT NULL,
    coach_id       INT     NOT NULL,
    training_start DATE    NOT NULL,
    training_end   DATE
);

ALTER TABLE footballer
    ADD CONSTRAINT "footballer_club_id_fk" FOREIGN KEY (club_id) REFERENCES club (club_id);
ALTER TABLE footballer_position
    ADD CONSTRAINT "footballer_position_id_fk" FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id);
ALTER TABLE footballer_rating
    ADD CONSTRAINT "footballer_rating_id_fk" FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id);
ALTER TABLE footballer_rating
    ADD CONSTRAINT "footballer_rating_club_id_fk" FOREIGN KEY (club_id) REFERENCES club (club_id);
ALTER TABLE footballer_ability
    ADD CONSTRAINT "footballer_ability_id_fk" FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id);
ALTER TABLE footballer_skill
    ADD CONSTRAINT "footballer_skill_id_fk" FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id);
ALTER TABLE enrollment
    ADD CONSTRAINT "enrollment_footballer_id_fk" FOREIGN KEY (footballer_id) REFERENCES footballer (footballer_id);
ALTER TABLE enrollment
    ADD CONSTRAINT "enrollment_club_id_fk" FOREIGN KEY (club_id) REFERENCES club (club_id);
ALTER TABLE club_league
    ADD CONSTRAINT "club_league_id_fk" FOREIGN KEY (club_id) REFERENCES club (club_id);
ALTER TABLE club_trophy
    ADD CONSTRAINT "club_trophy_id_fk" FOREIGN KEY (club_id) REFERENCES club (club_id);
ALTER TABLE training
    ADD CONSTRAINT "training_club_id_fk" FOREIGN KEY (club_id) REFERENCES club (club_id);
ALTER TABLE training
    ADD CONSTRAINT "coach_id_fk" FOREIGN KEY (coach_id) REFERENCES coach (coach_id);
