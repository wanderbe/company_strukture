package validators;

import dao.WorkersDao;
import dao.WorkersDaoImpl;
import entity.Workers;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by wanderbe on 05.07.2016.
 */
public class WorkerValidator {
    private static final String REGEX_EMAIL = "^[-\\w.]+@([A-z0-9][-A-z0-9]+\\.)+[A-z]{2,4}$";

    public static boolean checkEmailByRegex(String email) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static int checkEmailOnUnike(String email) {
        WorkersDao workersDao = new WorkersDaoImpl();
        Workers worker = null;
        try {
            worker = workersDao.getByEmail(email);
        } catch (SQLException ignore) {
            // ignore
        }

        if (worker == null) return -1;
        else return worker.getId();
    }

    public static boolean checkBirthdayOnEmpty(String birthday) {
        if (birthday.length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkNameOnEmpty(String name) {
        if (name.length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean checkDepartmentNameOnEmpty(String nameDepartment) {
        if (nameDepartment.length() != 0) {
            return true;
        } else {
            return false;
        }
    }
}
