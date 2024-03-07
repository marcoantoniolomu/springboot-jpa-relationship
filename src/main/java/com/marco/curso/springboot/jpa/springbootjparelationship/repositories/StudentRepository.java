package com.marco.curso.springboot.jpa.springbootjparelationship.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.marco.curso.springboot.jpa.springbootjparelationship.entities.Student;

public interface StudentRepository extends CrudRepository<Student, Long>{

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id=?1")
    Optional<Student> findOneWithCourses(Long id);

}
