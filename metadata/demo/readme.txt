/* 
 * MYSQL:TABLE
 */

CREATE TABLE t_user(
    id INT PRIMARY KEY AUTO_INCREMENT,
    uid BIGINT NOT NULL UNIQUE,
    screen_name VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(32) NOT NULL UNIQUE,
    province INT NULL ,
    city INT NULL ,
    location VARCHAR(32) NULL ,
    description VARCHAR(128) NULL ,
    url VARCHAR(128) NULL ,
    profile_image_url VARCHAR(128) NULL ,
    domain VARCHAR(32) NULL ,
    gender VARCHAR(1) NULL  DEFAULT 'm',
    followers_count INT NOT NULL  DEFAULT 0,
    friends_count INT NOT NULL  DEFAULT 0,
    statuses_count INT NOT NULL  DEFAULT 0,
    favourites_count INT NOT NULL  DEFAULT 0,
    created_at DATETIME NOT NULL ,
    allow_all_act_msg VARCHAR(1) NULL  DEFAULT 'f',
    remark VARCHAR(32) NULL ,
    geo_enabled VARCHAR(1) NULL  DEFAULT 't',
    verified VARCHAR(1) NULL  DEFAULT 'f',
    allow_all_comment VARCHAR(1) NULL  DEFAULT 't',
    avatar_large VARCHAR(128) NULL ,
    verified_reason VARCHAR(32) NULL ,
    online_status INT NULL ,
    lang VARCHAR(8) NULL ,
    weihao VARCHAR(32) NULL ,
    verifiedType INT NULL ,
    create_date TIMESTAMP NULL  DEFAULT now()
)ENGINE=InnoDB DEFAULT CHARSET=utf8;