-- 科目表
CREATE TABLE IF NOT EXISTS `edu_subject` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `subject_code` VARCHAR(50) NOT NULL COMMENT '科目编码',
  `subject_name` VARCHAR(100) NOT NULL COMMENT '科目名称',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_subject_code` (`subject_code`),
  UNIQUE KEY `uk_subject_name` (`subject_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='科目表';

-- 初始数据
INSERT INTO `edu_subject` (`subject_code`, `subject_name`, `remark`) VALUES
('MATH', '数学', '数学科目'),
('CHINESE', '语文', '语文科目'),
('ENGLISH', '英语', '英语科目'),
('PHYSICS', '物理', '物理科目'),
('CHEMISTRY', '化学', '化学科目'),
('BIOLOGY', '生物', '生物科目');

-- 培训管理一级菜单
INSERT INTO `sys_menu` VALUES('5', '培训管理', '0', '5', 'education', null, '', '', 1, 0, 'M', '0', '0', '', 'education', 'admin', sysdate(), '', null, '培训管理目录');

-- 科目管理二级菜单
INSERT INTO `sys_menu` VALUES('500', '科目管理', '5', '1', 'subject', 'education/subject/index', '', '', 1, 0, 'C', '0', '0', 'education:subject:list', 'education', 'admin', sysdate(), '', null, '科目管理菜单');

-- 科目管理按钮权限
INSERT INTO `sys_menu` VALUES('5000', '科目查询', '500', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5001', '科目新增', '500', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5002', '科目修改', '500', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5003', '科目删除', '500', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5004', '科目导出', '500', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:export', '#', 'admin', sysdate(), '', null, '');