package entity;

import dao.DepartmentsDaoImpl;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;

/**
 * Created by wanderbe on 27.06.2016.
 */
public class Workers {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private int id;
    private String name;
    private Date birthdey;
    private String email;
    private Departments department;

    public Departments getDepartment() throws SQLException {
        if (department == null){
            this.setDepartment((new DepartmentsDaoImpl()).getByWorker(this));
        }
        return department;
    }

    public void setDepartment(Departments department) {
        this.department = department;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthdey(Date birthdey) {
        this.birthdey = birthdey;
    }

    public void setBirthdeyUseSimpleDateFormat(String birthdey) {
        DateFormat dateFormat;
        dateFormat = new SimpleDateFormat(DATE_PATTERN);
        Date birthdayDate = null;
        try {
            birthdayDate = new java.sql.Date(dateFormat.parse(birthdey).getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);//// TODO: 06.07.2016 Work with exeption
        }
        setBirthdey(birthdayDate);
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Date getBirthdey() {
        return birthdey;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        String result = null;
        try {
            result = "Workers{" + "id=" + id + ", name=" + name + ", birthdey=" + birthdey + ", email=" + email +
                    ", department=" + this.getDepartment().getName() + '}';
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


}
