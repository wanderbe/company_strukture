package servlets;

import dao.DepartmentsDao;
import dao.DepartmentsDaoImpl;
import dao.WorkersDao;
import dao.WorkersDaoImpl;
import entity.Departments;
import entity.Workers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wanderbe on 28.06.2016.
 */
public class DepartmentDeleteServlet extends HttpServlet{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DepartmentsDao departmentsDao = new DepartmentsDaoImpl();
        WorkersDao workersDao = new WorkersDaoImpl();

        try {
            Departments department =
                    (Departments)departmentsDao.getByID(Integer.parseInt(request.getParameter("idDepartment")));
            List<Workers> workers = workersDao.getListByDepartment(department);
            for (Workers w:workers){
                w.setDepartment(departmentsDao.getDeletedDepartment());
                workersDao.update(w);
            }
            departmentsDao.remove(department);
        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("incorrectPage.jsp").forward(request, response);
            e.printStackTrace(); // // TODO: 07.07.2016 Logger
        }

        response.sendRedirect("departmentslist");
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
