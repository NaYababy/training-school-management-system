-- 教室表
CREATE TABLE IF NOT EXISTS `edu_classroom` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `classroom_code` VARCHAR(50) NOT NULL COMMENT '教室编号',
  `classroom_name` VARCHAR(100) NOT NULL COMMENT '教室名称',
  `floor` VARCHAR(20) NOT NULL COMMENT '楼层',
  `capacity` INT NOT NULL COMMENT '容量',
  `facilities` VARCHAR(500) DEFAULT NULL COMMENT '设施',
  `status` VARCHAR(20) DEFAULT '启用' COMMENT '状态（启用/停用）',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_classroom_code` (`classroom_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教室表';

-- 课程表
CREATE TABLE IF NOT EXISTS `edu_course_schedule` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_id` BIGINT(20) NOT NULL COMMENT '科目ID',
  `teacher_id` BIGINT(20) NOT NULL COMMENT '教师ID',
  `classroom_id` BIGINT(20) NOT NULL COMMENT '教室ID',
  `class_date` DATE NOT NULL COMMENT '上课日期',
  `start_time` TIME NOT NULL COMMENT '开始时间',
  `end_time` TIME NOT NULL COMMENT '结束时间',
  `status` VARCHAR(20) DEFAULT '正常' COMMENT '状态（正常/调课/取消）',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  INDEX `idx_class_date` (`class_date`),
  INDEX `idx_teacher_id` (`teacher_id`),
  INDEX `idx_classroom_id` (`classroom_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课程表';

-- 教室管理二级菜单
INSERT INTO `sys_menu` VALUES('505', '教室管理', '5', '6', 'classroom', 'education/classroom/index', '', '', 1, 0, 'C', '0', '0', 'education:classroom:list', 'user', 'admin', sysdate(), '', null, '教室管理菜单');

-- 教室管理按钮权限
INSERT INTO `sys_menu` VALUES('5050', '教室查询', '505', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5051', '教室新增', '505', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5052', '教室修改', '505', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5053', '教室删除', '505', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5054', '教室导出', '505', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:export', '#', 'admin', sysdate(), '', null, '');

-- 课程排课管理二级菜单
INSERT INTO `sys_menu` VALUES('506', '课程排课', '5', '7', 'schedule', 'education/schedule/index', '', '', 1, 0, 'C', '0', '0', 'education:schedule:list', 'user', 'admin', sysdate(), '', null, '课程排课菜单');

-- 课程排课按钮权限
INSERT INTO `sys_menu` VALUES('5060', '排课查询', '506', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5061', '新增排课', '506', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5062', '修改排课', '506', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5063', '删除排课', '506', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5064', '排课导出', '506', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:export', '#', 'admin', sysdate(), '', null, '');