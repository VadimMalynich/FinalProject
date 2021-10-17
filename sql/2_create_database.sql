CREATE DATABASE `ads_db` DEFAULT CHARACTER SET utf8;

create user 'ads_app'@localhost
    identified by 'ads_password';

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `ads_db`.*
    TO ads_app@localhost;

GRANT SELECT,INSERT,UPDATE,DELETE
    ON `ads_db`.*
    TO ads_user@'%';
#IDENTIFIED BY 'ads_password';
