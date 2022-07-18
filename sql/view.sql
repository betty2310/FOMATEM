CREATE OR REPLACE VIEW footballer_overview AS
SELECT  footballer.footballer_id, footballer.name, footballer.nationality, 
		footballer.height, footballer.weight, footballer_age(footballer.footballer_id) AS age, 
		footballer.stronger_foot, enrollment.price, club.name AS club, coach.name AS coach
FROM footballer 
JOIN enrollment ON (footballer.footballer_id = enrollment.footballer_id
				            AND footballer.club_id = enrollment.club_id)
JOIN club ON footballer.club_id = club.club_id
JOIN coach ON club.coach_id = coach.coach_id
ORDER BY footballer.footballer_id;

CREATE OR REPLACE VIEW club_overview AS
WITH tempClubFootballer(club_id, number_of_footballer) AS (
    SELECT club_id, count(name)
    FROM footballer
    GROUP BY club_id
),
tempClubTrophy(club_id, number_of_trophy) AS (
    SELECT club_id, count(name)
    FROM club_trophy
    GROUP BY club_id
)
SELECT club.club_id,
       club.name AS club_name,
       club.country AS club_country,
       coach.name AS coach_name,
       tempclubfootballer.number_of_footballer AS number_of_footballer,
       coalesce(tempclubtrophy.number_of_trophy, 0) AS number_of_trophy
FROM club
LEFT JOIN coach ON club.coach_id = coach.coach_id
NATURAL JOIN tempclubfootballer
NATURAL LEFT JOIN tempclubtrophy;

create or replace view coach_overview as 
(
	with tmp0 as 
	(
		with tmp1 as (
		select coach_id, count(coach_id) from training
		join club_trophy on (training.club_id = club_trophy.club_id
							 and club_trophy.date > training.training_start)
		group by training.coach_id
		)
		select club.coach_id, coach.name as coachName, club.name as club, tmp1.count as totalTrophy
		from club 
		join tmp1 on club.coach_id = tmp1.coach_id
		join coach on club.coach_id = coach.coach_id
	)
	select coach.coach_id, coach.name as coach, club.name as club, 
	tmp0.totaltrophy from coach 
	left join tmp0 on coach.coach_id = tmp0.coach_id
	left join club on coach.coach_id = club.coach_id
	order by coach.coach_id
);
