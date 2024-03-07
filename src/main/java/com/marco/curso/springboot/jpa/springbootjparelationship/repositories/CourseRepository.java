package com.marco.curso.springboot.jpa.springbootjparelationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.marco.curso.springboot.jpa.springbootjparelationship.entities.Course;

public interface CourseRepository extends CrudRepository<Course, Long>{

    @Query("SELECT c FROM Course c LEFT JOIN FETCH c.students WHERE c.id=?1")
    Optional<Course> findOneWithStudents(Long id);

}
