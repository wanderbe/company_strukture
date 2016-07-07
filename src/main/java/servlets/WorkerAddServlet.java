package servlets;

import dao.DepartmentsDao;
import dao.DepartmentsDaoImpl;
import dao.WorkersDao;
import dao.WorkersDaoImpl;
import entity.Departments;
import entity.Workers;
import validators.WorkerValidator;

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
public class WorkerAddServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentsDao departmentsDao = new DepartmentsDaoImpl();
        List<Departments> departments= null;
        try {
            departments = departmentsDao.getList();
        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("incorrectPage.jsp").forward(request, response);
            e.printStackTrace();
        }
        request.setAttribute("departments", departments);
        request.setAttribute("message", "Add new worker");
        request.getRequestDispatcher("workerAddPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DepartmentsDao departmentsDao = new DepartmentsDaoImpl();
        WorkersDao workersDao = new WorkersDaoImpl();

        List<Departments> departments= null;
        try {
            departments = departmentsDao.getList();
        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("incorrectPage.jsp").forward(request, response);
            e.printStackTrace();
        }
        request.setAttribute("departments", departments);

        try {
            if (!WorkerValidator.checkNameOnEmpty(request.getParameter("nameWorker"))){
                request.setAttribute("message", "Name haven't to be empty!!! \n" +
                        "Please insert name");
                request.getRequestDispatcher("workerAddPage.jsp").forward(request, response);
            }
            if (!WorkerValidator.checkBirthdayOnEmpty(request.getParameter("birthdayWorker"))){
                request.setAttribute("message", "Birthday haven't to be empty!!! \n" +
                        "Please insert birthday");
                request.getRequestDispatcher("workerAddPage.jsp").forward(request, response);
            }
            if (!WorkerValidator.checkDepartmentNameOnEmpty(request.getParameter("nameDepartmentWorker"))){
                request.setAttribute("message", "Department haven't to be empty!!! \n" +
                        "Please choose department");
                request.getRequestDispatcher("workerAddPage.jsp").forward(request, response);
            }
            Workers worker = new Workers();
            worker.setName(request.getParameter("nameWorker"));
            worker.setBirthdeyUseSimpleDateFormat(request.getParameter("birthdayWorker"));
            worker.setEmail(request.getParameter("emailWorker"));
            worker.setDepartment((Departments)departmentsDao.getByName(request.getParameter("nameDepartmentWorker")));
            if (!WorkerValidator.checkEmailByRegex(worker.getEmail())) {
                request.setAttribute("message", "Incorrect format of email address!!! \n" +
                        "Please insert correct email address");
                request.getRequestDispatcher("workerAddPage.jsp").forward(request, response);
            }
            else if (WorkerValidator.checkEmailOnUnike(worker.getEmail()) != (-1)) {
                request.setAttribute("message", "We allrady have this email address \n" +
                        "Please try another");
                request.getRequestDispatcher("workerAddPage.jsp").forward(request, response);
            }
            else {
                workersDao.save(worker);
                String redirekt = "workerslist?idDepartment=" + request.getParameter("idDepartment")
                        + "&nameDepartment=" + request.getParameter("nameDepartment");
                response.sendRedirect(redirekt);
            }
        } catch (SQLException e) {
            request.setAttribute("message", "Something wrong with your Data Base! \n " +
                    "Try letter!");
            request.getRequestDispatcher("workerAddPage.jsp").forward(request, response);
            e.printStackTrace();//// TODO: 05.07.2016 Logger
        }

    }

}
