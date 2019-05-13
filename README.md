# Movie
# Framework
Springboot Mybatis JPA Redis Shiro 

## database
- ### movie
CREATE TABLE movie (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  director varchar(255) DEFAULT NULL,
  releaseDate datetime DEFAULT NULL,
  PRIMARY KEY (id),
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

- ### user
CREATE TABLE user (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  password varchar(255) DEFAULT NULL,
  salt varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
- ### tag
CREATE TABLE tag (
  id int(11) NOT NULL AUTO_INCREMENT,
  mid int(11) DEFAULT NULL,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_tag_movie FOREIGN KEY (mid) REFERENCES movie (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

- ### property
CREATE TABLE property (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(255) DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

- ### propertyValue
CREATE TABLE propertyvalue (
  id int(11) NOT NULL AUTO_INCREMENT,
  pid int(11) DEFAULT NULL,
  value varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_propertyvalue_property FOREIGN KEY (pid) REFERENCES property (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
