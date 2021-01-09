CREATE TABLE `t_base_retry` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `business_id` varchar(255) NOT NULL,
  `service_name` varchar(255) NOT NULL,
  `service_method` varchar(255) NOT NULL,
  `service_method_param` varchar(255) NOT NULL,
  `error_message` varchar(255) NOT NULL,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_time` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;