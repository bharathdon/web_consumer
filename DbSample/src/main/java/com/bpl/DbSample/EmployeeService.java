package com.bpl.DbSample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class EmployeeService {

    @GetMapping(path = "/emp", produces = {"application/xml", "application/json", "text/plain"})
    public List<Employee> findALl() throws SQLException {

        ArrayList<Employee> arrayList = new ArrayList<Employee>();
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bms", "abc");
        PreparedStatement prepareStatement = connection.prepareStatement("select * from emp_tbl");
        ResultSet resultSet = prepareStatement.executeQuery();
        while (resultSet.next()) {
            Employee employee = new Employee();
            employee.setEno(resultSet.getInt("eno"));
            employee.setName(resultSet.getString("name"));
            arrayList.add(employee);
        }

        return arrayList;
    }

    @PostMapping(path = "/emp1", produces = {"application/xml", "application/json", "text/plain"}, consumes = {"application/xml",
            "application/json", "text/plain"})
    public int insert(Employee employee) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bms", "abc");
        PreparedStatement prepareStatement = connection.prepareStatement("insert into emp_tbl values(?,?,?,?)");
        prepareStatement.setInt(1, 124);
        prepareStatement.setString(2, "ajay");
        prepareStatement.setDouble(3, 500d);
        prepareStatement.setString(4, "dept");
        int update = prepareStatement.executeUpdate();

        return update;

    }

    @PostMapping(path = "/emp2", produces = {"application/xml", "application/json", "text/plain"}, consumes = {"application/xml",
            "application/json", "text/plain"})
    public int update(Employee employee) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bms", "abc");
        PreparedStatement prepareStatement = connection.prepareStatement("update emp_tbl set name= 'bharah' where eno=1");
        int update = prepareStatement.executeUpdate();
        return update;

    }


    @DeleteMapping(path = "/emp3", produces = {"application/xml", "application/json", "text/plain"}, consumes = {"application/xml",
            "application/json", "text/plain"})
    public int delete(Employee employee) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "bms", "abc");
        PreparedStatement prepareStatement = connection.prepareStatement("delete from emp_tbl where eno = 2");
        System.out.println(employee);
        int update = prepareStatement.executeUpdate();

        return update;

    }


}
