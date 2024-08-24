-- 创建数据库

CREATE
DATABASE IF NOT EXISTS url_0 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_1 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_2 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_3 DEFAULT CHARACTER SET utf8;
CREATE
DATABASE IF NOT EXISTS url_4 DEFAULT CHARACTER SET utf8;

-- 在url-0数据中执行以下语句
DROP TABLE IF EXISTS WORKER_NODE;
CREATE TABLE WORKER_NODE
(
ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
PORT VARCHAR(64) NOT NULL COMMENT 'port',
TYPE INT NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
MODIFIED TIMESTAMP NOT NULL COMMENT 'modified time',
CREATED TIMESTAMP NOT NULL COMMENT 'created time',
PRIMARY KEY(ID)
)COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB;

-- 在每个数据库中执行以下语句
DROP TABLE IF EXISTS url_mapping_0;
CREATE TABLE `url_mapping_0`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    KEY `expire_time` (`expire_time`),
    UNIQUE KEY `short_url` (`short_url`)
);

DROP TABLE IF EXISTS url_mapping_1;
CREATE TABLE `url_mapping_1`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    KEY `expire_time` (`expire_time`),
    UNIQUE KEY `short_url` (`short_url`)
);


DROP TABLE IF EXISTS url_mapping_2;
CREATE TABLE `url_mapping_2`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    KEY `expire_time` (`expire_time`),
    UNIQUE KEY `short_url` (`short_url`)
);

DROP TABLE IF EXISTS url_mapping_3;
CREATE TABLE `url_mapping_3`
(
    `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
    `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
    `long_url`    varchar(512) NOT NULL COMMENT '原始url',
    `expire_time` datetime COMMENT 'key失效日期',
    `created_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
    `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
    KEY `expire_time` (`expire_time`),
    UNIQUE KEY `short_url` (`short_url`)
);
-- (
--     `id`          bigint(20) AUTO_INCREMENT PRIMARY KEY COMMENT '主键',
--     `short_url`   varchar(6)   NOT NULL COMMENT '短链key',
--     `long_url`    varchar(512) NOT NULL COMMENT '原始url',
--     `expire_time` datetime COMMENT 'key失效日期',
--     `creat_time`  datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建日期',
--     `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改日期',
--      KEY `expire_time` (`expire_time`),
--      UNIQUE KEY `short_url` (`short_url`)
-- );
