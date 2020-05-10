

SELECT snum, ssn FROM students WHERE name = 'Becky';
       
SELECT name,level From major
Where snum= (SELECT  snum FROM students
       WHERE ssn = 123097834 );

SELECT name from courses
Where department_code= (SELECT  code FROM departments
       WHERE name = 'Computer Science' );
       
       
SELECT name,level From degrees
Where department_code= (SELECT  code FROM departments
       WHERE name = 'Computer Science' );
       
SELECT name From students
Where snum in(SELECT  snum FROM minor);

	
SELECT COUNT(*) FROM minor;


SELECT students.name,c_phone FROM students WHERE snum =(SELECT  snum FROM register
       WHERE course_number=(SELECT  number FROM courses
       WHERE name=('Algorithm') ));
       
       



Select name, snum from students where dob = (select   min(dob)
             from students);
             
             

Select name, snum from students where dob = (select   max(dob)
             from students);
             
             
select snum,ssn
from students where name like '%n%'
and name like '%N%';

select snum,ssn
from students where name not like '%n%'
and name not like '%N%';


SELECT course_number,courses.name,count(course_number) FROM register,courses where course_number=courses.number  group by course_number;

SELECT name FROM students where snum in(SELECT  snum FROM register
       WHERE regtime='Fall2015');
             
SELECT name,number FROM courses where department_code in(SELECT  code FROM departments
       WHERE name ='Computer Science');
       
SELECT name,number FROM courses where department_code in(SELECT  code FROM departments
       WHERE name ='Computer Science' or name ='Landscape Architect');       

       
