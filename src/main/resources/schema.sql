CREATE TABLE IF NOT EXISTS `host` (
    `id` int NOT NULL AUTO_INCREMENT,
    `host` varchar(50) DEFAULT NULL,
    `port` int DEFAULT NULL,
    `user` varchar(50) DEFAULT NULL,
    `password` varchar(50) DEFAULT NULL,
    `create_at` datetime DEFAULT CURRENT_TIMESTAMP,
    `delete_at` datetime DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `instances` (
`id` int NOT NULL AUTO_INCREMENT,
`instance_name` varchar(50) DEFAULT NULL,
`instance_type` tinyint DEFAULT NULL COMMENT '1.MySQL',
`host_id` int DEFAULT NULL,
`create_at` datetime DEFAULT CURRENT_TIMESTAMP,
`delete_at` datetime DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
