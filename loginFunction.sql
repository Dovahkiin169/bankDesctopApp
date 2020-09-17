CREATE DEFINER=`root`@`localhost` FUNCTION `login`( login_id_1 INT, password_1 varchar(32) ) RETURNS int
    READS SQL DATA
BEGIN
 DECLARE done INT DEFAULT FALSE;
 DECLARE v_counter1 INT;
 DECLARE v_counter2 INT;
 DECLARE v_counter VARCHAR(32);
 DECLARE csr CURSOR FOR 
 SELECT password FROM bank.users
 WHERE login_id = login_id_1;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 OPEN csr;
 read_loop: LOOP
 FETCH csr INTO v_counter;

 IF done THEN
 LEAVE read_loop;
 END IF;

 IF v_counter = password_1 then
 SET v_counter2 = 100;
 END IF;
 END LOOP;
 CLOSE csr;

 RETURN v_counter2;
 END