CREATE DEFINER=`admin`@`%` PROCEDURE `saveCredential`(IN _user_id INT, IN _password VARCHAR(100))
BEGIN

	UPDATE credentials SET enabled = 0 AND disabled_at = NOW() WHERE user_id = _user_id AND enabled = 1;
    INSERT INTO credentials (user_id,password) VALUES (_user_id, _password); 
    
    SELECT credential_id idCredential, created_at createdAt, disabled_at disabledAt, user_id idUser, enabled FROM credentials WHERE credential_id = last_insert_id();

END