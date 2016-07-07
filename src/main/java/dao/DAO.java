package dao;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wanderbe on 27.06.2016.
 */
public interface DAO {
    Object getByID(int id) throws SQLException;
    List getList() throws SQLException;
    Object getByName(String name) throws SQLException;
    int save(Object o) throws SQLException;
    void update(Object o) throws SQLException;
    boolean remove(Object o) throws SQLException;
}
