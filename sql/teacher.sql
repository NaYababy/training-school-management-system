-- 教师表
CREATE TABLE IF NOT EXISTS `edu_teacher` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_name` VARCHAR(100) NOT NULL COMMENT '教师姓名',
  `password` VARCHAR(100) NOT NULL COMMENT '登录密码（加密存储）',
  `phone` VARCHAR(20) NOT NULL COMMENT '手机号码',
  `commission_rate` DECIMAL(5,2) DEFAULT 0.00 COMMENT '抽成比例（%）',
  `create_by` VARCHAR(64) DEFAULT '' COMMENT '创建者',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_by` VARCHAR(64) DEFAULT '' COMMENT '更新者',
  `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remark` VARCHAR(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_teacher_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教师表';

-- 教师科目关联表
CREATE TABLE IF NOT EXISTS `edu_teacher_subject` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `teacher_id` BIGINT(20) NOT NULL COMMENT '教师ID',
  `subject_id` BIGINT(20) NOT NULL COMMENT '科目ID',
  `is_main` TINYINT(1) DEFAULT 0 COMMENT '是否主带科目（0-否，1-是）',
  `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_teacher_subject` (`teacher_id`, `subject_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教师科目关联表';

-- 教师管理二级菜单
INSERT INTO `sys_menu` VALUES('502', '教师管理', '5', '3', 'teacher', 'education/teacher/index', '', '', 1, 0, 'C', '0', '0', 'education:teacher:list', 'user', 'admin', sysdate(), '', null, '教师管理菜单');

-- 教师管理按钮权限
INSERT INTO `sys_menu` VALUES('5020', '教师查询', '502', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5021', '教师新增', '502', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5022', '教师修改', '502', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5023', '教师删除', '502', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5024', '教师导出', '502', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5025', '教师导入', '502', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:import', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5026', '课时统计', '502', '7', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:hours', '#', 'admin', sysdate(), '', null, '');