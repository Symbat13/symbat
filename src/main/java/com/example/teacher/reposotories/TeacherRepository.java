package com.example.teacher.reposotories;

import com.example.teacher.models.Teacher;
import org.springframework.data.repository.CrudRepository;


public interface TeacherRepository extends CrudRepository<Teacher,Long>  {
}
