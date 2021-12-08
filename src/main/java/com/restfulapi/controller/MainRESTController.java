package com.restfulapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.restfulapi.dao.EmployeeDAO;
import com.restfulapi.model.Employee;

@RestController // để thông báo với Spring rằng nó là một Spring Restful Controller
public class MainRESTController {

    @Autowired  // Spring sẽ tự động tìm kiếm các bean tương ứng được khai báo như là tham số trong constructor.
    private EmployeeDAO employeeDAO;

    @RequestMapping("/") // nó nhận request URL từ client, để nó biết gọi API nào
    @ResponseBody  // sau khi nhận data vao, lưu xuống, trả dữ liệu về cho client dưới định dạng json, nó parse chuyển đổi từ DTO sang json
    public String welcome() {
        return "Welcome to RestTemplate Example.";
    }
    
    // Get all method
    @RequestMapping(value = "/api/employees", method = RequestMethod.GET, //
            produces = { MediaType.APPLICATION_JSON_VALUE })  //produces (trả về cho người dùng) các dữ liệu với các định dạng nào.
    @ResponseBody
    public List<Employee> getEmployees() {
        List<Employee> list = employeeDAO.getAllEmployees();
        return list;
    }
    
    // Get by ID Method
    @RequestMapping(value = "/api/employee/{empNo}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody 
    public Employee getEmployee(@PathVariable("empNo") String empNo) {  // có thể sử dụng @RequestParam("id") String personId
        return employeeDAO.getEmployee(empNo);
    }
    
    // POST Method
    @RequestMapping(value = "/api/employee", method = RequestMethod.POST, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody // sau khi nhận data vao, lưu xuống, trả dữ liệu về cho client dưới định dạng json, nó parse chuyển đổi từ DTO sang json
    public Employee addEmployee(@RequestBody Employee emp) { // requestBody đối tượng giúp chúng ta nhận data từ client

        System.out.println("(Service Side) Creating employee: " + emp.getEmpNo());
        return employeeDAO.addEmployee(emp);
    }
    
    // PUT method
    @RequestMapping(value = "/api/employee", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public Employee updateEmployee(@RequestBody Employee emp) {
        System.out.println("(Service Side) Editing employee: " + emp.getEmpNo());
        return employeeDAO.updateEmployee(emp);
    }
    
    // DELETE method
    @RequestMapping(value = "/api/employee/{empNo}", method = RequestMethod.DELETE, produces = { MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public void deleteEmployee(@PathVariable("empNo") String empNo) {
        System.out.println("(Service Side) Deleting employee: " + empNo);
        employeeDAO.deleteEmployee(empNo);
    }
}
