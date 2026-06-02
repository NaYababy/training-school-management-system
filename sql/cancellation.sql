-- 课时注销记录表
CREATE TABLE IF NOT EXISTS `edu_cancellation_record` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `student_id` BIGINT(20) NOT NULL COMMENT '学生ID',
  `subject_id` BIGINT(20) NOT NULL COMMENT '科目ID',
  `teacher_id` BIGINT(20) NOT NULL COMMENT '操作教师ID',
  `cancel_hours` INT NOT NULL COMMENT '注销课时数',
  `cancel_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注销时间',
  `photo_url` VARCHAR(500) DEFAULT NULL COMMENT '注销拍照地址',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  INDEX `idx_student_id` (`student_id`),
  INDEX `idx_subject_id` (`subject_id`),
  INDEX `idx_teacher_id` (`teacher_id`),
  INDEX `idx_cancel_time` (`cancel_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='课时注销记录表';

-- 课时注销管理二级菜单
INSERT INTO `sys_menu` VALUES('503', '课时注销', '5', '4', 'cancellation', 'education/cancellation/index', '', '', 1, 0, 'C', '0', '0', 'education:cancellation:list', 'user', 'admin', sysdate(), '', null, '课时注销管理菜单');

-- 课时注销管理按钮权限
INSERT INTO `sys_menu` VALUES('5030', '注销查询', '503', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5031', '课时注销', '503', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5032', '注销修改', '503', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5033', '注销删除', '503', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5034', '注销导出', '503', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:export', '#', 'admin', sysdate(), '', null, '');

-- 数据统计二级菜单
INSERT INTO `sys_menu` VALUES('504', '数据统计', '5', '5', 'statistics', 'education/statistics/index', '', '', 1, 0, 'C', '0', '0', 'education:statistics:list', 'user', 'admin', sysdate(), '', null, '数据统计菜单');

-- 数据统计按钮权限
INSERT INTO `sys_menu` VALUES('5040', '统计查询', '504', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:statistics:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5041', '统计导出', '504', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:statistics:export', '#', 'admin', sysdate(), '', null, '');