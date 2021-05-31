CREATE DEFINER=`admin`@`%` PROCEDURE `getCredentialsByUsername`(IN _username VARCHAR(45))
BEGIN
    SELECT u.username,c.password FROM credentials c
    INNER JOIN users  u ON c.user_id = u.user_id
    WHERE u.username = _username
    AND c.enabled = 1;
END