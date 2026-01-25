package com.example.schoolmanager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

import com.example.schoolmanager.service.StudentService;
import com.example.schoolmanager.model.Student;

// Controller: Điều hướng & truyền dữ liệu
// Controller xử lý request GET /students. Model truyền dữ liệu sang HTML. @Controller cho web MVC (không phải REST API)
@Controller
@RequestMapping("/api")
// @CrossOrigin // cho phép frontend gọi
public class StudentController {

    @Autowired
    private StudentService service;

    // 1. Danh sách + Tìm kiếm
    @GetMapping("/students")
    public String listStudents(@RequestParam(required = false) String name, Model model) {
        List<Student> students = service.searchStudentByName(name);
        model.addAttribute("students", students);
        model.addAttribute("searchName", name);// giữ giá trị tìm kiếm
        return "students"; // Trả về file students.html
    }

    // 2. Chi tiết
    @GetMapping("/student/{id}")
    public String viewStudent(@PathVariable("id") int id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        return "student-detail";// trả dữ liệu về file student-detail.html
    }

    // 3. Trang thêm mới
    @GetMapping("/students/new")
    public String showCreateForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("title", "Thêm sinh viên mới");
        return "student-form"; // sẽ tạo file student-form.html
    }

    // 3.1 Xử lý thêm mới
    @PostMapping("/students")
    public String createStudent(@ModelAttribute Student student) {
        // ID sẽ do bạn tự tăng hoặc dùng IDENTITY trong SQL (xem phần DB)
        service.saveStudent(student);
        return "redirect:/api/students";// khi lưu xong thì quay lại trang danh sách sv
    }

    // 4. Trang sửa
    @GetMapping("/students/{id}/edit")
    public String showEditForm(@PathVariable int id, Model model) {
        Student student = service.getStudentById(id);
        model.addAttribute("student", student);
        model.addAttribute("title", "Sửa thông tin sinh viên");
        return "student-form";
    }

    // 4.1 Xử lý sửa
    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable int id, @ModelAttribute Student student) {
        student.setId(id); // đảm bảo id đúng
        service.saveStudent(student);
        return "redirect:/api/students";
    }

    // 5. Xóa
    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
        return "redirect:/api/students";
    }

}
