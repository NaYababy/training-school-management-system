package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.Classroom;
import com.ruoyi.system.mapper.ClassroomMapper;
import com.ruoyi.system.mapper.CourseScheduleMapper;
import com.ruoyi.system.service.IClassroomService;

@Service
public class ClassroomServiceImpl implements IClassroomService
{
    @Autowired
    private ClassroomMapper classroomMapper;

    @Autowired
    private CourseScheduleMapper courseScheduleMapper;

    @Override
    public Classroom selectClassroomById(Long id)
    {
        return classroomMapper.selectClassroomById(id);
    }

    @Override
    public List<Classroom> selectClassroomList(Classroom classroom)
    {
        return classroomMapper.selectClassroomList(classroom);
    }

    @Override
    public int insertClassroom(Classroom classroom)
    {
        return classroomMapper.insertClassroom(classroom);
    }

    @Override
    public int updateClassroom(Classroom classroom)
    {
        return classroomMapper.updateClassroom(classroom);
    }

    @Override
    public int deleteClassroomById(Long id)
    {
        return classroomMapper.deleteClassroomById(id);
    }

    @Override
    @Transactional
    public void deleteClassroomByIds(Long[] ids)
    {
        for (Long id : ids)
        {
            if (courseScheduleMapper.countByClassroomId(id) > 0) {
                throw new ServiceException("该教室存在排课记录，无法删除");
            }
            classroomMapper.deleteClassroomById(id);
        }
    }

    @Override
    public boolean checkClassroomCodeUnique(Classroom classroom)
    {
        Long id = StringUtils.isNull(classroom.getId()) ? -1L : classroom.getId();
        int count = classroomMapper.checkClassroomCodeUnique(classroom.getClassroomCode());
        return count == 0 || (count == 1 && classroomMapper.selectClassroomById(id) != null);
    }

    @Override
    public List<Classroom> selectAllClassrooms()
    {
        return classroomMapper.selectClassroomList(new Classroom());
    }
}