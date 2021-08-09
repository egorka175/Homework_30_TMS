package DAO.DisciplineDAO;

import entity.Discipline;
import entity.Teacher;

import java.util.List;

public interface disciplineInterface {
    void save(Discipline discipline);
    List<Discipline> getAll();
    Discipline getById(long id);
    boolean exist(String username);
    void deleteById(long id);
    Discipline getByName(String name);


}

