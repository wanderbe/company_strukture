package servlets;

import dao.DepartmentsDao;
import dao.DepartmentsDaoImpl;
import entity.Departments;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanderbe on 28.06.2016.
 */
public class DepartmentsListByWorkerServlet extends HttpServlet{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DepartmentsDao departmentsDao = new DepartmentsDaoImpl();
        List<Departments> departments= new LinkedList<>();
        String message = null;

        try {
            if(request.getParameter("idWorker") == null || request.getParameter("idWorker").length() == 0
                    || request.getParameter("idWorker") == "null"){
                departments = departmentsDao.getList();
                message= "List of Department:";
            }
            else {
                int idWorker = Integer.parseInt(request.getParameter("idWorker"));
                Departments department = departmentsDao.getByWorkerId(idWorker);
                departments.add(department);
                message = "Department by worker " + "'" + request.getParameter("nameWorker") + "':";
            }


        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("incorrectPage.jsp").forward(request, response);
            e.printStackTrace(); // // TODO: 07.07.2016 Logger
        }

        request.setAttribute("departments", departments);
        request.setAttribute("message", message);
        request.getRequestDispatcher("departmentsList.jsp").forward(request, response);
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
