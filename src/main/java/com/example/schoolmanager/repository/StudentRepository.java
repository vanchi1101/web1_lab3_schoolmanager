package com.example.schoolmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.schoolmanager.model.Student;

// Repository: Truy vấn dữ liệu
// Repository kế thừa JpaRepository để có sẵn các method CRUD như findAll(), save()...
public interface StudentRepository extends JpaRepository<Student, Integer> {
    Optional<Student> findById(Integer id);// tìm student theo id để lấy thông tin chi tiết theo id

    List<Student> findByNameContainingIgnoreCase(String name);
}
