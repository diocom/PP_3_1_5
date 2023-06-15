INSERT INTO `roles` (`name`) VALUES ('USER');
INSERT INTO `roles` (`name`) VALUES ('ADMIN');

INSERT INTO `users` (`name`,`last_name`,`user_name`,`age`, `password`, `enabled`) VALUES ('Федор','Семенов','semen',30,'$2a$10$ctM2SBSmfNOUjkQCB2AZ4eUhlPIqZ.1DT6Ds1T2xHUiTpxcOySYEq', 1);
INSERT INTO `users` (`name`,`last_name`,`user_name`,`age`, `password`, `enabled`) VALUES ('Иоанн','Волков','ioann@volkov.io',35, '$2a$10$gWd3Fyt0Fe6ljcyZQHm4U.GpxorohcYS4TcIetcDpVqchoN9FJC0O', 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 1);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (1, 2);
INSERT INTO `users_roles` (`user_id`, `role_id`) VALUES (2, 1);