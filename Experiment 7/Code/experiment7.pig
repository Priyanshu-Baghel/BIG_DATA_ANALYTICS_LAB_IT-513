students = LOAD 'students.csv' USING PigStorage(',')
        AS (student_id:int, name:chararray, age:int,
            subject:chararray, score:int, grade_level:int);

high_performers = FILTER students BY score > 85;

grouped_subject = GROUP students BY subject;

project_data = FOREACH students GENERATE name, score;

sorted_students = ORDER students BY score DESC;

DUMP high_performers;
DUMP grouped_subject;
DUMP project_data;
DUMP sorted_students;
