package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.Classroom;

public interface IClassroomService
{
    Classroom selectClassroomById(Long id);

    List<Classroom> selectClassroomList(Classroom classroom);

    int insertClassroom(Classroom classroom);

    int updateClassroom(Classroom classroom);

    int deleteClassroomById(Long id);

    void deleteClassroomByIds(Long[] ids);

    boolean checkClassroomCodeUnique(Classroom classroom);

    List<Classroom> selectAllClassrooms();
}