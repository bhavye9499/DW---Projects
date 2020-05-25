# Link: http://chriseiffel.com/everything-linux/how-to-import-a-large-csv-file-to-mysql/

DATABASE_NAME=DW;

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='id,name' --local $DATABASE_NAME ./CSVs/dimensions/dimCompany.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='id,name' --local $DATABASE_NAME ./CSVs/dimensions/dimPosition.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='id,job_description' --local $DATABASE_NAME ./CSVs/dimensions/dimJobDescription.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='id,name' --local $DATABASE_NAME ./CSVs/dimensions/dimStudent.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='date_time' --local $DATABASE_NAME ./CSVs/dimensions/dimDate.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='company_id,position_id,jd_id,date_time' --local $DATABASE_NAME ./CSVs/facts/factJobRequirement.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='company_id,student_id,date_time,backlogs,allowed_backlogs,student_gpa,required_gpa' --local $DATABASE_NAME ./CSVs/facts/factEligibility.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='company_id,date_time,work_hours,bonus_offered,paid_overtime' --local $DATABASE_NAME ./CSVs/facts/factBenefits.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='company_id,student_id,position_id,date_time,base_amount,allowances,signing_bonus,joining_bonus,expected_salary' --local $DATABASE_NAME ./CSVs/facts/factSalary.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='company_id,student_id,date_time,work_city,home_city,distance' --local $DATABASE_NAME ./CSVs/facts/factCity.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='company_id,date_time,pass_rate,number_of_rounds,syllabus' --local $DATABASE_NAME ./CSVs/facts/factInterviewProcess.csv

sudo mysqlimport --ignore-lines=1 --fields-terminated-by=, --columns='company_id,student_id,date_time,student_gpa,ctc,work_city,is_selected' --local $DATABASE_NAME ./CSVs/facts/factAppearForInteview.csv