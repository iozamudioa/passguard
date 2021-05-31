CREATE DEFINER=`admin`@`%` PROCEDURE `updatePersonData`(IN _name VARCHAR(45), IN _lastname VARCHAR(45), IN _email VARCHAR(45), IN _birth_date DATE, IN _user_id INT)
BEGIN
	DECLARE _persons_data_id INT DEFAULT NULL;
    
    SELECT    persons_data_id INTO _persons_data_id FROM persons_data WHERE user_id = _user_id;
    
    IF _persons_data_id IS NULL THEN
        INSERT INTO persons_data (name, lastname, email, birth_date,user_id) VALUES (_name, _lastname, _email, _birth_date,_user_id);
        SET _persons_data_id = last_insert_id();
    ELSE 
		UPDATE persons_data SET 
			name = IFNULL(_name,name)
            ,lastname = IFNULL(_lastname, lastname)
            ,email = IFNULL(_email, email)
            ,birth_date = IFNULL(_birth_date, birth_date) 
            ,updated_at = NOW()
        WHERE persons_data_id = _persons_data_id;
    END IF; 
    SELECT _persons_data_id idPersonData, name, lastname, email, birth_date birthDate, p.created_at createdAt, updated_at updatedAt, u.user_id idUser  , username
    FROM persons_data p 
    INNER JOIN users u ON u.user_id = p.user_id WHERE persons_data_id = _persons_data_id;

END