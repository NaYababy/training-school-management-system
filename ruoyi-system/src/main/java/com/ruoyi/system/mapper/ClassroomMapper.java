package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.Classroom;

public interface ClassroomMapper
{
    Classroom selectClassroomById(Long id);

    List<Classroom> selectClassroomList(Classroom classroom);

    int insertClassroom(Classroom classroom);

    int updateClassroom(Classroom classroom);

    int deleteClassroomById(Long id);

    int deleteClassroomByIds(Long[] ids);

    int checkClassroomCodeUnique(String classroomCode);
}