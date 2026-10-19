# Write your MySQL query statement below
SELECT DISTINCT email as Email FROM Person WHERE email IN(SELECT email FROM Person GROUP BY email HAVING COUNT(*)>1);