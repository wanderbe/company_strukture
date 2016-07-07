package dao;

import entity.Departments;
import entity.Workers;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanderbe on 27.06.2016.
 */
public class DepartmentsDaoImpl implements DepartmentsDao {
    private static final String DELETED_DEPARTMENT = "Department_was_delete";
    private static final String GET_BY_ID = "SELECT id, name_department FROM departments WHERE id=";
    private static final String GET_LIST = "SELECT id, name_department FROM departments";
    private static final String GET_BY_NAME = "SELECT id, name_department FROM departments WHERE name_department=";
    private static final String SAVE_NEW_DEPARTMENT = "INSERT INTO departments (name_department) VALUES (?)";
    private static final String UPDATE_DEPARTMENT = "UPDATE departments SET name_department = ? WHERE id = ?";
    private static final String DELETE_DEPARTMENT_BY_ID = "DELETE FROM departments WHERE id=?";
    private static final String GET_DEPARTMENT_ID_BY_WORKER_ID = "SELECT id_department FROM workers WHERE id=";

    public Departments getByID(int id) throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_BY_ID+id);
        rs.next();
        Departments department = DepartmentsDaoImpl.createDepartment(rs);
        conn.close();
        return department;
    }

    public List getList() throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_LIST);
        List<Departments> departments = new LinkedList<Departments>();
        while (rs.next()) {
            Departments department = DepartmentsDaoImpl.createDepartment(rs);
            departments.add(department);
        }
        conn.close();
        return departments;
    }

    public Departments getByName(String name) throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_BY_NAME+"'"+name + "'");
        rs.next();
        Departments department = DepartmentsDaoImpl.createDepartment(rs);
        conn.close();
        return department;
    }

    public int save(Object o) throws SQLException {
        Departments department = (Departments) o;
        Connection conn = MyDriverManager.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pst =
                conn.prepareStatement(SAVE_NEW_DEPARTMENT);
        pst.setString(1, department.getName());
        pst.executeUpdate();
        conn.commit();

        int id = getByName(department.getName()).getId();

        return id;
    }

    public void update(Object o) throws SQLException {
        Departments department = (Departments) o;
        int idDepartment = department.getId();
        DepartmentsDao departmentsDao = new DepartmentsDaoImpl();
        if (departmentsDao.getByID(idDepartment) == null)throw new IllegalArgumentException();
        Connection conn = MyDriverManager.getConnection();
        PreparedStatement pst =
                conn.prepareStatement(UPDATE_DEPARTMENT);
        pst.setString(1, department.getName());
        pst.setInt(2, department.getId());
        pst.executeUpdate();
        conn.close();
    }

    public boolean remove(Object o) throws SQLException { //// TODO: 28.06.2016 boolean
        Departments department = (Departments) o;
        int idDepartment = department.getId();
        Connection conn = MyDriverManager.getConnection();
        PreparedStatement pst = conn.prepareStatement(DELETE_DEPARTMENT_BY_ID);
        pst.setInt(1, idDepartment);
        pst.executeUpdate();
        return true;
        }

    private static Departments createDepartment(ResultSet rs) throws SQLException {
        Departments department = new Departments();
        int id_department = rs.getInt("id");
        department.setId(id_department);
        department.setName(rs.getString("name_department"));
        return department;
    }

    public Departments getByWorkerId(int id_worker) throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_DEPARTMENT_ID_BY_WORKER_ID+id_worker);
        rs.next();
        int id_department = rs.getInt("id_department");
        conn.close();
        return (new DepartmentsDaoImpl()).getByID(id_department);
    }

    public Departments getByWorker(Workers worker) throws SQLException {
        return (new DepartmentsDaoImpl()).getByWorkerId(worker.getId());
    }

    @Override
    public Departments getDeletedDepartment() throws SQLException{
        Departments department = null;
        try {
           department = getByName(DELETED_DEPARTMENT);
        } catch (SQLException ignore) {
            // Ignore
        }
        if (department != null) return department;
        department = new Departments();
        department.setName(DELETED_DEPARTMENT);
        save(department);
        department = getByName(DELETED_DEPARTMENT);
        return department;
    }
}
