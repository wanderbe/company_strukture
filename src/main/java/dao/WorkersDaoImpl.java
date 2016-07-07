package dao;

import entity.Departments;
import entity.Workers;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by wanderbe on 27.06.2016.
 */
public class WorkersDaoImpl implements WorkersDao {
    private static final String GET_WORKER_BY_ID = "SELECT id, name_worker, birthday, email FROM workers WHERE id=";
    private static final String GET_WORKER_BY_EMAIL = "SELECT id, name_worker, birthday, email FROM workers WHERE email=";
    private static final String GET_WORKER_BY_ID_DEPARTMENT = "SELECT id, name_worker, birthday, email FROM workers WHERE id_department=";
    private static final String GET_LIST_WORKERS = "SELECT id, name_worker, birthday, email FROM workers";
    private static final String SAVE_NEW_WORKER = "INSERT INTO workers (name_worker, birthday, email, id_department) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_WORKER_BY_ID ="UPDATE workers SET name_worker = ?, birthday = ?, email = ?, id_department = ? WHERE id = ?";
    private static final String DELETE_WORKER_BY_ID = "DELETE FROM workers WHERE id=?";

    public Workers getByID(int id) throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_WORKER_BY_ID+id);
        rs.next();
        Workers worker = WorkersDaoImpl.createWorker(rs);
        conn.close();
        return worker;
    }

    public Workers getByEmail(String email) throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_WORKER_BY_EMAIL+"'"+email+"'");
        rs.next();
        Workers worker = WorkersDaoImpl.createWorker(rs);
        conn.close();
        return worker;
    }

    public List<Workers> getListByDepartmentsId(int id_department) throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_WORKER_BY_ID_DEPARTMENT+id_department);
        List<Workers> workers = new LinkedList<Workers>();
        while (rs.next()){
        Workers worker = WorkersDaoImpl.createWorker(rs);
            workers.add(worker);
        }
        conn.close();
        return workers;
    }

    public List<Workers> getListByDepartment(Departments department) throws SQLException {
        return (new WorkersDaoImpl()).getListByDepartmentsId(department.getId());
    }

    private static Workers createWorker(ResultSet rs) throws SQLException {
        Workers worker = new Workers();
        worker.setId(rs.getInt("id"));
        worker.setName(rs.getString("name_worker"));
        worker.setBirthdey(rs.getDate("birthday")); // todo: use calendar
        worker.setEmail(rs.getString("email"));

        return worker;
    }

    public List getList() throws SQLException {
        Connection conn = MyDriverManager.getConnection();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(GET_LIST_WORKERS);
        List<Workers> workers = new LinkedList<Workers>();
        while (rs.next()){
        Workers worker = WorkersDaoImpl.createWorker(rs);
            workers.add(worker);
        }
        conn.close();
        return workers;
    }

    public Object getByName(String name) {
        throw new UnsupportedOperationException();
    }

    public int save(Object o) throws SQLException {
        Workers worker = (Workers)o;
        Connection conn = MyDriverManager.getConnection();
        conn.setAutoCommit(false);
        PreparedStatement pst =
                conn.prepareStatement(SAVE_NEW_WORKER);
        pst.setString(1, worker.getName());
        pst.setDate(2, (Date) worker.getBirthdey());
        pst.setString(3, worker.getEmail());
        pst.setInt(4, worker.getDepartment().getId());
        pst.executeUpdate();
        conn.commit();

        int id = (new WorkersDaoImpl()).getByEmail(worker.getEmail()).getId();

        return id;
    }

    public void update(Object o) throws SQLException {
        Workers worker = (Workers)o;
        int idWorker = worker.getId();
        WorkersDao workersDao = new WorkersDaoImpl();
        if (workersDao.getByID(idWorker) == null)throw new IllegalArgumentException();
        Connection conn = MyDriverManager.getConnection();
        PreparedStatement pst =
                conn.prepareStatement(UPDATE_WORKER_BY_ID);
        pst.setString(1, worker.getName());
        pst.setDate(2, (Date) worker.getBirthdey());
        pst.setString(3, worker.getEmail());
        pst.setInt(4, worker.getDepartment().getId());
        pst.setInt(5, worker.getId());
        pst.executeUpdate();
        conn.close();
    } // TODO: 28.06.2016 boolean

    public boolean remove(Object o) throws SQLException { //// TODO: 28.06.2016 boolean
        Workers worker = (Workers)o;
        int idWorker = worker.getId();
        Connection conn = MyDriverManager.getConnection();
        PreparedStatement pst = conn.prepareStatement(DELETE_WORKER_BY_ID);
        pst.setInt(1, idWorker);
        pst.executeUpdate();

        return true;
        } // TODO: 28.06.2016 boolean
}
