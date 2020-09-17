CREATE DEFINER=`root`@`localhost` FUNCTION `createAccount`( 
login_id1 INT, 
account_type1 varchar(32),
account_currnecy1 varchar(32)
) RETURNS int
    READS SQL DATA
BEGIN

 DECLARE done INT DEFAULT FALSE;
 
 DECLARE account_number INT;
 DECLARE account_number0 INT;
 DECLARE account_numberZ INT;
 DECLARE v_counter INT;
 DECLARE login_id2 INT;
 DECLARE account_type2 varchar(32);
 DECLARE account_currnecy2 varchar(32);
 
 DECLARE csr CURSOR FOR 
 SELECT account_number FROM bank.bank_account;
 
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 
 set v_counter = 10;
 set login_id2 = login_id1;
 set account_type2 = account_type1;
 set account_currnecy2 = account_currnecy1;
 
 set account_number = 0;
 
 OPEN csr;
 read_loop: LOOP
 FETCH csr INTO account_number;
 IF done THEN
 LEAVE read_loop;
 END IF;
 IF cast(account_number as unsigned) < cast(account_number0 as unsigned) then
 SET account_number = account_number0;
 END IF;
 END LOOP;
 CLOSE csr;
 
 set account_number = account_number+1;

Insert into bank.bank_account(login_id, account_number, account_type, account_currency, account_balance)
 VALUES (login_id2, LPAD(account_number, 15, '0'), account_type2, account_currnecy2, '0.00');
 RETURN account_number;

 End