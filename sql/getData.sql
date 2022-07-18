----------------------------
-- INSERT DATA STATEMENTS --
----------------------------

\COPY coach (coach_id, name, dob)  FROM 'coach.csv' CSV;
\COPY club (club_id, coach_id, country, name) FROM 'club.csv' CSV;
\COPY footballer (footballer_id, name, nationality, height, weight, dob, stronger_foot, club_id)  FROM 'footballer.csv' CSV;
\COPY club_league (club_id, league) FROM 'club_league.csv' CSV;
\COPY club_trophy (club_id, name, date) FROM 'club_trophy.csv' CSV;
\COPY footballer_ability (footballer_id, last_update, offensive_awareness, ball_control, dribbling, tight_possession, low_pass, lofted_pass, finishing, heading, set_piece_taking, curl, speed, acceleration, kicking_power, jumping, physical_contact, balance, stamina, defensive_awareness, tackling, aggression, defensive_engagement, gk_awareness, gk_catching, gk_parrying, gk_reflexes, gk_reach, weak_foot_usage, weak_foot_accuracy, form, injury_resistance) FROM 'footballer_ability.csv' CSV;
\COPY footballer_position (footballer_id, position) FROM 'footballer_position.csv' CSV;
\COPY footballer_skill (footballer_id, skill) FROM 'footballer_skill.csv' CSV;
\COPY enrollment (footballer_id, club_id, enrollment_start, enrollment_end, price, salary) FROM 'enrollment.csv' CSV;
\COPY training (club_id, coach_id, training_start, training_end) FROM 'training.csv' CSV;
