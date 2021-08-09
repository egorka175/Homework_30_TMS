package DAO.TeacherDAO;

import entity.Discipline;
import entity.Teacher;
import services.DisciplineService.DisciplineService;
import services.TeacherService.TeacherService;

import java.io.InputStream;
import java.io.Reader;
import java.math.BigDecimal;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

public class TeacherDAO implements teacherInterface {
    TeacherService teacherService = new TeacherService();
    private Connection connection;

    public TeacherDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Teacher teacher) {
        try {
            List<Discipline> listDiscipline = new ArrayList<>();
            listDiscipline.add(new Discipline(3, "Chemistry"));
            listDiscipline.add(new Discipline(4, "Chemistry"));
            PreparedStatement preparedStatement = connection.prepareStatement("insert into teacher values (default, ?, ?, ?) returning  id,name,username,password");
            preparedStatement.setString(1, teacher.getName());
            preparedStatement.setString(2, teacher.getUsername());
            preparedStatement.setString(3, teacher.getPassword());
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            teacherService.saveTeacher(new Teacher(id, name));
            addTeacherAndIdDiscipline(listDiscipline, id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void addTeacherAndIdDiscipline(List<Discipline> listId, int id) {
        for (Discipline item : listId) {
            PreparedStatement preparedStatement1 = null;
            try {
                preparedStatement1 = connection.prepareStatement("insert into teacher_table values ( ?,?) ");
                preparedStatement1.setInt(1, id);
                preparedStatement1.setInt(2, item.getId());
                preparedStatement1.execute();
                preparedStatement1.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    @Override
    public List<Teacher> getAll() {
        List<Teacher> listTeachers = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select t.id,t.name,t.username,t.password,tt.id_discipline,tt.id_teacher from teacher t join teacher_table tt on tt.id_teacher = t.id");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                List<Discipline> listTeacherDiscipline = new ArrayList<>();
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String username = resultSet.getString(3);
                String password = resultSet.getString(4);
                Teacher teacher = new Teacher(id, name, username, password, listTeacherDiscipline);
                PreparedStatement preparedStatement1 = connection.prepareStatement("select d.id,d.name, tt.id_teacher from discipline d join teacher_table tt on tt.id_discipline=d.id where tt.id_teacher=" + id);
                ResultSet resultSet1 = preparedStatement1.executeQuery();
                while (resultSet1.next()) {
                    int idDicipline = resultSet1.getInt(1);
                    String nameDiscipline = resultSet1.getString(2);
                    listTeacherDiscipline.add(new Discipline(idDicipline, nameDiscipline));
                }
                if (!listTeachers.contains(teacher))
                    listTeachers.add(teacher);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return listTeachers;
    }

    @Override
    public void updateName(long id, String newName) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("update teacher set name=? where id=?");
            preparedStatement.setString(1, newName);
            preparedStatement.setLong(2, id);
            preparedStatement.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public boolean exist(String username) {
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from teacher where username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count != 0)
                result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteById(long id) {
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement("delete from teacher where id=? ");
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            PreparedStatement preparedStatement1 = connection.prepareStatement("delete from teacher_table where id_teacher=? ");
            preparedStatement1.setLong(1, id);
            preparedStatement1.execute();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
    }


    @Override
    public boolean findByUserName(String username) {
        boolean result = false;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("select count(*) from teacher where username=?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count != 0)
                result = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

}
