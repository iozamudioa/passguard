CREATE DEFINER=`admin`@`%` PROCEDURE `saveUser`(IN _username VARCHAR(45), in _password VARCHAR(100))
BEGIN
	DECLARE _user_id INT DEFAULT NULL;
		
	INSERT INTO users (username) VALUES (_username);
    SET _user_id = last_insert_id();

	INSERT INTO credentials (user_id,password) VALUES (_user_id, _password); 
    
    SELECT u.username,u.user_id as idUser FROM
    users  u WHERE user_id = _user_id;
END