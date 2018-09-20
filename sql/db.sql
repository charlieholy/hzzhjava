CREATE TABLE `hiveTable` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '库表唯一Id',
  `dbName` VARCHAR(100) COMMENT '库名',
  `tableName` VARCHAR(100) COMMENT '表名',
  `location` VARCHAR(500) COMMENT '路径',
  `createTime` DATETIME COMMENT '创建时间',
  `updateTime` DATETIME COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

CREATE TABLE `hiveLocation` (
  `id` int(100) NOT NULL AUTO_INCREMENT COMMENT '路径唯一Id',
  `location` VARCHAR(500) COMMENT '路径',
  `size` INT(10) COMMENT '大小',
  `createTime` DATETIME COMMENT '创建时间',
  `updateTime` DATETIME COMMENT '更新时间',
   PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;