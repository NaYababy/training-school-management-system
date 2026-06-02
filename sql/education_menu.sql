-- 培训管理模块菜单权限配置

-- 培训管理一级菜单
INSERT INTO `sys_menu` VALUES('5', '培训管理', '0', '5', 'education', null, '', '', 1, 0, 'M', '0', '0', '', 'education', 'admin', sysdate(), '', null, '培训管理目录');

-- ============ 科目管理 ============
-- 科目管理二级菜单
INSERT INTO `sys_menu` VALUES('500', '科目管理', '5', '1', 'subject', 'education/subject/index', '', '', 1, 0, 'C', '0', '0', 'education:subject:list', 'education', 'admin', sysdate(), '', null, '科目管理菜单');

-- 科目管理按钮权限
INSERT INTO `sys_menu` VALUES('5000', '科目查询', '500', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5001', '科目新增', '500', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5002', '科目修改', '500', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5003', '科目删除', '500', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5004', '科目导出', '500', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:subject:export', '#', 'admin', sysdate(), '', null, '');

-- ============ 学生管理 ============
-- 学生管理二级菜单
INSERT INTO `sys_menu` VALUES('501', '学生管理', '5', '2', 'student', 'education/student/index', '', '', 1, 0, 'C', '0', '0', 'education:student:list', 'education', 'admin', sysdate(), '', null, '学生管理菜单');

-- 学生管理按钮权限
INSERT INTO `sys_menu` VALUES('5010', '学生查询', '501', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5011', '学生新增', '501', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5012', '学生修改', '501', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5013', '学生删除', '501', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5014', '学生导出', '501', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5015', '课时明细', '501', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'education:student:hours', '#', 'admin', sysdate(), '', null, '');

-- ============ 教师管理 ============
-- 教师管理二级菜单
INSERT INTO `sys_menu` VALUES('502', '教师管理', '5', '3', 'teacher', 'education/teacher/index', '', '', 1, 0, 'C', '0', '0', 'education:teacher:list', 'education', 'admin', sysdate(), '', null, '教师管理菜单');

-- 教师管理按钮权限
INSERT INTO `sys_menu` VALUES('5020', '教师查询', '502', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5021', '教师新增', '502', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5022', '教师修改', '502', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5023', '教师删除', '502', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5024', '教师导出', '502', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:export', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5025', '授课科目', '502', '6', '', '', '', '', 1, 0, 'F', '0', '0', 'education:teacher:hours', '#', 'admin', sysdate(), '', null, '');

-- ============ 课时注销管理 ============
-- 课时注销管理二级菜单
INSERT INTO `sys_menu` VALUES('503', '课时注销', '5', '4', 'cancellation', 'education/cancellation/index', '', '', 1, 0, 'C', '0', '0', 'education:cancellation:list', 'education', 'admin', sysdate(), '', null, '课时注销菜单');

-- 课时注销按钮权限
INSERT INTO `sys_menu` VALUES('5030', '注销查询', '503', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5031', '课时注销', '503', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5032', '注销删除', '503', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5033', '注销导出', '503', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:cancellation:export', '#', 'admin', sysdate(), '', null, '');

-- ============ 教室管理 ============
-- 教室管理二级菜单
INSERT INTO `sys_menu` VALUES('504', '教室管理', '5', '5', 'classroom', 'education/classroom/index', '', '', 1, 0, 'C', '0', '0', 'education:classroom:list', 'education', 'admin', sysdate(), '', null, '教室管理菜单');

-- 教室管理按钮权限
INSERT INTO `sys_menu` VALUES('5040', '教室查询', '504', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5041', '教室新增', '504', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5042', '教室修改', '504', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5043', '教室删除', '504', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5044', '教室导出', '504', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:classroom:export', '#', 'admin', sysdate(), '', null, '');

-- ============ 课程排课管理 ============
-- 课程排课管理二级菜单
INSERT INTO `sys_menu` VALUES('505', '课程排课', '5', '6', 'schedule', 'education/schedule/index', '', '', 1, 0, 'C', '0', '0', 'education:schedule:list', 'education', 'admin', sysdate(), '', null, '课程排课菜单');

-- 课程排课按钮权限
INSERT INTO `sys_menu` VALUES('5050', '排课查询', '505', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:query', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5051', '新增排课', '505', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:add', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5052', '排课修改', '505', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:edit', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5053', '排课删除', '505', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:remove', '#', 'admin', sysdate(), '', null, '');
INSERT INTO `sys_menu` VALUES('5054', '排课导出', '505', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'education:schedule:export', '#', 'admin', sysdate(), '', null, '');

-- ============ 数据统计 ============
-- 数据统计二级菜单
INSERT INTO `sys_menu` VALUES('506', '数据统计', '5', '7', 'statistics', 'education/statistics/index', '', '', 1, 0, 'C', '0', '0', 'education:statistics:query', 'education', 'admin', sysdate(), '', null, '数据统计菜单');

-- 数据统计按钮权限
INSERT INTO `sys_menu` VALUES('5060', '统计查询', '506', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'education:statistics:query', '#', 'admin', sysdate(), '', null, '');

-- ============ 角色权限配置 ============
-- 为管理员角色添加培训管理权限
INSERT INTO `sys_role_menu` VALUES('1', '5');
INSERT INTO `sys_role_menu` VALUES('1', '500');
INSERT INTO `sys_role_menu` VALUES('1', '5000');
INSERT INTO `sys_role_menu` VALUES('1', '5001');
INSERT INTO `sys_role_menu` VALUES('1', '5002');
INSERT INTO `sys_role_menu` VALUES('1', '5003');
INSERT INTO `sys_role_menu` VALUES('1', '5004');
INSERT INTO `sys_role_menu` VALUES('1', '501');
INSERT INTO `sys_role_menu` VALUES('1', '5010');
INSERT INTO `sys_role_menu` VALUES('1', '5011');
INSERT INTO `sys_role_menu` VALUES('1', '5012');
INSERT INTO `sys_role_menu` VALUES('1', '5013');
INSERT INTO `sys_role_menu` VALUES('1', '5014');
INSERT INTO `sys_role_menu` VALUES('1', '5015');
INSERT INTO `sys_role_menu` VALUES('1', '502');
INSERT INTO `sys_role_menu` VALUES('1', '5020');
INSERT INTO `sys_role_menu` VALUES('1', '5021');
INSERT INTO `sys_role_menu` VALUES('1', '5022');
INSERT INTO `sys_role_menu` VALUES('1', '5023');
INSERT INTO `sys_role_menu` VALUES('1', '5024');
INSERT INTO `sys_role_menu` VALUES('1', '5025');
INSERT INTO `sys_role_menu` VALUES('1', '503');
INSERT INTO `sys_role_menu` VALUES('1', '5030');
INSERT INTO `sys_role_menu` VALUES('1', '5031');
INSERT INTO `sys_role_menu` VALUES('1', '5032');
INSERT INTO `sys_role_menu` VALUES('1', '5033');
INSERT INTO `sys_role_menu` VALUES('1', '504');
INSERT INTO `sys_role_menu` VALUES('1', '5040');
INSERT INTO `sys_role_menu` VALUES('1', '5041');
INSERT INTO `sys_role_menu` VALUES('1', '5042');
INSERT INTO `sys_role_menu` VALUES('1', '5043');
INSERT INTO `sys_role_menu` VALUES('1', '5044');
INSERT INTO `sys_role_menu` VALUES('1', '505');
INSERT INTO `sys_role_menu` VALUES('1', '5050');
INSERT INTO `sys_role_menu` VALUES('1', '5051');
INSERT INTO `sys_role_menu` VALUES('1', '5052');
INSERT INTO `sys_role_menu` VALUES('1', '5053');
INSERT INTO `sys_role_menu` VALUES('1', '5054');
INSERT INTO `sys_role_menu` VALUES('1', '506');
INSERT INTO `sys_role_menu` VALUES('1', '5060');