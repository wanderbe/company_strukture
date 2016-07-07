package validators;

import dao.DepartmentsDao;
import dao.DepartmentsDaoImpl;
import entity.Departments;

import java.sql.SQLException;

/**
 * Created by wanderbe on 05.07.2016.
 */
public class DepartmentValidator {

    public static int checkDepartmentNameOnUnike(String nameDepartment) {
        DepartmentsDao departmentsDao = new DepartmentsDaoImpl();
        Departments department = null;
        try {
            department = (Departments) departmentsDao.getByName(nameDepartment);
        } catch (SQLException ignore) {
            //ignore
        }

        if (department == null) return -1;
        else return department.getId();
    }
}
