package servlets;

import dao.DepartmentsDao;
import dao.DepartmentsDaoImpl;
import entity.Departments;
import validators.DepartmentValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by wanderbe on 28.06.2016.
 */
public class DepartmentUpdateServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("message", "Update department");
        request.getRequestDispatcher("departmentUpdatePage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentsDao departmentsDao = new DepartmentsDaoImpl();

        try {
            Departments department = new Departments();
            department.setId(Integer.parseInt(request.getParameter("idDepartment")));
            department.setName(request.getParameter("nameDepartment"));
            if (DepartmentValidator.checkDepartmentNameOnUnike(department.getName())!=(-1)&&
                    DepartmentValidator.checkDepartmentNameOnUnike(department.getName())!=department.getId()){
                request.setAttribute("message", "We allready have this department!!! \n" +
                        "Try another!");
                request.getRequestDispatcher("departmentUpdatePage.jsp").forward(request, response);
            }
            else {
                departmentsDao.update(department);
                response.sendRedirect("departmentslist?message=Departent updated!");
            }
        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("departmentUpdatePage.jsp").forward(request, response);
            e.printStackTrace();//// TODO: 05.07.2016 Logger
        }
    }

}
