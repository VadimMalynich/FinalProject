USE `ads_db`;

-- -----------------------------------------------------
-- Table `category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `category`
(
    `id`   INT         NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(45) NOT NULL,
    CONSTRAINT `PK_id` PRIMARY KEY (`id`)
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `cities`
-- -----------------------------------------------------
CREATE TABLE `cities`
(
    `id`     INT         NOT NULL AUTO_INCREMENT,
    /**
     * 1 - Брестская (Region.BREST)
     * 2 - Витебская (Region.VITEBSK)
     * 3 - Гомельская (Region.GOMEL)
     * 4 - Гродненская (Region.GRODNO)
     * 5 - Минская (Region.MINSK)
     * 6 - Могилевская (Region.MOGILEV)
     */
    `region` TINYINT     NOT NULL CHECK ( `region` IN (1, 2, 3, 4, 5, 6)),
    `city`   VARCHAR(25) NOT NULL,
    CONSTRAINT `PK_id` PRIMARY KEY (`id`),
    UNIQUE INDEX `city_UNIQUE` (`city` ASC) VISIBLE
);

-- -----------------------------------------------------
-- Table `users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `users`
(
    `id`       INT          NOT NULL AUTO_INCREMENT,
    `email`    VARCHAR(254) NOT NULL,
    `password` VARCHAR(50)  NOT NULL,
    `name`     VARCHAR(23)  NOT NULL,
    `phone`    VARCHAR(13)  NOT NULL,
    /**
     * 0 - администратор (Role.ADMINISTRATOR)
	 * 1 - пользователь (Role.USER)
     */
    `role`     TINYINT      NOT NULL CHECK (`role` IN (0, 1)) DEFAULT 1,
    `city_id`  INT          NOT NULL,

    CONSTRAINT `PK_id, PK_email` PRIMARY KEY (`id`, `email`),
    UNIQUE INDEX `email_UNIQUE` (`email` ASC) VISIBLE,
    INDEX `city_id_idx` (`city_id` ASC) VISIBLE,
    CONSTRAINT `city_id`
        FOREIGN KEY (`city_id`)
            REFERENCES `cities` (`id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `ads_info`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ads_info`
(
    `id`          INT          NOT NULL AUTO_INCREMENT,
    `user_id`     INT          NOT NULL,
    `category_id` INT          NOT NULL,
    `date`        DATE         NOT NULL,
    `topic`       VARCHAR(50)  NOT NULL,
    `material`    VARCHAR(50)  NOT NULL,
    /**
      * 1 - XS (ClothesSize.XS)
      * 2 - S (ClothesSize.S)
      * 3 - M (ClothesSize.M)
      * 4 - L (ClothesSize.L)
      * 5 - XL (ClothesSize.XL)
      * 6 - XXL (ClothesSize.XXL)
      * 7 - XXXL (ClothesSize.XXXL)
     */
    `size`        TINYINT   NOT NULL CHECK ( 'size' IN (1,2,3,4,5,6,7) ),
    /**
     * 0 - Мужской (ClothesSex.MAN)
     * 1 - Женский (ClothesSex.WOMAN)
     * 2 - Унисекс (ClothesSex.UNISEX)
     * 3 - Детский (ClothesSex.CHILD)
     */
    `sex`         TINYINT      NOT NULL CHECK ( `sex` IN (0, 1, 2, 3)),
    `description` VARCHAR(500) NOT NULL,
    `picture`     VARCHAR(260)  NOT NULL DEFAULT 'default.png',
    CONSTRAINT `PK_id` PRIMARY KEY (`id`),
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `category_id_idx` (`category_id` ASC) VISIBLE,
    CONSTRAINT `category_id`
        FOREIGN KEY (`category_id`)
            REFERENCES `category` (`id`)
            ON DELETE RESTRICT
            ON UPDATE RESTRICT,
    CONSTRAINT `user_id`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `comments`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `comments`
(
    `id`           INT          NOT NULL AUTO_INCREMENT,
    `ad_info_id`   INT          NOT NULL,
    `user_id`      INT          NOT NULL,
    `comment`      VARCHAR(200) NOT NULL,
    `comment_date` TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT `PK_id` PRIMARY KEY (`id`),
    INDEX `user_comment_idx` (`user_id` ASC) VISIBLE,
    INDEX `ad_comment_idx` (`ad_info_id` ASC) VISIBLE,
    CONSTRAINT `ad_comment`
        FOREIGN KEY (`ad_info_id`)
            REFERENCES `ads_info` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `user_comment`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB
    AUTO_INCREMENT = 1;


-- -----------------------------------------------------
-- Table `likes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `likes`
(
    `ad_info_id` INT NOT NULL,
    `user_id`    INT NOT NULL,
    CONSTRAINT `PK_ad_info_id, PK_user_id` PRIMARY KEY (`ad_info_id`, `user_id`),
    CONSTRAINT `UN_ads_users` UNIQUE (`ad_info_id`, `user_id`),
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `ad_like`
        FOREIGN KEY (`ad_info_id`)
            REFERENCES `ads_info` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE,
    CONSTRAINT `user_like`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `messengers`
-- -----------------------------------------------------
CREATE TABLE `messengers`
(
    `user_id`  INT NOT NULL,
    `telegram` BIT NOT NULL DEFAULT 0,
    `viber`    BIT NOT NULL DEFAULT 0,
    `whatsapp` BIT NOT NULL DEFAULT 0,
    CONSTRAINT `PK_user_id` PRIMARY KEY (`user_id`),
    UNIQUE INDEX `user_id_UNIQUE` (`user_id` ASC) VISIBLE,
    CONSTRAINT `id_user`
        FOREIGN KEY (`user_id`)
            REFERENCES `users` (`id`)
            ON DELETE CASCADE
            ON UPDATE CASCADE
)
    ENGINE = InnoDB;
