package DAO.TeacherDAO;

import entity.Discipline;
import entity.Teacher;

import java.sql.SQLException;
import java.util.List;

public interface teacherInterface {
   void save(Teacher teacher) ;
   void addTeacherAndIdDiscipline(List<Discipline> listId, int id);
   List<Teacher> getAll();
   void updateName(long id, String newName);
   boolean exist(String username);
   void deleteById(long id);
   boolean findByUserName(String username);


}
