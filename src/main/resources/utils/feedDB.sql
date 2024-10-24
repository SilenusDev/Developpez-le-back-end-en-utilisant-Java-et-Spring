-- Insérer l'utilisateur
INSERT INTO `USERS` (`email`, `name`, `password`, `created_at`, `updated_at`)
VALUES ('user@user.com', 'user', 'user1234', NOW(), NOW());

-- Récupérer l'id du user inséré
SET @user_id = (SELECT `id` FROM `USERS` WHERE `email` = 'user@user.com');

-- Insérer des locations (rentals) pour cet utilisateur
INSERT INTO `RENTALS` (`name`, `surface`, `price`, `picture`, `description`, `owner_id`, `created_at`, `updated_at`)
VALUES 
('Appartement Centre Ville', 55.0, 1200.0, 'appartement1.jpg', 'Appartement moderne au centre-ville.', @user_id, NOW(), NOW()),
('Maison de campagne', 120.0, 1800.0, 'maison1.jpg', 'Grande maison en pleine nature.', @user_id, NOW(), NOW()),
('Studio Quartier Latin', 30.0, 850.0, 'studio1.jpg', 'Petit studio dans le Quartier Latin, idéal pour étudiant.', @user_id, NOW(), NOW());

-- Récupérer les ids des locations insérées
SET @rental1_id = (SELECT `id` FROM `RENTALS` WHERE `name` = 'Appartement Centre Ville');
SET @rental2_id = (SELECT `id` FROM `RENTALS` WHERE `name` = 'Maison de campagne');
SET @rental3_id = (SELECT `id` FROM `RENTALS` WHERE `name` = 'Studio Quartier Latin');

-- Insérer des messages pour chaque location
INSERT INTO `MESSAGES` (`rental_id`, `user_id`, `message`, `created_at`, `updated_at`)
VALUES 
(@rental1_id, @user_id, 'Je suis intéressé par cet appartement.', NOW(), NOW()),
(@rental2_id, @user_id, 'Je souhaite visiter cette maison.', NOW(), NOW()),
(@rental3_id, @user_id, 'Est-ce que ce studio est encore disponible ?', NOW(), NOW());
