CREATE DEFINER=`root`@`localhost` FUNCTION `register`( 
password1 varchar(255), 
email_address1 varchar(70), 
pesel1 bigint(11), 
name1 varchar(50), 
name2 varchar(50), 
surname1 varchar(100), 
address1 varchar(255), 
sex1 varchar(1), 
birth_date1 DATE, 
register_date1 DATE ) RETURNS date
    READS SQL DATA
Check1:Begin

DECLARE done INT DEFAULT FALSE;

DECLARE password2 varchar(255);
DECLARE email_address2 varchar(70);
DECLARE pesel2 bigint(11);
DECLARE name1_1 varchar(50);
DECLARE name2_1 varchar(50);
DECLARE surname2 varchar(100);
DECLARE address2 varchar(255);
DECLARE sex2 varchar(1);
DECLARE birth_date2 DATE;
DECLARE register_date2 DATE;

DECLARE v_counter varchar(11);
DECLARE csr CURSOR FOR
SELECT pesel FROM bank.users;

DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

IF email_address1 LIKE '%@%.%' AND LENGTH(pesel1) = 11 THEN
  SET email_address2 = email_address1;
  SET pesel2 = pesel1;
  SET password2 = password1;
  SET name1_1 = name1;
  SET name2_1 = name2;
  SET surname2 = surname1;
  SET address2 = address1;
  SET sex2 = sex1;
  SET birth_date2 = birth_date1;
  SET register_date2 = register_date1;
ELSE
  LEAVE Check1;
END IF;


OPEN csr;
read_loop: LOOP
FETCH csr INTO v_counter;
IF done THEN
LEAVE read_loop;
END IF;
IF v_counter = pesel1 then
LEAVE Check1;
END IF;

END LOOP;
CLOSE csr;

Insert into bank.users(password, email_address, pesel, name, name_2, surename, address, sex, birth_date, register_date)
Values (password2, email_address2, pesel2, name1_1, name2_1, surname2, address2, sex2, birth_date2, register_date2);
RETURN register_date2;

End