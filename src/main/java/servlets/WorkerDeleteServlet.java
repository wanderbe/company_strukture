package servlets;

import dao.WorkersDao;
import dao.WorkersDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by wanderbe on 28.06.2016.
 */
public class WorkerDeleteServlet extends HttpServlet{

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        WorkersDao workersDao = new WorkersDaoImpl();

        try {
            workersDao.remove(workersDao.getByID(Integer.parseInt(request.getParameter("idWorker"))));
        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("incorrectPage.jsp").forward(request, response);
            e.printStackTrace(); // // TODO: 07.07.2016 Logger
        }

        String redirekt = "workerslist?idDepartment=" + request.getParameter("idDepartment")
                + "&nameDepartment=" + request.getParameter("nameDepartment");
        response.sendRedirect(redirekt);
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
