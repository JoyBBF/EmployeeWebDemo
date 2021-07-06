/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.employeewebdemo.model;

import java.util.ArrayList;

/**
 *
 * @author JoyBB
 */
public class Model {
    //---- Singleton Variable ----
    private static Model uniqueInstance;
    
    public static Model instance(){
        if (uniqueInstance == null){
            uniqueInstance = new Model();
        }
        return uniqueInstance;
    }
    //----------------------------
    
    ArrayList<Employee> employees;
    
    //Init
    private Model(){
        employees = new ArrayList();
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    public void insert(Employee employee) {
        employees.add(employee);
    }
    public void delete(int index) {
        employees.remove(index);
    }

    public void deleteAll() {
        employees = new ArrayList();
    }
    
    public void update(int index, Employee emp) {
        employees.remove(index);
        employees.add(index, emp);
    }
    
}
