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

SET @current = (SELECT NOW());

INSERT INTO
	dimDate
VALUES (
	@current
);

INSERT INTO
	factBenefits
VALUES (
	3,
	@current,
	15,
	850000,
	1000
);

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