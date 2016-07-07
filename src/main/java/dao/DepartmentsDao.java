package dao;

import entity.Departments;
import entity.Workers;

import java.sql.SQLException;

/**
 * Created by wanderbe on 27.06.2016.
 */
public interface DepartmentsDao extends DAO {
    Departments getByWorkerId (int id_worker)throws SQLException;
    Departments getByWorker(Workers worker) throws SQLException;
    Departments getDeletedDepartment() throws SQLException;
}
