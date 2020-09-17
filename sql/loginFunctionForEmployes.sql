CREATE DEFINER=`root`@`localhost` FUNCTION `login_employes`(
login_id_1 INT, 
password_1 varchar(32) 
) RETURNS int
    READS SQL DATA
cal:BEGIN
 DECLARE done INT DEFAULT false;
 DECLARE v_counter10 VARCHAR(32);
 DECLARE v_counter varchar(32);
 DECLARE csr CURSOR FOR 
 SELECT password_e FROM bank.users
 WHERE login_id_e = login_id_1;
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 OPEN csr;
 read_loop: LOOP
 FETCH csr INTO v_counter;

 IF done THEN
 LEAVE read_loop;
 END IF;
 set v_counter10 = password_1;
 if v_counter != v_counter10 then
 leave cal;
 END IF;
 END LOOP;
 CLOSE csr;

 RETURN login_id_1;
END