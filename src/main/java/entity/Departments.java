package entity;

import dao.WorkersDaoImpl;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by wanderbe on 27.06.2016.
 */
public class Departments {
    int id;
    String name;
    List<Workers> workers;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWorkers(List<Workers> workers) {
        this.workers = workers;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Workers> getWorkers() throws SQLException {
        if (workers == null){
            this.setWorkers((new WorkersDaoImpl()).getListByDepartment(this));
        }
        return workers;
    }

    @Override
    public String toString() {
        return "Departments{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
