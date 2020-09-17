CREATE DEFINER=`root`@`localhost` FUNCTION `transaction_operaion`( 
account_number_from1 INT,
account_number_to1 INT,
v_total INT
) RETURNS int
    READS SQL DATA
BEGIN

 DECLARE done INT DEFAULT FALSE;
 
 DECLARE v_counter INT;
 DECLARE v_counter1 INT;
 DECLARE v_counter2 varchar(3);
 DECLARE v_counter3 varchar(3);
 
 DECLARE csr CURSOR FOR 
 SELECT account_balance FROM bank.bank_account
 WHERE account_number = account_number_from1;
 
 DECLARE csr1 CURSOR FOR
 SELECT account_balance FROM bank.bank_account
 WHERE account_number = account_number_to1;
 
 DECLARE csr2 CURSOR FOR
 SELECT account_balance FROM bank.bank_account
 WHERE account_number = account_number_from1;
 
 DECLARE csr3 CURSOR FOR
 SELECT account_currency FROM bank.bank_account
 WHERE account_number = account_number_to1;
 
 DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;
 OPEN csr;
 OPEN csr1;
 OPEN csr2;
 OPEN csr3;
 read_loop: LOOP
 FETCH csr INTO v_counter;
 FETCH csr INTO v_counter1;
 FETCH csr INTO v_counter2;
 FETCH csr INTO v_counter3;
 IF done THEN
 LEAVE read_loop;
 END IF;
 IF v_counter2 = v_counter3 then
 SET v_counter = v_counter - v_total;
 SET v_counter1 = v_total + v_counter1;
 END IF;
 IF v_counter2 = 'PLN' AND v_counter3 = 'USD' then
 SET v_counter = v_counter - v_total;
 SET v_counter1 = v_total/3.765 + v_counter1;
 END IF;
IF v_counter2 = 'USD' AND v_counter3 = 'PLN' then
 SET v_counter = v_counter - v_total;
 SET v_counter1 = v_total/3.75 + v_counter1;
 END IF;
 END LOOP;
 CLOSE csr;
 CLOSE csr1;
 CLOSE csr2;
 CLOSE csr3;
 
 update bank.bank_account
 set account_balance = v_counter
 where account_number = account_number_from1;
 
 update bank.bank_account
 set account_balance = v_counter1
 where account_number = account_number_to1;

 RETURN v_counter;

 End