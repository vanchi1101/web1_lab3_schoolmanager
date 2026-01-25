package com.example.schoolmanager.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.schoolmanager.model.Student;
import com.example.schoolmanager.repository.StudentRepository;

//  Service xử lý logic nghiệp vụ (ở đây chỉ đọc dữ liệu).
@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {// xử lý trả về dữ là list students
        return repository.findAll();
    }

    public Student getStudentById(int id) {// xử lý trả về dữ liệu là student
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy sinh viên với id: " + id));
    }

    public List<Student> searchStudentByName(String name) {
        if (name == null || name.isEmpty()) {
            return repository.findAll();
        }
        return repository.findByNameContainingIgnoreCase(name);
    }

    // Thêm / Sửa chung
    public void saveStudent(Student student) {
        repository.save(student);
    }

    // Xóa
    public void deleteStudent(int id) {
        repository.deleteById(id);
    }
}
