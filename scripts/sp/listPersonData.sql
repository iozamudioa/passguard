CREATE DEFINER=`admin`@`%` PROCEDURE `listPersonData`(IN _user_id INT)
BEGIN
	SELECT u.username , u.user_id idUser  ,persons_data_id idPersonData, name, lastname, email, birth_date birthDate, u.created_at createdAt, p.updated_at updatedAt
    FROM users u
    LEFT JOIN persons_data p ON p.user_id = u.user_id
    WHERE u.user_id = IFNULL(_user_id, u.user_id);
     
END