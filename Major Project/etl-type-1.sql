USE DW;


SELECT
	*
FROM
	factBenefits AS benefits
	INNER JOIN dimCompany AS company on benefits.company_id = company.id
WHERE
	benefits.company_id = 3
ORDER BY benefits.date_time DESC
-- LIMIT 1
;

UPDATE factBenefits AS benefits
SET work_hours = 10
WHERE
	benefits.company_id = 3
	AND benefits.date_time = "2018-03-16 10:27:59"
;

SELECT
	*
FROM
	factBenefits AS benefits
	INNER JOIN dimCompany AS company on benefits.company_id = company.id
WHERE
	benefits.company_id = 3
ORDER BY benefits.date_time DESC
-- LIMIT 1
;