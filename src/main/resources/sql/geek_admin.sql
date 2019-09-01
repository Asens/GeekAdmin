/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : geek_admin

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-09-01 20:46:36
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_blob_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_calendars
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('CountScheduler', 'TASK_2', 'DEFAULT', '0 0/30 * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('CountScheduler', 'TASK_2', 'DEFAULT', null, 'com.geekutil.modules.sys.task.schedule.ScheduleJobBean', '0', '0', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000027800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('CountScheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('CountScheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_paused_trigger_grps
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('CountScheduler', 'DESKTOP-V2UJRDE1567338544461', '1567341983771', '15000');

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simple_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int(11) DEFAULT NULL,
  `INT_PROP_2` int(11) DEFAULT NULL,
  `LONG_PROP_1` bigint(20) DEFAULT NULL,
  `LONG_PROP_2` bigint(20) DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_simprop_triggers
-- ----------------------------

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) DEFAULT NULL,
  `PRIORITY` int(11) DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('CountScheduler', 'TASK_2', 'DEFAULT', 'TASK_2', 'DEFAULT', null, '1567342800000', '1567341000000', '5', 'WAITING', 'CRON', '1566431870000', '0', null, '2', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B020000787000000000000000027800);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `parent_code` varchar(100) DEFAULT NULL,
  `icon` varchar(50) DEFAULT NULL,
  `component` varchar(50) DEFAULT NULL,
  `sort_num` int(11) DEFAULT NULL,
  `is_menu` int(11) DEFAULT NULL,
  `real_path` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', '控制台', 'dashboard', '1', null, 'dashboard', 'RouteView', '1', '1', null);
INSERT INTO `sys_permission` VALUES ('2', '系统管理', 'system', '1', null, 'setting', 'PageView', '9', '1', null);
INSERT INTO `sys_permission` VALUES ('3', '菜单管理', 'menu', '1', 'system', 'bars', 'MenuList', '3', '1', null);
INSERT INTO `sys_permission` VALUES ('7', '角色管理', 'system.role', '1', 'system', 'team', 'RoleList', '2', '1', '/system/role');
INSERT INTO `sys_permission` VALUES ('16', '用户管理', 'system.user', '1', 'system', 'user', 'UserList', '1', '1', '/system/user');
INSERT INTO `sys_permission` VALUES ('25', '列表', 'system.menu.list', '1', 'menu', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('26', '新增', 'system.menu.save', '1', 'menu', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('27', '工作台', 'workplace', '1', 'dashboard', null, 'workplace', '10', '1', null);
INSERT INTO `sys_permission` VALUES ('28', '编辑', 'system.menu.edit', '1', 'menu', null, 'MenuAdd', '10', '0', '/system/menu/edit/:id');
INSERT INTO `sys_permission` VALUES ('29', '删除', 'system.menu.delete', '1', 'menu', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('31', '列表', 'system.user.list', '1', 'system.user', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('32', '编辑', 'system.user.edit', '1', 'system.user', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('33', '新增', 'system.user.add', '1', 'system.user', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('34', '删除', 'system.user.delete', '1', 'system.user', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('35', '保存', 'system.user.save', '1', 'system.user', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('36', '更新', 'system.user.update', '1', 'system.user', null, null, '10', '0', null);
INSERT INTO `sys_permission` VALUES ('37', '定时任务', 'system.schedule.manage', '1', 'system.schedule', '', 'Schedule', '4', '1', '/system/schedule');
INSERT INTO `sys_permission` VALUES ('41', '定时任务', 'system.schedule', '1', 'system', 'schedule', null, '4', '1', null);
INSERT INTO `sys_permission` VALUES ('42', '定时任务日志', 'system.schedule.log', '1', 'system.schedule', null, 'ScheduleLog', '10', '1', '/system/schedule/log');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `code` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '拥有所有权限', '1', '2019-07-21 18:47:29', 'admin');
INSERT INTO `sys_role` VALUES ('2', '用户管理员', '管理用户权限', '1', '2019-07-26 23:29:40', 'user_admin');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `permission_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=351 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('332', '1', '3');
INSERT INTO `sys_role_permission` VALUES ('333', '1', '7');
INSERT INTO `sys_role_permission` VALUES ('334', '1', '16');
INSERT INTO `sys_role_permission` VALUES ('335', '1', '25');
INSERT INTO `sys_role_permission` VALUES ('336', '1', '26');
INSERT INTO `sys_role_permission` VALUES ('337', '1', '27');
INSERT INTO `sys_role_permission` VALUES ('338', '1', '28');
INSERT INTO `sys_role_permission` VALUES ('339', '1', '29');
INSERT INTO `sys_role_permission` VALUES ('340', '1', '31');
INSERT INTO `sys_role_permission` VALUES ('341', '1', '32');
INSERT INTO `sys_role_permission` VALUES ('342', '1', '33');
INSERT INTO `sys_role_permission` VALUES ('343', '1', '34');
INSERT INTO `sys_role_permission` VALUES ('344', '1', '35');
INSERT INTO `sys_role_permission` VALUES ('345', '1', '36');
INSERT INTO `sys_role_permission` VALUES ('346', '1', '37');
INSERT INTO `sys_role_permission` VALUES ('347', '1', '42');
INSERT INTO `sys_role_permission` VALUES ('348', '1', '2');
INSERT INTO `sys_role_permission` VALUES ('349', '1', '41');
INSERT INTO `sys_role_permission` VALUES ('350', '1', '1');

-- ----------------------------
-- Table structure for sys_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule_job`;
CREATE TABLE `sys_schedule_job` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `remark` varchar(255) DEFAULT NULL,
  `bean_name` varchar(20) DEFAULT NULL,
  `method_name` varchar(20) DEFAULT NULL,
  `params` varchar(50) DEFAULT NULL,
  `cron_expression` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_schedule_job
-- ----------------------------
INSERT INTO `sys_schedule_job` VALUES ('2', '测试任务', 'testTask', 'test', null, '0 0/30 * * * ?', '1', '2019-08-22 07:57:51');

-- ----------------------------
-- Table structure for sys_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_schedule_job_log`;
CREATE TABLE `sys_schedule_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_id` bigint(20) DEFAULT NULL,
  `bean_name` varchar(20) DEFAULT NULL,
  `remark` varchar(20) DEFAULT NULL,
  `method_name` varchar(20) DEFAULT NULL,
  `params` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `error` varchar(2000) DEFAULT NULL,
  `times` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1168138937114296323 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_schedule_job_log
-- ----------------------------
INSERT INTO `sys_schedule_job_log` VALUES ('1165075694242361346', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor205.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '7', '2019-08-24 09:37:46', '2019-08-24 09:37:46');
INSERT INTO `sys_schedule_job_log` VALUES ('1165075701771137026', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor205.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-08-24 09:37:48', '2019-08-24 09:37:48');
INSERT INTO `sys_schedule_job_log` VALUES ('1165075710117801986', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor205.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-08-24 09:37:50', '2019-08-24 09:37:50');
INSERT INTO `sys_schedule_job_log` VALUES ('1165088838389985282', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor205.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 10:30:00', '2019-08-24 10:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165096388120408065', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor205.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 11:00:00', '2019-08-24 11:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165103937867608065', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor205.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 11:30:00', '2019-08-24 11:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165111487681916929', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor501.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '2', '2019-08-24 12:00:00', '2019-08-24 12:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165119037416534018', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor501.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 12:30:00', '2019-08-24 12:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165239834143465473', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor277.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '6', '2019-08-24 20:30:00', '2019-08-24 20:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165241593515581442', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '2', '2019-08-24 20:37:00', '2019-08-24 20:37:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165241601681891329', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-08-24 20:37:02', '2019-08-24 20:37:02');
INSERT INTO `sys_schedule_job_log` VALUES ('1165241605146386434', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 20:37:02', '2019-08-24 20:37:02');
INSERT INTO `sys_schedule_job_log` VALUES ('1165247383982940162', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 21:00:00', '2019-08-24 21:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165254932773838849', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 21:30:00', '2019-08-24 21:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165262482512650241', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 22:00:00', '2019-08-24 22:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165270032356319233', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 22:30:00', '2019-08-24 22:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165277582137073666', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-24 23:00:00', '2019-08-24 23:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165285131854913537', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-08-24 23:30:00', '2019-08-24 23:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165292681560170498', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-25 00:00:00', '2019-08-25 00:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165300231273816065', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-25 00:30:00', '2019-08-25 00:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165307781104902145', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-08-25 01:00:00', '2019-08-25 01:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165315330814353409', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-25 01:30:00', '2019-08-25 01:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165322880544776194', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor372.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-08-25 02:00:00', '2019-08-25 02:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1165436127734259714', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor310.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '22', '2019-08-25 09:30:00', '2019-08-25 09:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1167066068410277889', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor251.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '4', '2019-08-29 21:26:48', '2019-08-29 21:26:48');
INSERT INTO `sys_schedule_job_log` VALUES ('1167066872068284417', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor251.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-08-29 21:30:00', '2019-08-29 21:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1167987942107406337', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor309.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '5', '2019-09-01 10:30:00', '2019-09-01 10:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1167995491028328449', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor309.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-09-01 11:00:00', '2019-09-01 11:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1168003040750362625', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor309.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-09-01 11:30:00', '2019-09-01 11:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1168010590514339842', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor309.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-09-01 12:00:00', '2019-09-01 12:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1168018140320260097', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor309.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-09-01 12:30:00', '2019-09-01 12:30:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1168025689966796801', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor309.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '0', '2019-09-01 13:00:00', '2019-09-01 13:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1168131387572617218', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor178.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '13', '2019-09-01 20:00:00', '2019-09-01 20:00:00');
INSERT INTO `sys_schedule_job_log` VALUES ('1168138937114296322', '2', 'testTask', '测试任务', 'test', null, '0', 'java.lang.RuntimeException: 执行定时任务失败:java.lang.IllegalStateException: asdad<br>	at com.geekutil.modules.sys.task.schedule.TestTask.test(TestTask.java:16)<br>	at sun.reflect.GeneratedMethodAccessor263.invoke(Unknown Source)<br>	at sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:45005)<br>	at java.lang.reflect.Method.invoke(Method.java:498)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleRunnable.run(ScheduleRunnable.java:38)<br>	at com.geekutil.modules.sys.task.schedule.ScheduleJobBean.executeInternal(ScheduleJobBean.java:51)<br>	at org.springframework.scheduling.quartz.QuartzJobBean.execute(QuartzJobBean.java:75)<br>	at org.quartz.core.JobRunShell.run(JobRunShell.java:202)<br>	at org.quartz.simpl.SimpleThreadPool$WorkerThread.run(SimpleThreadPool.java:573)<br>', '1', '2019-09-01 20:30:00', '2019-09-01 20:30:00');

-- ----------------------------
-- Table structure for sys_system_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_system_log`;
CREATE TABLE `sys_system_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL,
  `message` varchar(100) DEFAULT NULL,
  `method_info` varchar(200) DEFAULT NULL,
  `params` varchar(500) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_system_log
-- ----------------------------

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `avatar` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `last_login_date` datetime DEFAULT NULL,
  `last_login_ip` varchar(50) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `deleted` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'Asens', '176b44781cefe21d7683785035381680', 'Asens', '2019-07-21 15:48:20', 'https://img.asens.cn/images/note/1/as148668994644749.jpg', '15233333333', null, null, '1', '0');
INSERT INTO `sys_user` VALUES ('2', '2', null, null, null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('3', '3', null, '阿斯蒂', null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('4', '阿斯蒂', null, 'as奥术大师', null, null, '2131651', null, null, '1', null);
INSERT INTO `sys_user` VALUES ('5', '沙发菜单', null, 'asdasd', null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('6', '撒旦法', null, '爱仕达多', null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('7', '撒旦法', null, 'asdasd', null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('8', '微服', null, '111111111', null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('9', '撒旦法', null, null, null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('10', '撒旦法', null, null, null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('11', '耳根', null, null, null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('12', '电饭煲', null, null, null, null, null, null, null, '1', null);
INSERT INTO `sys_user` VALUES ('13', 'asdasdasd', '6bc3e2c3df59abcc045e37af2b4d81a5', '1112', '2019-08-12 03:05:21', null, '15222222222', null, null, '1', '0');
INSERT INTO `sys_user` VALUES ('14', 'afawfa', '176b44781cefe21d7683785035381680', '13222222222', '2019-08-12 03:06:49', null, '13222222222', null, null, '1', '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('9', '1', '1');
INSERT INTO `sys_user_role` VALUES ('10', '1', '2');
