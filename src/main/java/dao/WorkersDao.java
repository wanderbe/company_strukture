package dao;

import entity.Departments;
import entity.Workers;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wanderbe on 27.06.2016.
 */
public interface WorkersDao extends DAO{
    Workers getByEmail(String email) throws SQLException;
    List<Workers> getListByDepartmentsId(int id_department) throws SQLException;
    List<Workers> getListByDepartment(Departments department) throws SQLException;
}
