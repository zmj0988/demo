CREATE TABLE `t_group` (
  `groupId` int(11) NOT NULL auto_increment,
  `groupName` varchar(256) NOT NULL,
  `status` int(2) NOT NULL,
  `isAdvance` int(1) NOT NULL DEFAULT 0
);