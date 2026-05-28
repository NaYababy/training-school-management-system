-- 学生表
CREATE TABLE IF NOT EXISTS `edu_student` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_name` VARCHAR(100) NOT NULL COMMENT '学生姓名',
  `grade` VARCHAR(50) NOT NULL COMMENT '年级',
  `gender` VARCHAR(10) NOT NULL COMMENT '性别',
  `phone` VARCHAR(20) NOT NULL COMMENT '手机号码',
  `total_hours` INT DEFAULT 0 COMMENT '总课时',
  `remaining_hours` INT DEFAULT 0 COMMENT '剩余课时',
  `total_fee` DECIMAL(10,2) DEFAULT 0.00 COMMENT '缴费金额',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生表';

-- 学生科目关联表
CREATE TABLE IF NOT EXISTS `edu_student_subject` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` BIGINT(20) NOT NULL COMMENT '学生ID',
  `subject_id` BIGINT(20) NOT NULL COMMENT '科目ID',
  `teacher_id` BIGINT(20) NOT NULL COMMENT '授课教师ID',
  `subject_hours` INT DEFAULT 0 COMMENT '该科目课时数',
  `subject_remaining_hours` INT DEFAULT 0 COMMENT '该科目剩余课时',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_student_subject` (`student_id`, `subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='学生科目关联表';

-- 学生管理二级菜单
INSERT INTO `sys_menu` VALUES('501', '学生管理', '5', '2', 'student', 'education/student/index', '', '', 1, 0, 'C', '0', '0', 'education:student:list', 'people', 'admin', sysdate(), '', null, '学生管理菜单');

-- 学生管理按钮权限
INSERT INTO `sys_menu` VALUES('5010', '学生查询', '501', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5011', '学生新增', '501', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5012', '学生修改', '501', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5013', '学生删除', '501', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5014', '学生导出', '501', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5015', '学生导入', '501', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:import', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5016', '课时统计', '501', '7', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:hours', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5017', '注销记录', '501', '8', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:cancellation', '#', 'admin', sysdate(), '', null, '');