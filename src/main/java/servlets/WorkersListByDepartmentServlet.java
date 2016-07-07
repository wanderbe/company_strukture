package servlets;

import dao.WorkersDao;
import dao.WorkersDaoImpl;
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
public class WorkersListByDepartmentServlet extends HttpServlet{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        WorkersDao workersDao = new WorkersDaoImpl();
        List<Workers> workers = null;
        String message = "";

        try {
            if(request.getParameter("idDepartment") == null || request.getParameter("idDepartment").length() == 0
                    || request.getParameter("idDepartment") == "null"){
                workers = workersDao.getList();
                message = "List workers";
            }
            else {
                workers = workersDao.getListByDepartmentsId(Integer.parseInt(request.getParameter("idDepartment")));
                message = "List workers of Department '" + request.getParameter("nameDepartment") + "':";
            }
        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("incorrectPage.jsp").forward(request, response);
            e.printStackTrace(); // // TODO: 07.07.2016 Logger
        }

        request.setAttribute("workers", workers);
        request.setAttribute("message", message);
        request.getRequestDispatcher("workersList.jsp").forward(request, response);
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
