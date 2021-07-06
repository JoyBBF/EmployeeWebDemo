/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import com.employeewebdemo.model.Employee;
import com.employeewebdemo.model.Model;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author JoyBB
 */
@WebServlet(name = "ServletEmployee", urlPatterns = {"/ServletEmployee"})
public class ServletEmployee extends HttpServlet{
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        switch (request.getServletPath()) {
            case "/ServletEmployee/selectAll":
                this.selectAll(request, response);
                break;
            case "/ServletEmployee/insert":
                this.insert(request, response);
                break;
            case "/ServletEmployee/delete":
                this.delete(request, response);
                break;
            case "/ServletEmployee/deleteAll":
                this.deleteAll(request, response);
                break;
            case "/ServletEmployee/update":
                this.update(request, response);
                break;
        }   
    }
    
    protected void selectAll(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            // Get Model
            Model model = Model.instance();
            
            //String id = request.getParameter("id");
            ArrayList<Employee> lista = model.getEmployees();
            
            Gson gson = new Gson();
            response.getWriter().write(gson.toJson(lista));
            
            response.setStatus(200);
            
        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }
    protected void insert(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            // Get Model
            Model model = Model.instance();;
            
            Gson gson = new Gson();
            
            Employee employee = gson.fromJson(request.getReader(), Employee.class);
            
            model.insert(employee);
            
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }
    protected void delete(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            // Get Model
            Model model = Model.instance();;
            
            int index = Integer.valueOf(request.getParameter("index"));
            
            model.delete(index);
            
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }
    protected void deleteAll(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            // Get Model
            Model model = Model.instance();;
            
            model.deleteAll();
            
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }
    protected void update(HttpServletRequest request,
            HttpServletResponse response) {
        try {
            HttpSession session = request.getSession(true);
            // Get Model
            Model model = Model.instance();;
            Gson gson = new Gson();
            
            int index = Integer.valueOf(request.getParameter("index"));
            
            Employee employee = new Employee(request.getParameter("name"), request.getParameter("lastName"), request.getParameter("email"));
            
            model.update(index, employee);
            
            response.setStatus(204);
        } catch (Exception e) {
            response.setStatus(status(e));
        }
    }
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    protected int status(Exception e) {
        if (e.getMessage().startsWith("404")) {
            return 404;
        }
        if (e.getMessage().startsWith("406")) {
            return 406;
        }
        return 400;
    }
}
