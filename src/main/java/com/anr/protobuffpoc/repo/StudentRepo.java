package com.anr.protobuffpoc.repo;

import com.anr.protobuffpoc.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, Integer> {
}
