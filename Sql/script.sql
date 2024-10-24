CREATE DATABASE `human_friends`;  
USE `human_friends`;  


CREATE TABLE `animals` (  
  `id` INT AUTO_INCREMENT PRIMARY KEY,  
  `name` VARCHAR(50) NOT NULL,  
  `birth_date` DATE NOT NULL,  
  `type` ENUM('pet', 'pack_animal') NOT NULL  
);  

CREATE TABLE `pets` (  
  `id` INT AUTO_INCREMENT PRIMARY KEY,  
  `animal_id` INT NOT NULL,  
  `pet_type` ENUM('dog', 'cat', 'hamster'),FOREIGN KEY (`animal_id`) REFERENCES `animals`(`id`)  
);  

CREATE TABLE `pack_animals` (  
  `id` INT AUTO_INCREMENT PRIMARY KEY,  
  `animal_id` INT NOT NULL,  
  `pack_type` ENUM('horse', 'camel', 'donkey'),  
  FOREIGN KEY (`animal_id`) REFERENCES `animals`(`id`)  
);  

CREATE TABLE `commands` (  
  `id` INT AUTO_INCREMENT PRIMARY KEY,  
  `animal_id` INT NOT NULL,  
  `command` VARCHAR(50) NOT NULL,  
  FOREIGN KEY (`animal_id`) REFERENCES `animals`(`id`)  
);  


INSERT INTO `animals` (`name`, `birth_date`, `type`) VALUES  
  ('Fido', '2020-01-01', 'pet'),  
  ('Whiskers', '2019-05-15', 'pet'),  
  ('Hammy', '2021-03-10', 'pet'),  
  ('Buddy', '2018-12-10', 'pet'),  
  ('Smudge', '2020-02-20', 'pet'),  
  ('Peanut', '2021-08-01', 'pet'),  
  ('Bella', '2019-11-11', 'pet'),  
  ('Oliver', '2020-06-30', 'pet'),  
  ('Thunder', '2015-07-21', 'pack_animal'),  
  ('Sandy', '2016-11-03', 'pack_animal'),  
  ('Eeyore', '2017-09-18', 'pack_animal'),  
  ('Storm', '2014-05-05', 'pack_animal'),  
  ('Dune', '2018-12-12', 'pack_animal'),  
  ('Burro', '2019-01-23', 'pack_animal'),  
  ('Blaze', '2016-02-29', 'pack_animal'),  
  ('Sahara', '2015-08-14', 'pack_animal');  

INSERT INTO `pets` (`animal_id`, `pet_type`) VALUES  
  (1, 'dog'),  
  (2, 'cat'),  
  (3, 'hamster'),  
  (4, 'dog'),  
  (5, 'cat'),  
  (6, 'hamster'),  
  (7, 'dog'),  
  (8, 'cat');  

INSERT INTO `pack_animals` (`animal_id`, `pack_type`) VALUES  
  (9, 'horse'),  
  (10, 'camel'),  
  (11, 'donkey'),  
  (12, 'horse'),  
  (13, 'camel'),  
  (14, 'donkey'),  
  (15, 'horse'),  
  (16, 'camel');  

INSERT INTO `commands` (`animal_id`, `command`) VALUES  
  (1, 'Sit'),  
  (1, 'Stay'),  
  (1, 'Fetch'),  
  (2, 'Sit'),  
  (2, 'Pounce'),  
  (3, 'Roll'),  
  (3, 'Hide'),  
  (4, 'Sit'),  
  (4, 'Paw'),  
  (4, 'Bark'),  
  (5, 'Sit'),  
  (5, 'Pounce'),  
  (5, 'Scratch'),  
  (6, 'Roll'),  
  (6, 'Spin'),  
  (7, 'Sit'),  
  (7, 'Stay'),  
  (7, 'Roll'),  
  (8, 'Meow'),  
  (8, 'Scratch'),  
  (8, 'Jump'),  
  (9, 'Trot'),  
  (9, 'Canter'),  
  (9, 'Gallop'),  
  (10, 'Walk'),  
  (10, 'Carry Load'),  
  (11, 'Walk'),  
  (11, 'Carry Load'),  
  (11, 'Bray'),  
  (12, 'Trot'),  
  (12, 'Canter'),  
  (13, 'Walk'),  
  (13, 'Sit'),  
  (14, 'Walk'),  
  (14, 'Bray'),  
  (14, 'Kick'),  
  (15, 'Trot'),  
  (15, 'Jump'),  
  (15, 'Gallop'),  
  (16, 'Walk'),  
  (16, 'Run');  


DELETE FROM `pack_animals` WHERE `pack_type` = 'camel';  


CREATE TABLE `horses_and_donkeys` AS  
SELECT `animals`.`id`, `animals`.`name`, `animals`.`birth_date`, `pack_animals`.`pack_type`  
FROM `animals`  
JOIN `pack_animals` ON `animals`.`id` = `pack_animals`.`animal_id`  
WHERE `pack_animals`.`pack_type` IN ('horse', 'donkey');  


CREATE TABLE `young_animals` AS  
SELECT   
  `animals`.`id`,  
  `animals`.`name`,  
  `animals`.`type`,  
  `animals`.`birth_date`,TIMESTAMPDIFF(MONTH, `animals`.`birth_date`, CURDATE()) / 12.0 AS `age_years`  
FROM `animals`  
WHERE TIMESTAMPDIFF(MONTH, `animals`.`birth_date`, CURDATE()) BETWEEN 12 AND 36;  


CREATE TABLE `all_animals` AS  
SELECT  
  `animals`.`id`,  
  `animals`.`name`,  
  `animals`.`birth_date`,  
  `animals`.`type`,  
  `pets`.`pet_type`,  
  `pack_animals`.`pack_type`,  
  `commands`.`command`,  
  `young_animals`.`age_years`  
FROM `animals`  
LEFT JOIN `pets` ON `animals`.`id` = `pets`.`animal_id`  
LEFT JOIN `pack_animals` ON `animals`.`id` = `pack_animals`.`animal_id`  
LEFT JOIN `commands` ON `animals`.`id` = `commands`.`animal_id`  
LEFT JOIN `young_animals` ON `animals`.`id` = `young_animals`.`id`;