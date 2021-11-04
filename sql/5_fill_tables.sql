-- Filling users table
INSERT INTO `users` (`email`, `password`, `name`, `phone`, `role`, `city_id`)
-- passwords 123456sS, Abcdef9, dwe54DSws, wdsad5wd5sWDsda652s
VALUES ('admin@gmail.com', '$31$14$Fs3Fi-2rlGMW3eCO2cw7F68p4NKVPVs4Al6N_7gu150', 'Алексей', '+375294822432', 0, 16);
INSERT INTO `users` (`email`, `password`, `name`, `phone`, `city_id`)
VALUES ('slowman@yandex.ru', '$31$14$1a944_v9szD_A4yzg6QMcE5NhV9-w3OeFu82k64knlU', 'Vasiliy', '+375245520036', 95),
       ('example@mail.ru', '$31$14$kD5S6aQM-ETST189g2HVODDrEnxgcmbrq8s1RREZgD8', 'Георгий', '+375332140036', 54),
       ('vadim.malynich@gmail.com', '$31$14$yAHS2sIdUHYlJJV5zDixYywuniTQrfT5q_P4Yh5mhrw', 'Vadim', '+375336695412', 54);


-- Filling messengers table
INSERT INTO `messengers` (user_id, telegram, viber, whatsapp)
VALUES (1, 1, 1, 1);
INSERT INTO `messengers` (user_id)
VALUES (2);
INSERT INTO `messengers` (user_id, viber)
VALUES (3, 1);
INSERT INTO `messengers` (user_id, viber, whatsapp)
VALUES (4, 1, 1);


-- Filling ads_info table
INSERT INTO `ads_info` (`user_id`, `category_id`, `date`, `topic`, `material`, `size`, `sex`, `description`)
VALUES ('3', '3', '2021-07-23', 'Приталеная рубашка', 'Хлопок', 4, 0,
        'Рубашка новая. Продаю так как не подошла по размеру.'),
       ('3', '1', '2021-08-09', 'Свитер бабушкин', 'Шерстяные нитки', 3, 2, 'Бабушка мне связала и вам свяжет'),
       ('4', '1', '2021-05-16', 'Худи clown', 'Лен', 6, 2, 'Лиметированное худи может оказаться у вас.'),
       ('2', '4', '2021-06-29', 'Майка Polo', 'Хлопок', 5, 0, 'Майка новая. Продаю так как не подошла по размеру.'),
       ('3', '1', '2021-08-26', 'Панамка', 'Солома', 3, 0, 'Черная панама хорошо защищает от солнца зимой и летом'),
       ('2', '2', '2021-08-15', 'Чёрные брюки', 'Шелк', 5, 1, 'Молодежные стильные брюки.');


-- Filling likes table
INSERT INTO `likes` (`ad_info_id`, `user_id`)
VALUES ('1', '4'),
       ('2', '2'),
       ('2', '1'),
       ('2', '4'),
       ('5', '3'),
       ('5', '2');


-- Filling comments table
INSERT INTO `comments` (`ad_info_id`, `user_id`, `comment`, `comment_date`)
VALUES ('2', '2', 'Эх не мой размер. Так бы взял.', '2021-08-20 18:05:49'),
       ('6', '1', 'Эксклюзивный товар!', '2021-08-20 22:36:09'),
       ('6', '4', 'Покупал уже. Жена носит с удовольствием', '2021-08-22 10:08:30'),
       ('2', '1', 'Возможно скоро появятся другие размеры следите за объявлениями', '2021-08-22 11:04:58'),
       ('6', '2', 'Можете посоветовать друзьям.', '2021-08-24 16:26:10'),
       ('5', '3', 'Коллекционное издание', '2021-08-26 15:01:38');
